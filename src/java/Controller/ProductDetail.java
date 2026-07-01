/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Products;
import model.dao.ProductsDAO;

/**
 *
 * @author wuann_
 */
@WebServlet(name = "ProductDetail", urlPatterns = {"/detailProduct"})
public class ProductDetail extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String productId = request.getParameter("productId");
        Products product = new ProductsDAO().findProducts(productId);
        if (product == null) {
            response.sendRedirect("MainController?btnAction=index");
            return;
        }
        Cookie[] listCookies = request.getCookies();
        String viewedProductString = createViewedProductString(listCookies, productId);

        Cookie ck = new Cookie("viewedProduct", viewedProductString);
        ck.setMaxAge(30 * 24 * 60 * 60);
//        30 * 24 * 60 * 60
        ck.setPath(request.getContextPath());
        response.addCookie(ck);

        List<Products> viewedProductList = convertToViewedProductList(viewedProductString);

        request.setAttribute("viewedProductList", viewedProductList);
        request.setAttribute("product", product);

        RequestDispatcher rd = request.getRequestDispatcher("ProductDetails.jsp");
        rd.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private String createViewedProductString(Cookie[] listCookies, String productId) {
        ProductsDAO dao = new ProductsDAO();
        String result = "";
        if (listCookies != null) {
            for (Cookie x : listCookies) {
                if (x.getName().equalsIgnoreCase("viewedProduct")) {
                    result = x.getValue();
                    break;
                }
            }
        }

        if (result.isEmpty()) {
            return productId;
        }

        String[] arr = result.split("-");
        List<Products> temp = new ArrayList<>();

        for (String i : arr) {
            Products x = dao.findProducts(i);
            if (x != null) {
                temp.add(x);
            }
        }

        for (Products p : temp) {
            if (p.getProductId().equals(productId)) {
                return result;
            }
        }

        if (temp.size() >= 5) {
            temp.remove(temp.size() - 1);
            result = "";
            for (Products i : temp) {
                if (!result.isEmpty()) {
                    result += "-";
                }
                result += i.getProductId();
            }
        }

        result = productId + "-" + result;
        return result;
    }

    private List<Products> convertToViewedProductList(String viewedProductString) {
        List<Products> result = new ArrayList<>();
        ProductsDAO dao = new ProductsDAO();

        if (viewedProductString == null || viewedProductString.trim().isEmpty()) {
            return result;
        }

        String[] arr = viewedProductString.split("-");

        for (String i : arr) {
            Products x = dao.findProducts(i);
            if (x != null) {
                result.add(x);
            }
        }
        return result;
    }

}
