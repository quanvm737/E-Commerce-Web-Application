/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Accounts;
import model.Categories;
import model.dao.CategoriesDAO;

/**
 *
 * @author wuann_
 */
@WebServlet(name = "CategoryAdd", urlPatterns = {"/addCategory"})
public class CategoryAdd extends HttpServlet {

    private final String ADD_CATEGORY_PAGE = "/private/AddCategory.jsp";
    private final String CATEGORY_PAGE = "listCategory";
    private final String ERROR_PAGE = "Error.jsp";

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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(false);
        Accounts x = (session == null) ? null : (Accounts) session.getAttribute("loginInfo");
        if (x == null) {
            response.sendRedirect("MainController?btnAction=Login");
        }else if (x.getRoleInSystem() == 3){
            response.sendRedirect("Error.jsp");
            return;
        } else {
            request.getRequestDispatcher(ADD_CATEGORY_PAGE).forward(request, response);
        }
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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(false);
        Accounts x = (session == null) ? null : (Accounts) session.getAttribute("loginInfo");
        String url = "";
        if (x == null) {
            response.sendRedirect("MainController?btnAction=Login");
        } else {
            CategoriesDAO dao = new CategoriesDAO();
            String categoryName = request.getParameter("categoryName");
            String memo = request.getParameter("categoryMemo");

            Categories tempCategory = new Categories(null, categoryName, memo);

            try {
                dao.create(tempCategory);
                url = CATEGORY_PAGE;
            } catch (Exception ex) {
                url = ERROR_PAGE;
                Logger.getLogger(CategoryAdd.class.getName()).log(Level.SEVERE, null, ex);
            }
            response.sendRedirect(url);
        }
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
