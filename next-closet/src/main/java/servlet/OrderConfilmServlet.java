
package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.StockManager;
import model.bean.CartItemBean;
import model.dao.CartDAO;
import model.dao.ProductDAO;
import model.dao.UserDAO;
import regexp.AddressValidator;
import regexp.PostCodeValidator;

@WebServlet("/OrderConfilmServlet")
public class OrderConfilmServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {

		ProductDAO productDao = new ProductDAO();
		UserDAO userDao = new UserDAO();
		CartDAO cartDao = new CartDAO();
		
		int userId = (int) request.getSession().getAttribute("userId");
		String order = request.getParameter("order"); //今すぐ購入だったら中身がある
		int cartItemId = -1;
		String cartItemIdStr = request.getParameter("cartItemId");
		String addAddress = request.getParameter("addAddress");
		
		if( addAddress != null) { //住所の追加があった場合
			
			String postCode = request.getParameter("postcode");
			String prefectures = request.getParameter("prefectures");
			String address = request.getParameter("address");
			
			//郵便番号チェック: 全角を半角に置換
			String convertPostCode = postCode.replace("０", "0")
											 .replace("１", "1")
											 .replace("２", "2")
											 .replace("３", "3")
											 .replace("４", "4")
											 .replace("５", "5")
											 .replace("６", "6")
											 .replace("７", "7")
											 .replace("８", "8")
											 .replace("９", "9");
			
			//郵便番号の入力に対してハイフン無しの形式を要
			if(!PostCodeValidator.validate(convertPostCode)) {
				request.setAttribute("error_message", "郵便番号が正しくありません");
			}
			
			//住所の空文字チェック
			if(address.isEmpty()) {
				request.setAttribute("error_message", "住所が正しくありません");
			}
			
			if(PostCodeValidator.validate(convertPostCode) && address != null) {
			
				//住所のデータを統一(全角を半角にする)
				String normalizedAddress = AddressValidator.normalizeAddress(address);
				
				UserDAO uDao = new UserDAO();
				try {
					int res = uDao.addSubAddress(userId, convertPostCode, normalizedAddress, prefectures);
					if(res == 1) {
				        request.setAttribute("message", "住所を追加しました");
					}
				} catch(ClassNotFoundException e) {
					e.printStackTrace();
					request.getSession().setAttribute("errorMessage", "内部の設定エラーが発生しました。"
							+ "お問い合わせよ管理者に連絡して、解決の支援を受けてください。");
			        response.sendRedirect("error.jsp");
			        return;
				} catch(SQLException e) {
					e.printStackTrace();
					request.getSession().setAttribute("errorMessage", "現在データベースにアクセスできません。後ほど再度お試しください。"
							+ "問題が続く場合は、お問い合わせより管理者にご連絡ください。");
					response.sendRedirect("error.jsp");
					return;
				} catch(Exception e) {
					e.printStackTrace();
					request.getSession().setAttribute("errorMessage", "申し訳ありませんが、システムエラーが発生しました。"
							+ "もう一度お試しいただくか、お問い合わせより管理者にお問い合わせください。");
					response.sendRedirect("error.jsp");
					return;
				}
			}
			
		} //住所追加処理終了

		if (cartItemIdStr != null) {
			cartItemId = Integer.parseInt(request.getParameter("cartItemId")); //カートの中身のうち一つのみ購入の場合は中身が入る
		}

		try {
			//userIdでUserBeanから情報を持ってくる
			request.setAttribute("user", userDao.getUpdatedUser(userId)); //user情報
			request.setAttribute("address", userDao.getUserAddressId(userId)); //メイン住所
			request.setAttribute("addAddresses", userDao.getSubAddress(userId)); //サブ住所
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			request.getSession().setAttribute("errorMessage", "内部の設定エラーが発生しました。"
					+ "お問い合わせよ管理者に連絡して、解決の支援を受けてください。");
			response.sendRedirect("error.jsp");
			return;
		} catch (SQLException e) {
			e.printStackTrace();
			request.getSession().setAttribute("errorMessage", "現在データベースにアクセスできません。後ほど再度お試しください"
					+ "。問題が続く場合は、お問い合わせより管理者にご連絡ください。");
			response.sendRedirect("error.jsp");
			return;
		}

		if (order != null) { //商品詳細 → 今すぐ購入の場合

			try {
				//productIdで商品の情報をとってくる
				int productId = Integer.parseInt(request.getParameter("productId"));
				int sizeId = Integer.parseInt(request.getParameter("sizeId"));
				int quantity = Integer.parseInt(request.getParameter("quantity"));

				request.setAttribute("productList", productDao.detailProductList(productId)); //商品の詳細情報　商品名やらお金やら
				request.setAttribute("order", "order");//今回のオーダーが今すぐ購入から購入であることを証明
				request.setAttribute("sizeId", sizeId);
				request.setAttribute("orderProductId", productId);
				request.setAttribute("quantity", quantity);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				request.getSession().setAttribute("errorMessage", "内部の設定エラーが発生しました。"
						+ "お問い合わせよ管理者に連絡して、解決の支援を受けてください。");
				response.sendRedirect("error.jsp");
				return;
			} catch (SQLException e) {
				e.printStackTrace();
				request.getSession().setAttribute("errorMessage", "現在データベースにアクセスできません。後ほど再度お試しください"
						+ "。問題が続く場合は、お問い合わせより管理者にご連絡ください。");
				response.sendRedirect("error.jsp");
				return;
			}

		} else {

			if (cartItemId != -1) { //カートの中のどれか一つの場合
				//setAttributeでカートの単品であることを情報として持っていく
				request.setAttribute("order", "singleCartItem"); //今回のオーダーがカートの中身のうち一つであることを証明

				try {
					CartItemBean Item = cartDao.getCartItem(cartItemId); //cartItemの情報を受け取る
					request.setAttribute("cartItem", Item); //CartItemBeanのリストでくる
					request.setAttribute("cartItemId", cartItemId);
					int productId = Item.getProductId(); //cartItemのproductIdを受け取る
					request.setAttribute("productList", productDao.detailProductList(productId)); //商品の詳細をjspに渡す
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
					request.getSession().setAttribute("errorMessage", "内部の設定エラーが発生しました。"
							+ "お問い合わせよ管理者に連絡して、解決の支援を受けてください。");
					response.sendRedirect("error.jsp");
					return;
				} catch (SQLException e) {
					e.printStackTrace();
					request.getSession().setAttribute("errorMessage", "現在データベースにアクセスできません。後ほど再度お試しください"
							+ "。問題が続く場合は、お問い合わせより管理者にご連絡ください。");
					response.sendRedirect("error.jsp");
					return;
				}

			} else { //もしカートの中身全部だったら

				try {
					request.setAttribute("cartAllItemList", cartDao.getAllCartItems(userId));
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
					request.getSession().setAttribute("errorMessage", "内部の設定エラーが発生しました。"
							+ "お問い合わせよ管理者に連絡して、解決の支援を受けてください。");
					response.sendRedirect("error.jsp");
					return;
				} catch (SQLException e) {
					e.printStackTrace();
					request.getSession().setAttribute("errorMessage", "現在データベースにアクセスできません。後ほど再度お試しください"
							+ "。問題が続く場合は、お問い合わせより管理者にご連絡ください。");
					response.sendRedirect("error.jsp");
					return;
				}
				request.setAttribute("order", "allCartItems");//今回のオーダーがカートの中身を全て購入であることを証明
			}
		}
		request.getRequestDispatcher("order-confirm.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		ProductDAO productDao = new ProductDAO();
		UserDAO userDao = new UserDAO();
		CartDAO cartDao = new CartDAO();
		StockManager st = new StockManager();
		
		int userId = (int) request.getSession().getAttribute("userId");
		String delivery_address = (String) request.getParameter("address");
		int totalAmount = Integer.parseInt(request.getParameter("totalAmount"));
		String order = request.getParameter("order");
		request.setAttribute("order", order);

		try {
			//userIdでUserBeanから情報を持ってくる
			request.setAttribute("user", userDao.getUpdatedUser(userId)); //user情報
			request.setAttribute("address", userDao.getUserAddressId(userId)); //メイン住所
			request.setAttribute("addAddresses", userDao.getSubAddress(userId)); //サブ住所
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			request.getSession().setAttribute("errorMessage", "内部の設定エラーが発生しました。"
					+ "お問い合わせよ管理者に連絡して、解決の支援を受けてください。");
			response.sendRedirect("error.jsp");
			return;
		} catch (SQLException e) {
			e.printStackTrace();
			request.getSession().setAttribute("errorMessage", "現在データベースにアクセスできません。後ほど再度お試しください"
					+ "。問題が続く場合は、お問い合わせより管理者にご連絡ください。");
			response.sendRedirect("error.jsp");
			return;
		}

		if (order.equals("order")) { //商品詳細 → 今すぐ購入だったら

			int quantity = Integer.parseInt(request.getParameter("quantity")); //購入数を取得
			int sizeId = Integer.parseInt(request.getParameter("sizeId")); //sizeを取得
			int productId = Integer.parseInt(request.getParameter("productId"));

			try {
				if (st.productStock(productId, sizeId, quantity)) { //もし在庫がたりたら	
					st.decrementStock(productId, sizeId, quantity); //在庫 - 購入数
					st.orderRegistration(productId, sizeId, quantity, userId, totalAmount, delivery_address); //order_itemsに追加（INSERT）

					//ご購入いただきましてありがとうございました。に飛ばす
					RequestDispatcher dispatcher = request.getRequestDispatcher("order.jsp");
					dispatcher.forward(request, response);

				} else { // もし在庫がたりなかったら

					request.setAttribute("error_message", "この商品は在庫切れのため現在購入できません");
					sizeId = Integer.parseInt(request.getParameter("sizeId")); //もう一度サイズ渡す
					quantity = Integer.parseInt(request.getParameter("quantity")); //もう数量渡す

					try {
						request.setAttribute("productList", productDao.detailProductList(productId)); //商品の詳細情報を渡す
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
						request.getSession().setAttribute("errorMessage", "内部の設定エラーが発生しました。"
								+ "お問い合わせよ管理者に連絡して、解決の支援を受けてください。");
						response.sendRedirect("error.jsp");
						return;
					} catch (SQLException e) {
						e.printStackTrace();
						request.getSession().setAttribute("errorMessage", "現在データベースにアクセスできません。後ほど再度お試しください"
								+ "。問題が続く場合は、お問い合わせより管理者にご連絡ください。");
						response.sendRedirect("error.jsp");
						return;
					}

					request.setAttribute("order", "order");
					request.setAttribute("sizeId", sizeId);
					request.setAttribute("quantity", quantity);
					request.getRequestDispatcher("order-confirm.jsp").forward(request, response);
				}
			} catch (Exception e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}

		} else if (order.equals("singleCartItem")) {

			int cartItemId = Integer.parseInt(request.getParameter("cartItemId"));
			int productId = Integer.parseInt(request.getParameter("productId"));
			int quantity = Integer.parseInt(request.getParameter("quantity")); //購入数を取得
			int sizeId = Integer.parseInt(request.getParameter("sizeId")); //sizeを取得

			try {
				if (st.productStock(productId, sizeId, quantity)) { //もし在庫がたりたら	

					st.decrementStock(productId, sizeId, quantity); //在庫 - 購入数
					st.orderRegistration(productId, sizeId, quantity, userId, totalAmount, delivery_address); //order_itemsに追加（INSERT）
					//カートから削除
					try {
						cartDao.destroyCartItem(cartItemId); //cartから削除
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
						request.getSession().setAttribute("errorMessage", "内部の設定エラーが発生しました。"
								+ "お問い合わせよ管理者に連絡して、解決の支援を受けてください。");
						response.sendRedirect("error.jsp");
						return;
					} catch (SQLException e) {
						e.printStackTrace();
						request.getSession().setAttribute("errorMessage", "現在データベースにアクセスできません。後ほど再度お試しください"
								+ "。問題が続く場合は、お問い合わせより管理者にご連絡ください。");
						response.sendRedirect("error.jsp");
						return;
					}

					//ご購入いただきましてありがとうございました。に飛ばす
					RequestDispatcher dispatcher = request.getRequestDispatcher("order.jsp");
					dispatcher.forward(request, response);

				} else { // もし在庫がたりなかったら

					request.setAttribute("error_message", "この商品は在庫切れのため現在購入できません");
					request.setAttribute("order", "singleCartItem"); //今回のオーダーがカートの中身のうち一つであることを証明

					try {
						CartItemBean Item = cartDao.getCartItem(cartItemId); //cartItemの情報を受け取る
						request.setAttribute("cartItem", Item); //CartItemBeanのリストでくる
						request.setAttribute("cartItemId", cartItemId);
						request.setAttribute("productList", productDao.detailProductList(productId)); //商品の詳細をjspに渡す
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
						request.getSession().setAttribute("errorMessage", "内部の設定エラーが発生しました。"
								+ "お問い合わせよ管理者に連絡して、解決の支援を受けてください。");
						response.sendRedirect("error.jsp");
						return;
					} catch (SQLException e) {
						e.printStackTrace();
						request.getSession().setAttribute("errorMessage", "現在データベースにアクセスできません。後ほど再度お試しください"
								+ "。問題が続く場合は、お問い合わせより管理者にご連絡ください。");
						response.sendRedirect("error.jsp");
						return;
					}

					sizeId = Integer.parseInt(request.getParameter("sizeId")); //もう一度サイズ渡す
					quantity = Integer.parseInt(request.getParameter("quantity")); //もう数量渡す
					request.getRequestDispatcher("order-confirm.jsp").forward(request, response);
				}
			} catch (Exception e) {
				e.printStackTrace();
				request.getSession().setAttribute("errorMessage", "申し訳ありませんが、システムエラーが発生しました。"
						+ "もう一度お試しいただくか、お問い合わせより管理者にお問い合わせください。");
				response.sendRedirect("error.jsp");
				return;
			}

		} else if (order.equals("allCartItems")) {

			boolean stock = true;
			//カート全購入
			try {
				stock = productDao.cartProductStock(userId);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				request.getSession().setAttribute("errorMessage", "内部の設定エラーが発生しました。"
						+ "お問い合わせよ管理者に連絡して、解決の支援を受けてください。");
				response.sendRedirect("error.jsp");
				return;
			} catch (SQLException e) {
				e.printStackTrace();
				request.getSession().setAttribute("errorMessage", "現在データベースにアクセスできません。後ほど再度お試しください"
						+ "。問題が続く場合は、お問い合わせより管理者にご連絡ください。");
				response.sendRedirect("error.jsp");
				return;
			}

			if (stock) { //もし在庫が揃ってたら

				//在庫減らす
				try {
					st.cartDecrementStock(userId);
					st.cartRegistration(userId, delivery_address);
				} catch (Exception e) {
					e.printStackTrace();
					request.getSession().setAttribute("errorMessage", "申し訳ありませんが、システムエラーが発生しました。"
							+ "もう一度お試しいただくか、お問い合わせより管理者にお問い合わせください。");
					response.sendRedirect("error.jsp");
					return;
				}

				//カートから削除
				try {
					cartDao.destroyAllCartItem(userId); //cartから削除
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
					request.getSession().setAttribute("errorMessage", "内部の設定エラーが発生しました。"
							+ "お問い合わせよ管理者に連絡して、解決の支援を受けてください。");
					response.sendRedirect("error.jsp");
					return;
				} catch (SQLException e) {
					e.printStackTrace();
					request.getSession().setAttribute("errorMessage", "現在データベースにアクセスできません。後ほど再度お試しください"
							+ "。問題が続く場合は、お問い合わせより管理者にご連絡ください。");
					response.sendRedirect("error.jsp");
					return;
				}

				//ご購入ありがとうございました
				request.getRequestDispatcher("order.jsp").forward(request, response);

			} else { //在庫がない商品があれば

				try {
					request.setAttribute("cartAllItemList", cartDao.getCartItems(userId));
					request.setAttribute("error_message", "在庫切れの商品が含まれているため購入できません");
					request.setAttribute("order", "allCartItems"); //今回のオーダーがカートの中身のうち一つであることを証明
					request.getRequestDispatcher("order-confirm.jsp").forward(request, response);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
					request.getSession().setAttribute("errorMessage", "内部の設定エラーが発生しました。"
							+ "お問い合わせよ管理者に連絡して、解決の支援を受けてください。");
					response.sendRedirect("error.jsp");
					return;
				} catch (SQLException e) {
					e.printStackTrace();
					request.getSession().setAttribute("errorMessage", "現在データベースにアクセスできません。後ほど再度お試しください"
							+ "。問題が続く場合は、お問い合わせより管理者にご連絡ください。");
					response.sendRedirect("error.jsp");
					return;
				}
			}
		}

	}
}
