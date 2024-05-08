package com.khrd;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/jdbc_javas";
        String userName = "postgres";
        String password = "1111@2222@";

        try (Connection con = DriverManager.getConnection(url, userName, password)) {
            if (con.isValid(2)) {
                System.out.println("Connection is successful!");
            }

            String insertQuery = "INSERT INTO product (id, name, price_per_unit, active_for_sell) VALUES (?, ?, ?, ?)";
            try (PreparedStatement ps = con.prepareStatement(insertQuery)) {
                ps.setInt(1, 1);
                ps.setString(2, "Coke");
                ps.setDouble(3, 2);
                ps.setInt(4, 1);
                ps.executeUpdate();

                ps.setInt(1, 2);
                ps.setString(2, "Pepsi");
                ps.setDouble(3, 2);
                ps.setInt(4, 1);
                ps.executeUpdate();

                ps.setInt(1, 3);
                ps.setString(2, "kizz");
                ps.setDouble(3, 1);
                ps.setInt(4, 1);
                ps.executeUpdate();

                ps.setInt(1, 4);
                ps.setString(2, "Redbull");
                ps.setDouble(3, 2);
                ps.setInt(4, 1);
                ps.executeUpdate();

                System.out.println("Data inserted successfully!");
            }

            String getAllData = "SELECT * FROM product";
            try (PreparedStatement ps = con.prepareStatement(getAllData)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    System.out.println("ID: " + rs.getInt("id") + ", Name: " + rs.getString("name") + ", Price: " + rs.getDouble("price_per_unit") + ", Active: " + rs.getBoolean("active_for_sell"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
