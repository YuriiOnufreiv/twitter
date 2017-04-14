package ua.onufreiv.twitter.web.infrastructure;

import org.springframework.context.ConfigurableApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Yurii_Onufreiv on 10-Apr-17.
 */
public class DispatcherServlet extends HttpServlet {
    private ConfigurableApplicationContext webContext;

    @Override
    public void init() throws ServletException {
        String contextConfigLocationParam = getInitParameter("contextConfigLocation");
        String commonContextParam = getServletContext().getInitParameter("commonContext");

//        ApplicationContext commonContext = (ApplicationContext) getServletContext().getAttribute(commonContextParam);
//        webContext = new ClassPathXmlApplicationContext(new String[]{contextConfigLocationParam}, commonContext);
    }

    @Override
    public void destroy() {
        webContext.close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String beanName = getBeanNameFromUri(req);
        handleRequest(req, resp, beanName);
    }

    private void handleRequest(HttpServletRequest req, HttpServletResponse resp, String beanName) throws IOException {
        MyController myController = (MyController) webContext.getBean(beanName);

        if (myController != null) {
            myController.handleRequest(req, resp);
        } else {
            resp.getWriter().println("<h1>No controller found</h1>");
        }
    }

    private String getBeanNameFromUri(HttpServletRequest req) {
        String requestUri = req.getRequestURI();
        return requestUri.substring(requestUri.lastIndexOf('/'));
    }
}
