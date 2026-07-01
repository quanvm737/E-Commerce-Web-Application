package Controller;

import java.io.IOException;
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
import model.Orders;
import model.dao.OrdersDAO;
import model.dao.exceptions.NonexistentEntityException;

/**
 *
 * @author wuann_
 */
@WebServlet(name = "OrderUpdateStatus", urlPatterns = {"/OrderUpdateStatus"})
public class OrderUpdateStatus extends HttpServlet {

    private final String ORDER_LIST = "listOrder";
    private final String UPDATE_ORDER_PAGE = "/private/UpdateOrderStatus.jsp";
    private final String ERROR_PAGE = "Error.jsp";

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     * Shows the update form pre-filled with current order data.
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
            return;
        }
        if (x.getRoleInSystem() == 3) {
            response.sendRedirect(ERROR_PAGE);
            return;
        }

        String orderIdStr = request.getParameter("orderId");
        if (orderIdStr == null || orderIdStr.trim().isEmpty()) {
            response.sendRedirect(ORDER_LIST);
            return;
        }

        try {
            int orderId = Integer.parseInt(orderIdStr);
            OrdersDAO dao = new OrdersDAO();
            Orders order = dao.findOrders(orderId);

            if (order == null) {
                response.sendRedirect(ERROR_PAGE);
                return;
            }

            request.setAttribute("updateOrder", order);

            RequestDispatcher rd = request.getRequestDispatcher(UPDATE_ORDER_PAGE);
            rd.forward(request, response);

        } catch (NumberFormatException e) {
            Logger.getLogger(OrderUpdateStatus.class.getName()).log(Level.SEVERE, null, e);
            response.sendRedirect(ERROR_PAGE);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     * Processes the update form submission.
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
            return;
        }
        if (x.getRoleInSystem() == 3) {
            response.sendRedirect(ERROR_PAGE);
            return;
        }

        String orderIdStr = request.getParameter("orderId");
        String orderState = request.getParameter("orderState");
        String custName = request.getParameter("custName");
        String custPhone = request.getParameter("custPhone");
        String custAddr = request.getParameter("custAddr");

        try {
            int orderId = Integer.parseInt(orderIdStr);
            OrdersDAO dao = new OrdersDAO();
            Orders order = dao.findOrders(orderId);

            if (order == null) {
                response.sendRedirect(ERROR_PAGE);
                return;
            }

            order.setOrderState(orderState);
            order.setCustName(custName);
            order.setCustPhone(custPhone);
            order.setCustAddr(custAddr);

            dao.edit(order);
            url = ORDER_LIST;

        } catch (NonexistentEntityException ex) {
            url = ERROR_PAGE;
            Logger.getLogger(OrderUpdateStatus.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            url = ERROR_PAGE;
            Logger.getLogger(OrderUpdateStatus.class.getName()).log(Level.SEVERE, null, ex);
        }

        response.sendRedirect(url);
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
