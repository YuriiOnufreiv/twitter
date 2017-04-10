package ua.onufreiv.twitter.web.infrastructure;

import org.springframework.beans.factory.BeanNameAware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Yurii_Onufreiv on 10-Apr-17.
 */
public class HelloController implements MyController, BeanNameAware {
    private String beanName;

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().println("<h1>Hello from Hello Controller</h1>");
        response.getWriter().println("<h3>" + beanName + "</h3>");
    }

    @Override
    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }
}
