package com.neutec.dao;

import com.neutec.model.DeptVO;
import com.neutec.utils.JDBCConnFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class DeptDAO {

    public Connection conn = JDBCConnFactory.getInstance().getConnection();;

    {
        System.out.println(">>> DeptDAO 雙大誇號初始化"); // 比建構子早執行
    }

    public DeptDAO() {
        System.out.println(">>> DeptDAO 建構子");
    }

    /**
     * 查詢全部
     */
    public List<DeptVO> getAll() throws SQLException {
        List<DeptVO> list = new ArrayList<>();
        try (PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM DEPT_TB;");
            ResultSet rs = pstmt.executeQuery();
        ) {
            while (rs.next()) {
                DeptVO deptVO = new DeptVO();
                deptVO.setDeptNo(rs.getInt("DEPT_NO"));
                deptVO.setDeptName(rs.getString("DNAME"));
                deptVO.setDeptLoc(rs.getString("LOC"));
                list.add(deptVO);
            }
        }
        return list;
    }

    /**
     * 新增一筆
     */
    public Integer insertOne(DeptVO deptVO) throws SQLException {
        int resultNum = 0;
        try(PreparedStatement pstmt = conn.prepareStatement("INSERT INTO DEPT_TB(DNAME,LOC) VALUES (?, ?);");) {
            pstmt.setString(1, deptVO.getDeptName());
            pstmt.setString(2, deptVO.getDeptLoc());
            resultNum = pstmt.executeUpdate();
        }
        return resultNum;
    }

    public static void main(String[] args) {
        DeptDAO deptDAO = new DeptDAO();
        try {
            System.out.println(MessageFormat.format("insert result = {0}", deptDAO.insertOne(new DeptVO("元宇宙科", "台北汐止"))));
            deptDAO.getAll().forEach(System.out::println);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
