package com.xiaozhai.util;

import java.sql.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class DBUtil {
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://107.151.251.189:3306/java","testuser","123456");
    }
}
