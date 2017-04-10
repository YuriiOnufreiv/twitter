package ua.onufreiv.twitter.web.infrastructure;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Yurii_Onufreiv on 10-Apr-17.
 */
public interface MyController {
    void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
