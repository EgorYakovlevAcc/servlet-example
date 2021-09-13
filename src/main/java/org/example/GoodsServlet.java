package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GoodsServlet extends HttpServlet {
    private static List<Good> goodList = getGoodList();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("goods", this.goodList);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/goods.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        org.example.pojo.Good good = getGood(req);
        HttpSession session = req.getSession();
        List<org.example.session.Good> goods = (List<org.example.session.Good>) session.getAttribute("goods");
        if (!isEmpty(goods)) {
            org.example.session.Good goodSession = findGoodById(goods, good.getId());
            int amount = good.getIncrement();
            if (goodSession != null) {
                goods.remove(goodSession);
                amount = amount + goodSession.getAmount();
                goodSession.setAmount(amount);
            } else {
                goodSession = new org.example.session.Good();
                goodSession.setId(good.getId());
            }
            goodSession.setAmount(amount);
            goods.add(goodSession);
        } else {
            goods = new ArrayList<>();
            org.example.session.Good g = new org.example.session.Good();
            g.setId(good.getId());
            g.setAmount(good.getIncrement());
            goods.add(g);
        }
        session.setAttribute("goods", goods);
    }

    private org.example.session.Good findGoodById(List<org.example.session.Good> goods, Integer id) {
        return goods.stream()
                .filter(good -> Objects.equals(id, good.getId()))
                .findAny()
                .orElse(null);
    }

    private static boolean isEmpty(List list) {
        if (list != null) {
            if (list.size() > 0) {
                return false;
            }
        }
        return true;
    }

    private org.example.pojo.Good getGood(HttpServletRequest request) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(request.getReader(), org.example.pojo.Good.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static List<Good> getGoodList() {
        Good bread = new Good();
        bread.setId(1);
        bread.setName("Bread");
        bread.setPrice(50.0);

        Good meat = new Good();
        meat.setId(2);
        meat.setPrice(300.0);
        meat.setName("Meat");

        Good juice = new Good();
        juice.setId(3);
        juice.setPrice(100.0);
        juice.setName("Apple juice");

        return List.of(bread, meat, juice);
    }
}
