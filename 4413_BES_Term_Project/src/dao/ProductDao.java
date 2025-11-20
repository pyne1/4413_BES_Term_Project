package dao;

import model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {

    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();

        String sql = "SELECT id, name, price FROM products";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setPrice(rs.getDouble("price"));
                products.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }
}
