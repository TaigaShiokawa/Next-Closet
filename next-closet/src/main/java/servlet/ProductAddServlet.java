package servlet;

import java.io.IOException;
import java.nio.file.Paths;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.dao.AdminProductDAO;

@WebServlet("/ProductAddServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB 
maxFileSize = 1024 * 1024 * 10,      // 10MB 1アイテムの最大サイズ
maxRequestSize = 1024 * 1024 * 50)   // 50MB 1回のリクエストで送信できる合計サイズ
public class ProductAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
//	private static final String UPLOAD_DIRECTORY = "image";
	// 保存するディレクトリ
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		response.sendRedirect("product-add.jsp");
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
        
        // アップロードファイルを保存するパスを取得
//        String appPath = request.getServletContext().getRealPath("");
//        String savePath =  UPLOAD_DIRECTORY;

        // ディレクトリが存在しない場合は作成
//        File fileSaveDir = new File(savePath);
//        if (!fileSaveDir.exists()) {
//            fileSaveDir.mkdir();
//        }

        // ファイル名を取得し、サーバーに保存
        String fileName = "";
        Part filePart = request.getPart("image"); // inputタグのname="image"から取得
        if (filePart != null) {
            // ファイル名のみを取得する
            fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            if (fileName != null && !fileName.isEmpty()) {
                filePart.write(fileName);
            }
        }
        
        // imagePathは保存されたファイルのパス
        String imagePath = fileName;
		
		//各種パラメータ取得
		String pName = request.getParameter("productName");
		String description = request.getParameter("description");
		String categoryName = request.getParameter("category");
		int gender = Integer.parseInt(request.getParameter("gender"));
		int price = Integer.parseInt(request.getParameter("price"));
		int sSizeInventory = Integer.parseInt(request.getParameter("s_size_inventory"));
		int mSizeInventory = Integer.parseInt(request.getParameter("m_size_inventory"));
		int lSizeInventory = Integer.parseInt(request.getParameter("l_size_inventory"));
		
		if(pName.length() > 65) {
			request.setAttribute("productNameError", "商品名が長すぎます。65文字以内でお願いします。");
			request.getRequestDispatcher("product-add.jsp").forward(request, response);
			return;
		}
		
		if(description.length() > 1000) {
			request.setAttribute("descriptionError", "商品説明が長すぎます。1000文字以内でお願いします。");
			request.getRequestDispatcher("product-add.jsp").forward(request, response);
			return;
		}
		
		
		AdminProductDAO aDao = new AdminProductDAO();
		try {
			int sSizeId = aDao.getSizeId("S");
			int mSizeId = aDao.getSizeId("M");
			int lSizeId = aDao.getSizeId("L");
			try {
				//カテゴリーIDの取得
				int categoryId = aDao.getCategoryId(categoryName);
			    if(categoryId > 0) {
			        // 新規商品を追加
			        int productId = aDao.addProduct(categoryId, gender, pName, description, price, imagePath);
			        if(productId > 0) {
			            // Sサイズの在庫を挿入
			            int insertInventoryS = aDao.setProductInventory(productId, sSizeId, sSizeInventory);
			            // Mサイズの在庫を挿入
			            int insertInventoryM = aDao.setProductInventory(productId, mSizeId, mSizeInventory);
			            // Lサイズの在庫を挿入
			            int insertInventoryL = aDao.setProductInventory(productId, lSizeId, lSizeInventory);
			            
			            if (insertInventoryS == 1 && insertInventoryM == 1 && insertInventoryL == 1) {
			                // 在庫挿入が成功した場合の処理
			                response.sendRedirect("product-add.jsp"); 
			            } else {
			                // 在庫挿入が失敗した場合の処理
			            	request.getSession().setAttribute("productError", "商品の登録に失敗しました。");
			                response.sendRedirect("errorToAdmin.jsp");
			            }
			        } else {
			            // 商品挿入が失敗した場合の処理
			        	request.getSession().setAttribute("productError", "商品の登録に失敗しました。");
			            response.sendRedirect("errorToAdmin.jsp"); 
			        }
			    }
			} catch(ClassNotFoundException e) {
				e.printStackTrace();
				request.getSession().setAttribute("errorMessageToAdmin", "内部の設定エラーが発生しました。"
						+ "早急に対応してください。");
		        response.sendRedirect("errorToAdmin.jsp");
		        return;
			} catch (SQLException e) {
				e.printStackTrace();
				request.getSession().setAttribute("errorMessageToAdmin", "データベースにアクセスできません。"
						+ "早急に対応してください。");
				response.sendRedirect("errorToAdmin.jsp");
				return;
			}
		
		} catch(Exception e) {
			  e.printStackTrace();
			  request.getSession().setAttribute("errorMessageToAdmin", "システムエラーが発生しました。早急に対応してください。");
			  response.sendRedirect("errorToAdmin.jsp");
			  return;
		  }
	}
}