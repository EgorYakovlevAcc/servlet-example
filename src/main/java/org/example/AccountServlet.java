package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class AccountServlet extends HttpServlet {
    private int counter = 0;
    private List<Account> accounts = new ArrayList<>();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String bd = req.getParameter("birthdate");

        Account account = new Account(++counter, getDate(bd), password, login);
        accounts.add(account);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account inputAccount = getAccountFromRequestBody(req);
        Account account = getAccountById(inputAccount.getId());
        updateAccount(account, inputAccount);
    }

    private Account getAccountFromRequestBody(HttpServletRequest request) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(request.getReader(), Account.class);
    }

    private void updateAccount(Account account, Account inputAccount) {
        if (inputAccount.getLogin() != null) {
            account.setLogin(inputAccount.getLogin());
        }
        if (inputAccount.getBirthdate() != null) {
            account.setBirthdate(inputAccount.getBirthdate());
        }
        if (inputAccount.getPassword() != null) {
            account.setPassword(inputAccount.getPassword());
        }
    }

    private Account getAccountById(Integer id) {
        return this.accounts.stream()
                .filter(acc -> Objects.equals(id, acc.getId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No account existed with id = " + id));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer accountId = getAccountIdFromPath(req);
        if (accountId != null) {
            deleteAccountById(accountId);
        }
    }

    private void deleteAccountById(Integer id) {
        Account account = getAccountById(id);
        this.accounts.remove(account);
    }

    // String s = "I love Moscow";
    // s.split(" "); -> {"I", "love", "Moscow"}
    private Integer getAccountIdFromPath(HttpServletRequest req) {
        String path = req.getPathInfo();
        String[] pathVariables = path.split("/");
        return Arrays.stream(pathVariables)
                .filter(Objects::nonNull)
                .filter(v -> !Objects.equals(v, ""))
                .map(Integer::parseInt)
                .findFirst()
                .orElse(null);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = null;
        Integer id = getAccountIdFromPath(req);

        if (id != null) {
            Account account = getAccountById(id);
            req.setAttribute("user", account);
            requestDispatcher = req.getRequestDispatcher("/account.jsp");
        } else {
            req.setAttribute("users", this.accounts);
            requestDispatcher = req.getRequestDispatcher("/accounts.jsp");
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
