package com.lowkey.complex.database;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnalysisDatabaseStructureServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    Connection conn = null;
    Statement st = null;

    public AnalysisDatabaseStructureServlet() {
        super();
    }

    //获取conn
    public void init() throws ServletException {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/base", "root", "root");
        } catch (InstantiationException | SQLException | ClassNotFoundException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1、获取数据库所有表
        StringBuilder sbTables = new StringBuilder();
        List<String> tables = new ArrayList<String>();
        sbTables.append("-------------- 数据库中有下列的表 ----------<br/>");
        try {
            DatabaseMetaData dbMetaData = conn.getMetaData();
            ResultSet rs = dbMetaData.getTables(null, null, null, new String[]{"TABLE"});
            while (rs.next()) {// ///TABLE_TYPE/REMARKS
                sbTables.append("表名：" + rs.getString("TABLE_NAME") + "<br/>");
                sbTables.append("表类型：" + rs.getString("TABLE_TYPE") + "<br/>");
                sbTables.append("表所属数据库：" + rs.getString("TABLE_CAT") + "<br/>");
                sbTables.append("表所属用户名：" + rs.getString("TABLE_SCHEM") + "<br/>");
                sbTables.append("表备注：" + rs.getString("REMARKS") + "<br/>");
                sbTables.append("------------------------------<br/>");
                tables.add(rs.getString("TABLE_NAME"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 2、遍历数据库表，获取各表的字段等信息
        StringBuffer sbCloumns = new StringBuffer();
        for (String tableName : tables) {
            String sql = "select * from " + tableName;
            try {
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                ResultSetMetaData meta = rs.getMetaData();
                int columeCount = meta.getColumnCount();
                sbCloumns.append("表 " + tableName + "共有 " + columeCount + " 个字段。字段信息如下：<br/>");
                for (int i = 1; i < columeCount + 1; i++) {
                    sbCloumns.append("字段名：" + meta.getColumnName(i) + "<br/>");
                    sbCloumns.append("类型：" + meta.getColumnType(i) + "<br/>");
                    sbCloumns.append("------------------------------<br/>");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            sbCloumns.append("------------------------------<br/>");
        }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
        out.println("<HTML>");
        out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
        out.println("  <BODY>");
        out.println("" + sbTables.toString());
        out.println("" + sbCloumns.toString());
        out.println("  </BODY>");
        out.println("</HTML>");
        out.flush();
        out.close();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doGet(request, response);
    }

    //释放conn
    public void destroy() {
        super.destroy();
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}