package Controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Accounts;
import model.dao.OrdersDAO;
import model.dao.exceptions.IllegalOrphanException;
import model.dao.exceptions.NonexistentEntityException;

/**
 *
 * @author wuann_
 */
@WebServlet(name = "OrderDelete", urlPatterns = {"/OrderDelete"})
public class OrderDelete extends HttpServlet {

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
        if (x == null) {
            response.sendRedirect("MainController?btnAction=Login");
            return;
        }
        if (x.getRoleInSystem() == 3) {
            response.sendRedirect("Error.jsp");
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

            if (dao.findOrders(orderId) == null) {
                response.sendRedirect("Error.jsp");
                return;
            }

            dao.destroy(orderId);
            response.sendRedirect("listOrder");
            return;
        } catch (IllegalOrphanException ex) {
            Logger.getLogger(OrderDelete.class.getName()).log(Level.SEVERE, null, ex);
            response.sendRedirect("Error.jsp");
            return;
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(OrderDelete.class.getName()).log(Level.SEVERE, null, ex);
            response.sendRedirect("Error.jsp");
            return;
        } catch (NumberFormatException ex) {
            Logger.getLogger(OrderDelete.class.getName()).log(Level.SEVERE, null, ex);
            response.sendRedirect("Error.jsp");
            return;
        }
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
