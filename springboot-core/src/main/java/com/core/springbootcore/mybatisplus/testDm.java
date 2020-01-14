package com.core.springbootcore.mybatisplus;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import static java.lang.System.out;

public class testDm {
    public static void main(String[] args) {
        String driver= "dm.jdbc.driver.DmDriver";
        String url= "jdbc:dm://192.168.0.40:5236/SWZHBGXT";
        String username="SWZHBGXT";
        String password="SWZHBGXT123456";
    try{
        Class.forName(driver);
        Connection conn = DriverManager.getConnection(url, username, password);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select * from C_T_SYS_USER order by create_time");
        if (rs.next()) {
            String userName = rs.getString("UUID");
            out.println("userName:" +userName);
            }else{
            out.println("no data");
        }
        rs.close();
        conn.close();
    }catch (Exception e){
        e.printStackTrace();
    }


    }
}
