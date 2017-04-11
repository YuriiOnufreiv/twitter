package ua.onufreiv.twitter.web.infrastructure;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by Yurii_Onufreiv on 11-Apr-17.
 */
public class MyServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        String contextConfigLocation = servletContext.getInitParameter("commonContextLocation");
        ConfigurableApplicationContext commonContext
                = new ClassPathXmlApplicationContext(contextConfigLocation.split(" "));
        servletContext.setAttribute(servletContext.getInitParameter("commonContext"), commonContext);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
