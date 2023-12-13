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

@WebServlet("/OrderConfilmServlet")
public class OrderConfilmServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		
		ProductDAO productDao = new ProductDAO();
		UserDAO userDao = new UserDAO();
		CartDAO cartDao = new CartDAO();
		int userId = (int) request.getSession().getAttribute("userId");
		
		// カート内全てか商品一つか　と　カートからか今すぐ購入か()
		String order = request.getParameter("order");
		String cartItem = request.getParameter("cartitem");
		
		try {
			System.out.print("-------------------------");	
			System.out.print(userDao.getUpdateUser(userId));		
			//userIdでUserBeanから情報を持ってくる
			request.setAttribute("user" , userDao.getUpdateUser(userId));				//user情報
			request.setAttribute("address" , userDao.getUserAddressId(userId));			//メイン住所
			request.setAttribute("addAddresses" , userDao.getSubAddress(userId));	    //サブ住所
			
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		if(order != null ) { //商品詳細 → 今すぐ購入の場合
			
			try {
				//productIdで商品の情報をとってくる
				int productId = Integer.parseInt(request.getParameter("productId"));
				int sizeId = Integer.parseInt(request.getParameter("sizeId"));
				int quantity = Integer.parseInt(request.getParameter("quantity"));
				request.setAttribute("productList", productDao.detailProductList(productId)); //商品の詳細情報　商品名やらお金やら
				request.setAttribute("order", "order");
				request.setAttribute("sizeId", sizeId);
				request.setAttribute("quantity", quantity);
			} catch (SQLException | ClassNotFoundException e){
				e.printStackTrace();
			}
			
		} else {
			
			if(cartItem != null) { //カートの中のどれか一つの場合
				//setAttributeでカートの単品であることを情報として持っていく
				request.setAttribute("order", "cartItem");
				int cartItemId = Integer.parseInt(request.getParameter("cartItemId")); //カートページから単品のカートアイテムIDを受け取る
				try {
					
				CartItemBean Item = cartDao.getCartItem(cartItemId); //cartItemの情報を受け取る
				request.setAttribute( "cartItem" , Item );
				int productId = Item.getProductId(); //cartItemのproductIdを受け取る
				request.setAttribute("productList", productDao.detailProductList(productId));  //商品の詳細をjspに渡す

						
			
				request.setAttribute("productList", cartDao.getCartItem(cartItemId));
				} catch (SQLException | ClassNotFoundException e) {
					e.printStackTrace();
				}
				
			} else {
				
				try {
				request.setAttribute("cartAllItemList", cartDao.getCartItems(userId));
				} catch ( SQLException | ClassNotFoundException e) {
					e.printStackTrace();
				}
				request.setAttribute("order", "allCartItems");
				
			}
		}
		// 一つの時はproductIdとuserIdでcartitemsからとってくる
		
		request.getRequestDispatcher("order-confirm.jsp").forward(request, response);

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		ProductDAO productDao = new ProductDAO();
		UserDAO userDao = new UserDAO();
		CartDAO cartDAO = new CartDAO();
		
	
		int userId = (int) request.getSession().getAttribute("userId");
		
		// カート内全てか商品一つか　と　カートからか今すぐ購入か()
		String order = request.getParameter("order");
	
		
		try {
			//userIdでUserBeanから情報を持ってくる
			request.setAttribute("user" , userDao.getUpdateUser(userId));				//user情報
			request.setAttribute("address" , userDao.getUserAddressId(userId));			//メイン住所
			request.setAttribute("addAddresses" , userDao.getSubAddress(userId));	//サブ住所
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	
		String delivery_address = (String)request.getParameter("address");
		int productId = Integer.parseInt(request.getParameter("productId"));
		int totalAmount = Integer.parseInt(request.getParameter("totalAmount"));
		System.out.println("住所は" +  delivery_address);

		
		request.setAttribute("order" , order);
		ProductDAO productDAO = new ProductDAO();
		StockManager st = new StockManager();
		
		//productIdと在庫を照らし合わせ、足りなかったら「在庫がない商品があるので購入いただけません」で確認画面に戻す
		//productIdとsizeのん
		//しかもカート内のうち一つだけのパターンもある。。。
		if ( order.equals("cart")) {
			try {
				if(productDAO.cartProductStock(userId)) {
					
					//カート内一括購入のロジック
					//orderに追加
					//在庫を減らす
					
				} else {
					
					try {
						request.setAttribute("cartItemList", cartDAO.cartAllItem(request));
						} catch ( SQLException | ClassNotFoundException e) {
							e.printStackTrace();
						}
	
					request.setAttribute("order", "cart");
					request.setAttribute("message", "在庫切れの商品があるため購入できません");			
					int sizeId = Integer.parseInt(request.getParameter("sizeId"));
					int quantity = Integer.parseInt(request.getParameter("quantity"));
					request.setAttribute("productList", productDao.detailProductList(productId)); //商品の詳細情報　商品名やらお金やら
					request.setAttribute("sizeId", sizeId);
					request.setAttribute("quantity", quantity);
					request.getRequestDispatcher("order-confilm.jsp").forward(request, response);
					
				}
			} catch (SQLException | ClassNotFoundException e){
			e.printStackTrace();
			}
			
		} else if ( order.equals("cartItem"))  { //カートの中のどれか一つ
			
			int quantity = Integer.parseInt(request.getParameter("quantity"));
			int sizeId = Integer.parseInt(request.getParameter("sizeId"));
			if( st.productStock(productId,sizeId,quantity)) {
				
				//カート内一括購入のロジック
				//orderに追加
				//在庫を減らす
				
			} else {
				
				//購入アイテムのデータ
				request.setAttribute("order", "cartItem");
				request.setAttribute("message", "在庫切れの商品があるため購入できません");
				request.getRequestDispatcher("order-confilm.jsp").forward(request, response);
				
			}
			
		} else if ( order.equals("order")) { //商品詳細 → 今すぐ購入
			//System.out.println("オーダーの分岐には入ってます");
			
			int quantity = Integer.parseInt(request.getParameter("quantity"));
			int sizeId = Integer.parseInt(request.getParameter("sizeId"));
			
			if( st.productStock(productId,sizeId,quantity)) {
				System.out.println(delivery_address);				
				st.decrementStock(productId,sizeId,quantity );
				st.orderRegistration(productId,sizeId,quantity, userId ,totalAmount , delivery_address );
				
				//ご購入いただきましてありがとうございました。に飛ばす
				RequestDispatcher dispatcher = request.getRequestDispatcher("order.jsp");
		  	    dispatcher.forward(request, response);
			
			
			} else { // 在庫 < 購入数だったら
				
				
				request.setAttribute("message", "在庫切れの商品があるため購入できません");			
				sizeId = Integer.parseInt(request.getParameter("sizeId"));
				quantity = Integer.parseInt(request.getParameter("quantity"));
				try {
				request.setAttribute("productList", productDao.detailProductList(productId)); //商品の詳細情報　商品名やらお金やら
				} catch (SQLException | ClassNotFoundException e) {
					e.printStackTrace();
				}
				request.setAttribute("order", "order");
				request.setAttribute("sizeId", sizeId);
				request.setAttribute("quantity", quantity);
				
				request.setAttribute("message", "在庫切れの商品があるため購入できません");
				request.getRequestDispatcher("order-confirm.jsp").forward(request, response);
				
			}
			
			
		}
		
		//orderなのかcartitemなのかcartなのかでcartからのDERETEとorderへのINSERTを行う
		
		
	}
	
	
}

