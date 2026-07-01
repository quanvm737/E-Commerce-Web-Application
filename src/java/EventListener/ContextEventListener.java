/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/ServletListener.java to edit this template
 */
package EventListener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Web application lifecycle listener.
 *
 * @author wuann_
 */
public class ContextEventListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        List<String> onlineList = new ArrayList<>();
        sce.getServletContext().setAttribute("onlineList", onlineList);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
