package com.example;
import java.sql.*;

public class Main {

    private static final String URL =
        "jdbc:mysql://localhost:3306/simple_jdbc?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    public static void main(String[] args) {

        // CREATE
        insertUser("Nguyen Van A", "a@gmail.com");

        // READ
        getAllUsers();

        // UPDATE
        updateUser(1, "Nguyen Van A Updated", "updated@gmail.com");

        // DELETE
        deleteUser(2);
    }

    // ================= CRUD METHODS =================

    static void insertUser(String name, String email) {
        String sql = "INSERT INTO users(name, email) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, email);
            ps.executeUpdate();
            System.out.println("Inserted user successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void getAllUsers() {
        String sql = "SELECT * FROM users";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            System.out.println("User list:");
            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " | " +
                        rs.getString("name") + " | " +
                        rs.getString("email")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void updateUser(int id, String name, String email) {
        String sql = "UPDATE users SET name=?, email=? WHERE id=?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setInt(3, id);
            ps.executeUpdate();
            System.out.println("Updated user successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void deleteUser(int id) {
        String sql = "DELETE FROM users WHERE id=?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Deleted user successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}