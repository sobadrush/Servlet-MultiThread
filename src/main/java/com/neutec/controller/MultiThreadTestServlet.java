package com.neutec.controller;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.LinkedHashMap;
import java.util.Map;

@WebServlet(name = "MultiThreadTestServlet", urlPatterns = { "/MultiThreadTestServlet" })
public class MultiThreadTestServlet extends HttpServlet {

    private int mBalance = 100; // 餘額

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
            case "withdraw": // 提款
                response.setCharacterEncoding(StandardCharsets.UTF_8.name());
                response.setContentType("application/json; charset=utf-8");
                int withdrawMoney = Integer.parseInt(request.getParameter("withdrawMoney"));
                System.out.println(MessageFormat.format("{0}: withdrawMoney = {1}", Thread.currentThread().getName(), withdrawMoney));

                PrintWriter pw = response.getWriter();
                String resultStatus = "";
                synchronized (this) {
                    if (this.mBalance > withdrawMoney) {
                        // 若沒 synchronized，則提款 90 & 30 都會進入此 if，也就是說，不論餘額足不足夠，都會顯示提款成功 → 有問題
                        // 需用 synchronized 控制!!!
                        sleep(2); // 休眠
                        this.mBalance -= withdrawMoney;
                        System.out.println(MessageFormat.format(">>> 提款 {0} 成功: 餘額 {1}", withdrawMoney, this.mBalance));
                        resultStatus = "SUCCESS";
                    } else {
                        System.out.println(MessageFormat.format(">>> 提款 {0} 失敗: 餘額 {1}", withdrawMoney, this.mBalance));
                        resultStatus = "FAIL";
                    }
                }
                pw.write("{ \"status\" : \"" + resultStatus + "\", \"mBalance\": \"" + this.mBalance + "\" }");
                pw.close();
                return;
            case "recoverMDepositAmount": // 復原 mDepositAmount
                this.mBalance = 100;
                Map<String, String> rtnMap = new LinkedHashMap<>();
                rtnMap.put("status", "SUCCESS");
                rtnMap.put("mBalance", String.valueOf(this.mBalance));
                pw = response.getWriter();
                pw.println(new Gson().toJson(rtnMap));
                pw.close();
                return;
            default:
                System.out.println("do nothing...");
                break;
        }

    }

    private void sleep(int sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
