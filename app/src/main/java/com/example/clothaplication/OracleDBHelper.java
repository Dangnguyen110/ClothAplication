package com.example.clothaplication;

import android.os.StrictMode;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OracleDBHelper {

    // Thông tin kết nối tới cơ sở dữ liệu Oracle
    private static final String URL = "jdbc:oracle:thin:System@<localhost>:<1521>:<xe>"; // Thay <HOST>, <PORT>, <SID> bằng thông tin cơ sở dữ liệu của bạn
    private static final String USERNAME = "<System>"; // Tên đăng nhập Oracle
    private static final String PASSWORD = "<123>"; // Mật khẩu Oracle

    // Phương thức kết nối tới Oracle
    public static Connection getConnection() {
        Connection connection = null;
        try {
            // Cấu hình để cho phép chạy các kết nối mạng
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            // Tải Driver Oracle
            Class.forName("oracle.jdbc.OracleDriver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    // Kiểm tra đăng nhập
    public static boolean login(String email, String password) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM customers WHERE email = ? AND password = ?")) {

            statement.setString(1, email);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();
            return resultSet.next(); // Nếu tìm thấy bản ghi, đăng nhập thành công
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Đăng ký tài khoản
    public static boolean register(String email, String password) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO customers (email, password, balance) VALUES (?, ?, 1000)")) {

            statement.setString(1, email);
            statement.setString(2, password);

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0; // Nếu thêm bản ghi thành công, trả về true
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Lấy số dư tài khoản
    public static double getBalance(String email) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT balance FROM customers WHERE email = ?")) {

            statement.setString(1, email);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getDouble("balance");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Trả về -1 nếu không tìm thấy tài khoản
    }

    // Cập nhật số dư tài khoản
    public static boolean updateBalance(String email, double newBalance) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE customers SET balance = ? WHERE email = ?")) {

            statement.setDouble(1, newBalance);
            statement.setString(2, email);

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0; // Nếu cập nhật thành công, trả về true
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
