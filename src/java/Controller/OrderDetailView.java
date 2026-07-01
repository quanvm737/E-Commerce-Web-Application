package Controller;

import java.io.IOException;
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
import model.OrderDetails;
import model.Orders;
import model.dao.OrdersDAO;

/**
 *
 * @author wuann_
 */
@WebServlet(name = "OrderDetailView", urlPatterns = {"/OrderDetailView"})
public class OrderDetailView extends HttpServlet {

    private final String ORDER_DETAIL_PAGE = "/private/OrderDetailView.jsp";
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
            return;
        }
        if (x.getRoleInSystem() == 3) {
            response.sendRedirect(ERROR_PAGE);
            return;
        }

        String orderIdStr = request.getParameter("orderId");
        if (orderIdStr == null || orderIdStr.trim().isEmpty()) {
            response.sendRedirect("listOrder");
            return;
        }

        try {
            int orderId = Integer.parseInt(orderIdStr);
            OrdersDAO dao = new OrdersDAO();
            Orders order = dao.findOrderWithDetails(orderId);

            if (order == null) {
                response.sendRedirect(ERROR_PAGE);
                return;
            }

            request.setAttribute("orderDetail", order);

            RequestDispatcher rd = request.getRequestDispatcher(ORDER_DETAIL_PAGE);
            rd.forward(request, response);

        } catch (NumberFormatException e) {
            Logger.getLogger(OrderDetailView.class.getName()).log(Level.SEVERE, null, e);
            response.sendRedirect(ERROR_PAGE);
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
        doGet(request, response);
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
