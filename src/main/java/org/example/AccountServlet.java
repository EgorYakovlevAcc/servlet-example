package org.example;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
        System.out.println(user);
        users.add(user);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        RequestDispatcher requestDispatcher = null;

        if (id != null) {
            User user = users.stream()
                    .filter(u -> u.getId() == Integer.parseInt(id))
                    .findAny()
                    .orElse(null);
            req.setAttribute("user", user);
            requestDispatcher = req.getRequestDispatcher("account.jsp");
        } else {
            req.setAttribute("users", this.users);
            requestDispatcher = req.getRequestDispatcher("accounts.jsp");
        }
        requestDispatcher.forward(req, resp);
    }

    private Date getDate(String date) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
