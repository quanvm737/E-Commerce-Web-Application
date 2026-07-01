package Controller;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Accounts;
import model.Orders;
import model.dao.OrdersDAO;

/**
 *
 * @author wuann_
 */
@WebServlet(name = "OrderController", urlPatterns = {"/listOrder"})
public class OrderController extends HttpServlet {

    private final String ORDER_LIST_PAGE = "/private/OrderList.jsp";
    private final String UPDATE_ORDER_STATUS_CONTROLLER = "OrderUpdateStatus";
    private final String DELETE_ORDER_CONTROLLER = "OrderDelete";
    private final String VIEW_ORDER_DETAIL_CONTROLLER = "OrderDetailView";

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
        String url = ORDER_LIST_PAGE;
        String action = request.getParameter("btnOrderAction");

        if (x == null) {
            response.sendRedirect("MainController?btnAction=Login");
            return;
        }
        if (x.getRoleInSystem() == 3) {
            response.sendRedirect("Error.jsp");
            return;
        }

        if (action != null) {
            switch (action) {
                case "UpdateStatus":
                    url = UPDATE_ORDER_STATUS_CONTROLLER;
                    break;
                case "Delete":
                    url = DELETE_ORDER_CONTROLLER;
                    break;
                case "ViewDetail":
                    url = VIEW_ORDER_DETAIL_CONTROLLER;
                    break;
            }
        } else {
            url = ORDER_LIST_PAGE;
        }

        List<Orders> list = new OrdersDAO().listAll();
        request.setAttribute("listOrder", list);

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
