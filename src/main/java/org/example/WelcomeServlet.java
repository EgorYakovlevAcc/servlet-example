package org.example;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class WelcomeServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException {

        PrintWriter out = response.getWriter();
        // simple one
//                out.println("Hello, application!");

        // more complex
//        out.write("<html><body><h1>Hello, world!</h1></body></html>");
//
        // more more complex
        out.write("<html>" +
                "<head>" +
                "<title>Hello page</title>" +
                "</head>" +
                "<body>" +
                "<h1>Main page</h1>" +
                "<a href='/users/'>my link</a>"+
                "</body>" +
                "</html>");
    }
}
