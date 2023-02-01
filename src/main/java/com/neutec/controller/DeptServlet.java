package com.neutec.controller;

import com.google.gson.Gson;
import com.neutec.dao.DeptDAO;
import com.neutec.model.DeptVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "DeptServlet", urlPatterns = { "/DeptServlet.do" })
public class DeptServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(" === Enter DeptServlet doGet === ");
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(" === Enter DeptServlet doPost === ");
        request.setCharacterEncoding(StandardCharsets.UTF_8.name());
        if ("toDeptPage_dispatcher".equals(request.getParameter("action"))) {
            System.out.println(">>> Enter toDeptPage_dispatcher");
            request.getRequestDispatcher("/dept/dept.jsp").forward(request, response);
            return;
        }
        if ("toDeptPage_redirect".equals(request.getParameter("action"))) {
            System.out.println(">>> Enter toDeptPage_redirect");
            response.sendRedirect(request.getContextPath() + "/dept/dept.jsp");
            return;
        }
        if ("queryAllDept".equals(request.getParameter("action"))) {
            System.out.println(">>> Enter queryAllDept");
            DeptDAO deptDAO = new DeptDAO();
            try {
                List<DeptVO> deptList = deptDAO.getAll();
                request.setAttribute("myDeptList", deptList);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            request.getRequestDispatcher("/dept/dept.jsp").forward(request, response);
            return;
        }
        if ("queryAllDeptJson".equals(request.getParameter("action"))) {
            System.out.println(">>> Enter queryAllDeptJson");
            DeptDAO deptDAO = new DeptDAO();
            try {
                List<DeptVO> deptList = deptDAO.getAll();
                response.setContentType("application/json; charset=utf-8");
                response.setCharacterEncoding(StandardCharsets.UTF_8.name());
                try(PrintWriter pw = response.getWriter();) {
                    pw.write(new Gson().toJson(deptList));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return;
        }
    }
}
