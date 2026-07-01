/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
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
import model.dao.AccountsDAO;
import model.dao.exceptions.NonexistentEntityException;

/**
 *
 * @author wuann_
 */
@WebServlet(name = "AccountUpdate", urlPatterns = {"/AccountUpdate"})
public class AccountUpdate extends HttpServlet {

    private final String ACCOUNT_PAGE = "listAccount";
    private final String UPDATE_ACCOUNT_PAGE = "/private/UpdateAccount.jsp";
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
            response.sendRedirect(ERROR_PAGE);
            return;
        } else {
            String account = request.getParameter("accountName");

            AccountsDAO dao = new AccountsDAO();
            Accounts tempAccount = dao.findAccounts(account);

            request.setAttribute("updateInfo", tempAccount);

            RequestDispatcher rd = request.getRequestDispatcher(UPDATE_ACCOUNT_PAGE);
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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(false);
        Accounts x = (session == null) ? null : (Accounts) session.getAttribute("loginInfo");
        String url = "";
        if (x == null) {
            response.sendRedirect("MainController?btnAction=Login");
        }
        else if (x.getRoleInSystem() == 3){
            response.sendRedirect(ERROR_PAGE);
            return;
        }else {
            String account = request.getParameter("accountName");
            String pass = request.getParameter("accountPass");
            String firstName = request.getParameter("accountFirstName");
            String lastName = request.getParameter("accountLastName");
            Date birthday = Date.valueOf(request.getParameter("accountBirthday"));
            boolean gender = Boolean.parseBoolean(request.getParameter("accountGender"));
            String phone = request.getParameter("accountPhone");
            boolean isUse = request.getParameter("accountIsUse") != null;

            int role = Integer.parseInt(request.getParameter("accountRoleInSystem"));

            Accounts tempAccount = new Accounts(account, pass, lastName, firstName, birthday, gender, phone, isUse, role);

            AccountsDAO dao = new AccountsDAO();
            try {
                dao.edit(tempAccount);
                url = ACCOUNT_PAGE;
            } catch (NonexistentEntityException ex) {
                url = ERROR_PAGE;
                Logger.getLogger(AccountUpdate.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                url = ERROR_PAGE;
                Logger.getLogger(AccountUpdate.class.getName()).log(Level.SEVERE, null, ex);
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
