package com.neutec.controller;

import sun.plugin2.message.Message;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
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
