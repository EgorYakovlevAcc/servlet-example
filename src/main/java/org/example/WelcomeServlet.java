package org.example;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WelcomeServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(WelcomeServlet.class);

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {

        LOGGER.log(Level.INFO, "Welcome page has been successfully uploaded.");

        RequestDispatcher view = request.getRequestDispatcher("welcome.jsp");
        view.forward(request, response);
    }
}
