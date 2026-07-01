/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/ServletListener.java to edit this template
 */
package EventListener;

import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import model.Accounts;
import model.CartShop;

/**
 * Web application lifecycle listener.
 *
 * @author wuann_
 */
public class SessionEventListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        CartShop x = new CartShop();
        HttpSession session = se.getSession();
        session.setAttribute("cart", x);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        Accounts x = (Accounts) session.getAttribute("loginInfo");

        if (x != null) {
            List<String> onlineList = (List<String>) session.getServletContext().getAttribute("onlineList");
            if (onlineList != null) {
                onlineList.remove(x.getAccount());
            }
        }
    }
}
