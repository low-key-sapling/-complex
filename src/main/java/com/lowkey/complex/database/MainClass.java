package com.lowkey.complex.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainClass {

    /**
     * @param args
     * @author www.zuidaima.com
     */
    public static void main(String[] args) {
        Statement stmt = null;
        Connection conn = null;
        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:h2:tcp://www.zuidaima.com:5435/mem:DBTest", "Guest",
                    "123456");
            // add application code here
            stmt = conn.createStatement();
//			String createSql = "create table product(product_id integer not null,title varchar2(30) not null,type_id integer not null,info varchar2(80),price number(16,2) not null,constraint product_pk primary key (product_id),constraint product_fk foreign key(type_id) references item(type_id)))";
//			stmt.executeUpdate(createSql);
            // 添加一条语句
            // String insertSql =
            // "insert into UserInfo values('刘尚','www.zuidaima.com','888888','true',15827288756,15827288756)";
            // stmt.executeUpdate(insertSql);
            // 删除一条记录
            // String deleteSql = "delete from USERINFO where UID = '刘尚1'";
            // stmt.executeUpdate(deleteSql);
            // 修改一条语句
            // String UpdateSql =
            // "UPDATE USERINFO  SET IP='www.zuidaima.com' ,  LINKID='11111' , ACTION='FALSE' ,  UPTIME=2121231 ,DOWNTIME=2121212 WHERE UID='刘尚'";
            // stmt.executeUpdate(deleteSql);
            ResultSet rs = stmt.executeQuery("SELECT * FROM UserInfo ");
            while (rs.next()) {
                System.out.println("Uid:" + rs.getString("Uid") + "\tIp:"
                        + rs.getString("Ip") + "\tLinkId:"
                        + rs.getString("LinkId") + "\tAction:"
                        + rs.getBoolean("Action") + "\tUpTime:"
                        + rs.getLong("UpTime") + "\tDownTime:"
                        + rs.getLong("DownTime"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}