/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Categories;
import model.Products;
import model.dao.CategoriesDAO;
import model.dao.ProductsDAO;

/**
 *
 * @author wuann_
 */
@WebServlet(name = "indexController", urlPatterns = {"/index"})
public class indexController extends HttpServlet {

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
        ProductsDAO dao = new ProductsDAO();
        CategoriesDAO categoriesDAO = new CategoriesDAO();

        if (request.getAttribute("listProduct") == null) {
            List<Products> listProduct = dao.listAll();
            request.setAttribute("listProduct", listProduct);
        }

        Cookie[] listCookies = request.getCookies();
        String viewedProductString = getViewedProductString(listCookies);

        List<Products> viewedProductList = convertToViewedProductList(viewedProductString);

        request.setAttribute("viewedProductList", viewedProductList);

        List<Categories> listCategory = categoriesDAO.listAll();
        request.setAttribute("listCategory", listCategory);
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
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

    private String getViewedProductString(Cookie[] listCookies) {
        if (listCookies != null) {
            for (Cookie x : listCookies) {
                if (x.getName().equalsIgnoreCase("viewedProduct")) {
                    return x.getValue();
                }
            }
        }
        return "";
    }

    private List<Products> convertToViewedProductList(String viewedProductString) {
        List<Products> result = new ArrayList<>();
        ProductsDAO dao = new ProductsDAO();

        if (viewedProductString == null || viewedProductString.trim().isEmpty()) {
            return result;
        }

        for (String i : viewedProductString.split("-")) {
            Products x = dao.findProducts(i);
            if (x != null) {
                result.add(x);
            }
        }
        return result;
    }

}
