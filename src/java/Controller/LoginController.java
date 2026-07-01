/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Accounts;
import model.Accounts;
import model.dao.AccountsDAO;
import model.dao.AccountsDAO;

/**
 *
 * @author wuann_
 */
@WebServlet(name = "LoginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {
//
//    /**
//     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
//     * methods.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        request.setCharacterEncoding("UTF-8");
//        response.setCharacterEncoding("UTF-8");
//
//        String account = request.getParameter("accountName");
//        String password = request.getParameter("accountPass");
//
//        Accounts x = new AccountsDAO().getLogin(account, password);
//        HttpSession session = request.getSession();
//
//        if (x != null && x.getIsUse()) {
//            session.setAttribute("loginInfo", x);
//            request.getRequestDispatcher("MainController?btnAction=index").forward(request, response);
//
//        } else {
//            String errMess = (x == null ? "USER OR PASSWORD IS INCORRECT!! TRY AGAIN...." : "THIS ACCOUNT IS BANNED PLEASE CONTACT ADMINISTRATOR");
//            request.setAttribute("err", errMess);
//            request.getRequestDispatcher("Login.jsp").forward(request, response);
//        }
//    }

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
        request.getRequestDispatcher("Login.jsp").forward(request, response);
        return;
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

        String account = request.getParameter("accountName");
        String password = request.getParameter("accountPass");

        Accounts x = new AccountsDAO().getLogin(account, password);
        HttpSession session = request.getSession();

        if (x != null && x.getIsUse()) {

            List<String> onlineList = (List<String>) getServletContext().getAttribute("onlineList");

            if (onlineList.contains(x.getAccount())) {
                request.setAttribute("err", "THIS ACCOUNT IS ALREADY LOGGED IN ON ANOTHER BROWSER");
                request.getRequestDispatcher("Login.jsp").forward(request, response);
                return;
            }

            session.setAttribute("loginInfo", x);
            onlineList.add(x.getAccount());
            request.getRequestDispatcher("MainController?btnAction=index").forward(request, response);

        } else {
            String errMess = (x == null ? "USER OR PASSWORD IS INCORRECT!! TRY AGAIN...." : "THIS ACCOUNT IS BANNED PLEASE CONTACT ADMINISTRATOR");
            request.setAttribute("err", errMess);
            request.getRequestDispatcher("Login.jsp").forward(request, response);
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
