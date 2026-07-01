package Controller;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.util.List;
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

/**
 *
 * @author wuann_
 */
@WebServlet(name = "AccountController", urlPatterns = {"/listAccount"})
public class AccountController extends HttpServlet {

    private final String ACCOUNT_LIST_PAGE = "/private/AccountList.jsp";
    private final String ACTIVATION_ACCOUNT_CONTROLLER = "AccountActivation";
    private final String UPDATE_ACCOUNT_CONTROLLER = "AccountUpdate";
    private final String DELETE_ACCOUNT_CONTROLLER = "AccountDelete";

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

        HttpSession session = request.getSession(false);
        Accounts x = (session == null) ? null : (Accounts) session.getAttribute("loginInfo");
        String url = ACCOUNT_LIST_PAGE;
        String action = request.getParameter("btnAccountAction");

        if (x == null) {
            response.sendRedirect("MainController?btnAction=Login");
            return;
        }
        if (x.getRoleInSystem() == 3){
            response.sendRedirect("Error.jsp");
            return;
        }

        if (action != null) {
            switch (action) {
                case "Activation":
                    url = ACTIVATION_ACCOUNT_CONTROLLER;
                    break;
                case "Update":
                    url = UPDATE_ACCOUNT_CONTROLLER;
                    break;
                case "Delete":
                    url = DELETE_ACCOUNT_CONTROLLER;
                    break;
            }
        } else {
            url = ACCOUNT_LIST_PAGE;
        }

        List<Accounts> list = new AccountsDAO().listAll();
        request.setAttribute("listAccount", list);

        RequestDispatcher rd = request.getRequestDispatcher(url);
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
