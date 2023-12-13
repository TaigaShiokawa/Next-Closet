package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.StockManager;
import model.dao.CartDAO;
import model.dao.ProductDAO;
import model.dao.UserDAO;

@WebServlet("/OrderConfilmServlet")
public class OrderConfilmServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ProductDAO productDao = new ProductDAO();
		UserDAO userDao = new UserDAO();
		CartDAO cartDAO = new CartDAO();
		
		int userId = (int) request.getSession().getAttribute("user_id");
		
		// カート内全てか商品一つか　と　カートからか今すぐ購入か()
		String order = request.getParameter("order");
		String cartitem = request.getParameter("cartitem");
		
		try {
			//userIdでUserBeanから情報を持ってくる
			request.setAttribute("user" , userDao.getUpdateUser(userId));				//user情報
			request.setAttribute("address" , userDao.getUserAddressId(userId));			//メイン住所
			request.setAttribute("addAddresses" , userDao.getUserAddAddress(userId));	//サブ住所
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
		if(order != null ) { //商品詳細 → 今すぐ購入の場合
			
			try {
				//productIdで商品の情報をとってくる
				int productId = Integer.parseInt(request.getParameter("productId"));
				int sizeId = Integer.parseInt(request.getParameter("sizeId"));
				int quantity = Integer.parseInt(request.getParameter("quantity"));
				request.setAttribute("productList", productDao.detailProductList(productId));
				request.setAttribute("order", "order");
				request.setAttribute("sizeId", sizeId);
				request.setAttribute("quantity", quantity);
			} catch (SQLException | ClassNotFoundException e){
				e.printStackTrace();
			}
		
			int sizeId   = Integer.parseInt(request.getParameter("sizeId"));
			request.setAttribute("sizeId",sizeId);
			
			int quantity =  Integer.parseInt(request.getParameter("quantity"));
			request.setAttribute("quantity",quantity);
			
		} else {
			
			if(cartitem != null) { //カートの中のどれか一つの場合
				//setAttributeでカートの単品であることを情報として持っていく
				request.setAttribute("order", "cartItem");
				int cartItemId = Integer.parseInt(request.getParameter("cartItemId"));
				//userIdからカート内アイテムをリストにして持ってくる(CartBeansで！)
				int productId = Integer.parseInt(request.getParameter("productId"));
				
				
				try {
					//木村さんと連携をとって進める
				request.setAttribute("productList", cartDAO.cartItem(cartItemId));
				} catch (SQLException | ClassNotFoundException e) {
					e.printStackTrace();
				}
				
			} else {
				//userIdからカート内アイテムをリストにして持ってくる(CartBeansで！)
				//木村さんと連携をとって進める
				
				try {
				request.setAttribute("cartItemList", cartDAO.cartAllItem(request));
				} catch ( SQLException | ClassNotFoundException e) {
					e.printStackTrace();
				}
				request.setAttribute("order", "cart");
				
			}
		}
		// 一つの時はproductIdとuserIdでcartitemsからとってくる
		
		
		request.getRequestDispatcher("order-confilm.jsp").forward(request, response);

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ProductDAO productDao = new ProductDAO();
		UserDAO userDao = new UserDAO();
		CartDAO cartDAO = new CartDAO();
		
		String order     = request.getParameter("order");
		String productId = request.getParameter("productId");
		HttpSession session = request.getSession(false);
		int userId = (int)session.getAttribute("userId"); 
		boolean stock = true;
		
		request.setAttribute("order" , order);
		ProductDAO productDAO = new ProductDAO();
		
		//productIdと在庫を照らし合わせ、足りなかったら「在庫がない商品があルので購入いただけません」で確認画面に戻す
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
					request.getRequestDispatcher("order-confilm.jsp").forward(request, response);
					
				}
			} catch (SQLException | ClassNotFoundException e){
			e.printStackTrace();
			}
		} else if ( order.equals("cartItem"))  {
			if( StockManager.productStock()) {
				
				//カート内一括購入のロジック
				//orderに追加
				//在庫を減らす
				
			} else {
				//購入アイテムのデータ
				request.setAttribute("order", "cartItem");
				request.setAttribute("message", "在庫切れの商品があるため購入できません");
				request.getRequestDispatcher("order-confilm.jsp").forward(request, response);
				
			}
			
		} else if ( order.equals("order")) {
			
		}
		
		//orderなのかcartitemなのかcartなのかでcartからのDERETEとorderへのINSERTを行う
		
		//ご購入いただきましてありがとうございました。に飛ばす
		RequestDispatcher dispatcher = request.getRequestDispatcher("order.jsp");
  	    dispatcher.forward(request, response);
	}
	
	
}


//listに入れるメソッド（あとでmodelにうつす）