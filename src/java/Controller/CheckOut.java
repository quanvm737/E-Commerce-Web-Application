/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Accounts;
import model.CartShop;
import model.OrderDetails;
import model.OrderDetailsPK;
import model.Orders;
import model.dao.OrdersDAO;

/**
 *
 * @author wuann_
 */
@WebServlet(name = "CheckOut", urlPatterns = {"/checkOut"})
public class CheckOut extends HttpServlet {

    private final String LOGIN_CONTROLLER = "login";

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
        request.getRequestDispatcher("/private/CheckOut.jsp").forward(request, response);
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
        OrdersDAO dao = new OrdersDAO();

        CartShop cart = (session == null) ? null : (CartShop) session.getAttribute("cart");
        Accounts x = (session == null) ? null : (Accounts) session.getAttribute("loginInfo");
        if (x == null) {
            response.sendRedirect("MainController?btnAction=Login");
            return;
        }
        if (cart == null || cart.getItems() == null || cart.getItems().isEmpty()) {
            response.sendRedirect("MainController?btnAction=viewCart");
            return;
        }

        String fullName = request.getParameter("fullName");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String paymentMethod = request.getParameter("paymentMethod");
        Integer totalValue = cart.caculateTotalPrice();

        if (paymentMethod == null || !"COD".equalsIgnoreCase(paymentMethod)) {
            request.setAttribute("error", "Only COD is supported at the moment.");
            request.getRequestDispatcher("/private/CheckOut.jsp").forward(request, response);
            return;
        }

        List<OrderDetails> details = new ArrayList<>(cart.getItems().values());

        Orders orders = new Orders(null, fullName, address, phone, new Date(), totalValue, "PENDING", paymentMethod, details);

        try {
            dao.create(orders);
            session.setAttribute("cart", new CartShop());
            request.setAttribute("order", orders);
            request.getRequestDispatcher("/private/OrderSuccess.jsp").forward(request, response);
        } catch (Exception ex) {
            request.setAttribute("error", "Failed to place order. Please try again.");
            request.getRequestDispatcher("/private/CheckOut.jsp").forward(request, response);
            Logger.getLogger(CheckOut.class.getName()).log(Level.SEVERE, "CHECK OUT FAILED!!", ex);
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
