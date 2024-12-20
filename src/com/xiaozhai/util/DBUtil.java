package com.xiaozhai.util;

import java.sql.*;

public class DBUtil {
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/java","root","940727");
    }
}
