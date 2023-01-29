package com.neutec;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

// @WebServlet(value="/hello", name="helloServlet", urlPatterns = "/helloGG")
public class HelloServlet extends HttpServlet {

    // @Override
    // public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
    //     res.getWriter().println("Hello world!!");
    // }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.setContentType("text/plain");

        System.out.println("init param: " + super.getInitParameter("fruit"));

        try(PrintWriter pw = resp.getWriter();) {
            pw.write("init param: " + super.getInitParameter("fruit"));
        }
    }
}
