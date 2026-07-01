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
@WebServlet(name = "ProductListByCategory", urlPatterns = {"/productListByCategory"})
public class ProductListByCategory extends HttpServlet {

    private final String INDEX_CONTROLLER = "index";

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

        String listByCategoryId = request.getParameter("listByCategoryId");
        if (listByCategoryId == null) {
            response.sendRedirect("MainController?btnAction=index");
            return;
        }
        List<Products> listProduct = dao.listByType(Integer.parseInt(listByCategoryId));
        if (listProduct == null) {
            listProduct = new ArrayList<>();
        }
        request.setAttribute("listProduct", listProduct);

        Categories cat = categoriesDAO.findCategories(Integer.parseInt(listByCategoryId));
        request.setAttribute("nameListCategory", cat.getCategoryName());

        RequestDispatcher rd = request.getRequestDispatcher(INDEX_CONTROLLER);
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

}
