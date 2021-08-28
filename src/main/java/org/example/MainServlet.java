package org.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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

@WebServlet("/users/")
public class MainServlet extends HttpServlet {
    private List<User> users;
    private int idCounter;

    public MainServlet(List<User> users, int idCounter) {
        this.users = users;
        this.idCounter = idCounter;
    }

    @Override
    public void init() throws ServletException {
        super.init();
        this.users = new ArrayList<>();
        this.idCounter = 0;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();
        for (User user : this.users) {
            pw.write(user.toString());
        }
        pw.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // user creation before validation
        User user = new User();
        user.setName(req.getParameter("name"));
        user.setId(getId());
        String date = req.getParameter("date");
        user.setBirthdate(getDate(date));

        this.users.add(user);

        PrintWriter pw = resp.getWriter();
        pw.write("User " + user.getName() + " has been created successfully.");
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

    private Integer getId() {
        return ++this.idCounter;
    }
}
