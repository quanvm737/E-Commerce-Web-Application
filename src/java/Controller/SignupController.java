/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import java.io.IOException;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Accounts;
import model.dao.AccountsDAO;

/**
 *
 * @author wuann_
 */
@WebServlet(name = "SignupController", urlPatterns = {"/signup"})
public class SignupController extends HttpServlet {

    private final String SIGN_UP_PAGE = "Signup.jsp";
    private final String LOGIN_PAGE = "login";
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
        request.getRequestDispatcher(SIGN_UP_PAGE).forward(request, response);
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
        AccountsDAO dao = new AccountsDAO();
        String url = "";
        String account = request.getParameter("accountName");
        if (dao.findAccounts(account) == null) {
            String pass = request.getParameter("accountPass");
            String firstName = request.getParameter("accountFirstName");
            String lastName = request.getParameter("accountLastName");
            Date birthday = Date.valueOf(request.getParameter("accountBirthday"));
            boolean gender = Boolean.parseBoolean(request.getParameter("accountGender"));
            String phone = request.getParameter("accountPhone");
            boolean isUse = request.getParameter("accountIsUse") == null ? false : true;

            int role = Integer.parseInt(request.getParameter("accountRoleInSystem"));

            Accounts tempAccount = new Accounts(account, pass, lastName, firstName, birthday, gender, phone, isUse, role);

            try {
                dao.create(tempAccount);
                url = LOGIN_PAGE;
            } catch (Exception ex) {
                url = ERROR_PAGE;
                Logger.getLogger(AccountAdd.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.getRequestDispatcher(url).forward(request, response);

        } else {
            String errMess = "ACCOUNT NAME HAS BEEN OCCUPIED!! TRY ANOTHER..";
            request.setAttribute("err", errMess);
            request.getRequestDispatcher(SIGN_UP_PAGE).forward(request, response);
            return;
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
