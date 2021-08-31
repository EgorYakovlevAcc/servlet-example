package org.example;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AccountServlet extends HttpServlet {
    private int counter = 0;
    private List<User> users = new ArrayList<>();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String bd = req.getParameter("birthdate");

        User user = new User(++counter, getDate(bd), password, login);
        users.add(user);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        User user = users.stream()
                .filter(u -> u.getId() == Integer.parseInt(id))
                .findAny()
                .orElse(null);

        PrintWriter pw = resp.getWriter();

        if (user != null) {
            pw.write("<html>" +
                    "<body><h1>You choose user with name: " + user.getLogin() + "</h1></body>" +
                    "</html>");
        } else {
            pw.write("<html>" +
                    "<body><h1>No such user exists with id: " + id + "</h1></body>" +
                    "</html>");
        }
    }

    private Date getDate(String date) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            return simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
