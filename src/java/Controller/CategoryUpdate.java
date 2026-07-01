/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Accounts;
import model.Categories;
import model.dao.CategoriesDAO;
import model.dao.exceptions.NonexistentEntityException;

/**
 *
 * @author wuann_
 */
@WebServlet(name = "CategoryUpdate", urlPatterns = {"/CategoryUpdate"})
public class CategoryUpdate extends HttpServlet {

    private final String CATEGORY_PAGE = "listCategory";
    private final String UPDATE_CATEGORY_PAGE = "/private/UpdateCategory.jsp";
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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(false);
        Accounts x = (session == null) ? null : (Accounts) session.getAttribute("loginInfo");
        if (x == null) {
            response.sendRedirect("MainController?btnAction=Login");
            return;
        }else if (x.getRoleInSystem() == 3){
            response.sendRedirect("Error.jsp");
            return;
        } else {
            int typeId = Integer.parseInt(request.getParameter("typeid"));

            CategoriesDAO dao = new CategoriesDAO();
            Categories tempCategory = dao.findCategories(typeId);

            request.setAttribute("updateInfo", tempCategory);

            RequestDispatcher rd = request.getRequestDispatcher(UPDATE_CATEGORY_PAGE);
            rd.forward(request, response);
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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(false);
        Accounts x = (session == null) ? null : (Accounts) session.getAttribute("loginInfo");
        String url = "";
        if (x == null) {
            response.sendRedirect("MainController?btnAction=Login");
        } else {
            CategoriesDAO dao = new CategoriesDAO();
            int typeId = Integer.parseInt(request.getParameter("typeid"));
            String categoryName = request.getParameter("categoryName");
            String memo = request.getParameter("categoryMemo");

            Categories tempCategory = new Categories(typeId, categoryName, memo);
            
            try {
                dao.edit(tempCategory);
                url = CATEGORY_PAGE;
            } catch (NonexistentEntityException ex) {
                url = ERROR_PAGE;
                Logger.getLogger(CategoryUpdate.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                url = ERROR_PAGE;
                Logger.getLogger(CategoryUpdate.class.getName()).log(Level.SEVERE, null, ex);
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
