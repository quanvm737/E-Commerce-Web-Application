/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Accounts;

/**
 *
 * @author wuann_
 */
@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    private final String LOGIN_PAGE = "login";
    private final String SIGN_UP_CONTROLLER = "signup";
    private final String INDEX_PAGE = "index";
    private final String LIST_ACCOUNT_CONTROLLER = "listAccount";
    private final String ADD_ACCOUNT_CONTROLLER = "addAccount";
    private final String LIST_CATEGORY_CONTROLLER = "listCategory";
    private final String ADD_CATEGORY_CONTROLLER = "addCategory";
    private final String LIST_PRODUCT_CONTROLLER = "listProduct";
    private final String ADD_PRODUCT_CONTROLLER = "addProduct";
    private final String DETAIL_PRODUCT_CONTROLLER = "detailProduct";
    private final String PRODUCT_LIST_BY_CATEGORY = "productListByCategory";
    private final String SEARCH_PRODUCT = "searchProduct";
    private final String ADD_TO_CART_CONTROLLER = "addToCart";
    private final String VIEW_CART_CONTROLLER = "viewCart";
    private final String LIST_ORDERS = "listOrder";

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
        String action = request.getParameter("btnAction");
        String url = INDEX_PAGE;

        try {
            if (action == null || action.equals("index")) {
                url = INDEX_PAGE;
            } else {
                switch (action) {
                    case "Login":
                        url = LOGIN_PAGE;
                        break;
                    case "Signup":
                        url = SIGN_UP_CONTROLLER;
                        break;
                    case "listAccount":
                        if (x == null) {
                            url = LOGIN_PAGE;
                            break;
                        }
                        url = LIST_ACCOUNT_CONTROLLER;
                        break;
                    case "addAccount":
                        if (x == null) {
                            url = LOGIN_PAGE;
                            break;
                        }
                        url = ADD_ACCOUNT_CONTROLLER;
                        break;
                    case "listCategory":
                        if (x == null) {
                            url = LOGIN_PAGE;
                            break;
                        }
                        url = LIST_CATEGORY_CONTROLLER;
                        break;
                    case "addCategory":
                        if (x == null) {
                            url = LOGIN_PAGE;
                            break;
                        }
                        url = ADD_CATEGORY_CONTROLLER;
                        break;
                    case "listProduct":
                        if (x == null) {
                            url = LOGIN_PAGE;
                            break;
                        }
                        url = LIST_PRODUCT_CONTROLLER;
                        break;
                    case "addProduct":
                        if (x == null) {
                            url = LOGIN_PAGE;
                            break;
                        }
                        url = ADD_PRODUCT_CONTROLLER;
                        break;    
                    case "detailProduct":
                        url = DETAIL_PRODUCT_CONTROLLER;
                        break;
                    case "productListByCategory":
                        url = PRODUCT_LIST_BY_CATEGORY;
                        break;
                    case "searchProduct":
                        url = SEARCH_PRODUCT;
                        break;
                    case "addToCart":
                        if (x == null) {
                            url = LOGIN_PAGE;
                            break;
                        }
                        url = ADD_TO_CART_CONTROLLER;
                        break;
                    case  "viewCart":
                        if (x == null) {
                            url = LOGIN_PAGE;
                            break;
                        }
                        url = VIEW_CART_CONTROLLER;
                        break;
                    case  "listOrders":
                        if (x == null) {
                            url = LOGIN_PAGE;
                            break;
                        }
                        url = LIST_ORDERS;
                        break;
                }   
            }
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
