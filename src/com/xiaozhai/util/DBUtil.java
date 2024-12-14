package com.xiaozhai.util;

import java.sql.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class DBUtil {
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/java","root","940727");
    }
}
