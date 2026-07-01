/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import java.io.IOException;
import java.sql.Date;
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
import model.Categories;
import model.Products;
import model.dao.AccountsDAO;
import model.dao.CategoriesDAO;
import model.dao.ProductsDAO;
import model.dao.exceptions.NonexistentEntityException;

/**
 *
 * @author wuann_
 */
@WebServlet(name = "ProductUpdate", urlPatterns = {"/ProductUpdate"})
public class ProductUpdate extends HttpServlet {

    private final String PRODUCT_PAGE = "listProduct";
    private final String UPDATE_PRODUCT_PAGE = "/private/UpdateProduct.jsp";
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
        HttpSession session = request.getSession(false);
        Accounts x = (session == null) ? null : (Accounts) session.getAttribute("loginInfo");
        if (x == null) {
            response.sendRedirect("MainController?btnAction=Login");
            return;
        } else if (x.getRoleInSystem() == 3) {
            response.sendRedirect("Error.jsp");
            return;
        } else {
            String productId = request.getParameter("productId");

            ProductsDAO dao = new ProductsDAO();
            Products tempProduct = dao.findProducts(productId);

            request.setAttribute("updateInfo", tempProduct);

            List<Categories> typeList = new CategoriesDAO().listAll();
            request.setAttribute("typeList", typeList);
            RequestDispatcher rd = request.getRequestDispatcher(UPDATE_PRODUCT_PAGE);
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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(false);
        Accounts x = (session == null) ? null : (Accounts) session.getAttribute("loginInfo");
        String url = "";
        if (x == null) {
            response.sendRedirect("MainController?btnAction=Login");
            return;
        } else {
            ProductsDAO dao = new ProductsDAO();
            AccountsDAO accountdao = new AccountsDAO();
            CategoriesDAO categorydao = new CategoriesDAO();

            String productId = request.getParameter("productId");
            Products oldProduct = dao.findProducts(productId);
            if(oldProduct == null){
                response.sendRedirect(ERROR_PAGE);
                return;
            }

            String productName = request.getParameter("productName");
            String productImage = request.getParameter("productImg");
            String brief = request.getParameter("productBrief");
            Date postedDate = Date.valueOf(request.getParameter("productPostedDate"));

            //Categories oldType = oldProduct.getTypeId();
            Categories typeId = categorydao.findCategories(Integer.parseInt(request.getParameter("productTypeId")));

            Accounts account = accountdao.findAccounts(request.getParameter("productAccountName"));

            String unit = request.getParameter("productUnit");

            int price = Integer.parseInt(request.getParameter("productPrice"));
            int discount = Integer.parseInt(request.getParameter("productDiscount"));

            //Products tempProduct = new Products(productId, productName, productImage, brief, postedDate, unit, price, discount, account, typeId);

            oldProduct.setProductName(productName);
            oldProduct.setProductImage(productImage);
            oldProduct.setBrief(brief);
            oldProduct.setPostedDate(postedDate);
            oldProduct.setTypeId(typeId);
            oldProduct.setAccount(account);
            oldProduct.setUnit(unit);
            oldProduct.setPrice(price);
            oldProduct.setDiscount(discount);
            
            try {
                dao.edit(oldProduct);
                url = PRODUCT_PAGE;
            } catch (NonexistentEntityException ex) {
                url = ERROR_PAGE;
                Logger.getLogger(ProductUpdate.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                url = ERROR_PAGE;
                Logger.getLogger(ProductUpdate.class.getName()).log(Level.SEVERE, null, ex);
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
