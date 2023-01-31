package com.neutec.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;

@WebServlet(name = "MultiThreadTestServlet", urlPatterns = { "/MultiThreadTestServlet" })
public class MultiThreadTestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding(StandardCharsets.UTF_8.name());
        System.out.println(MessageFormat.format("action = {0}", request.getParameter("action")));

        switch (request.getParameter("action")) {
            case "toMultiThreadPage":
                request.getRequestDispatcher("/multiThreadPage.jsp").forward(request, response);
                return; // 沒 return 會繼續執行
            default:
                System.out.println("do nothing...");
                break;
        }

    }
}
