package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.ProductBean;
import model.dao.ProductDAO;
import model.dao.SearchDAO;

@WebServlet("/ProductListServlet")
public class ProductListServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int categoryId = -1;
        int gender = 0;
        String categoryIdStr = request.getParameter("categoryId");
        String genderStr =  request.getParameter("gender");
        String searchName = request.getParameter("searchName");

        if (categoryIdStr != null) {
            categoryId = Integer.parseInt(categoryIdStr);
        }

        if (genderStr != null) {
            gender = Integer.parseInt(genderStr);
        }

        String categoryName = request.getParameter("categoryName");
        ProductDAO dao = new ProductDAO();

        // ページネーション関連
        final int pageSize = 16; // 1ページあたりのアイテム数
        int currentPage = 1; // 現在のページ番号（デフォルトは1）
        String pageStr = request.getParameter("page");
        if (pageStr != null && !pageStr.isEmpty()) {
            currentPage = Integer.parseInt(pageStr);
        }

        try {
            request.setAttribute("categoryList", dao.categoryList());
            int totalItems = dao.getTotalProductCount(); // 総製品数を取得
            int totalPages = (int) Math.ceil((double) totalItems / pageSize); // 総ページ数を計算

            if (searchName != null && !searchName.isEmpty()) {
                // 検索機能
                SearchDAO searchDao = new SearchDAO();
                List<ProductBean> searchProducts = searchDao.searchProductList(searchName);
                request.setAttribute("searchName", searchName);
                request.setAttribute("searchProducts", searchProducts);
                request.setAttribute("title", searchName + "の検索結果");
            } else {
                // 通常の商品リスト表示
                List<ProductBean> products = dao.getProductListByPage(currentPage, pageSize); // ページネーション対応の製品リスト取得
                request.setAttribute("productList", products);
                if (categoryId == -1) {
                    request.setAttribute("title", "ALL / 商品一覧");
                    request.setAttribute("productList",dao.allProductList());
                } else {
                    if (gender == -1) {
                        request.setAttribute("title", "ALL / " + categoryName + "/ 商品一覧");
                        request.setAttribute("productList",dao.allCategoryProductList(categoryId));
                    } else {
                        request.setAttribute("title", genderStr + "/" + categoryName + "/ 商品一覧");
                        request.setAttribute("productList",dao.categoryProductList(categoryId , gender));
                    }
                }
            }

            request.setAttribute("currentPage", currentPage);
            request.setAttribute("totalPages", totalPages);

        } catch (NumberFormatException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            request.getSession().setAttribute("errorMessage", "エラーが発生しました。");
            response.sendRedirect("error.jsp");
            return;
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("product-list.jsp");
        dispatcher.forward(request, response);
    }
}
