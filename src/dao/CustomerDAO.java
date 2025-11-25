package dao;

import model.Customer;
import util.DBUtil;

import java.sql.*;

public class CustomerDAO {

    // Helper: map a ResultSet row to a Customer object
    private static Customer mapRow(ResultSet rs) throws SQLException {
        Customer c = new Customer();
        c.setId(rs.getInt("id"));
        c.setFirstName(rs.getString("firstName"));
        c.setLastName(rs.getString("lastName"));
        c.setEmail(rs.getString("email"));
        // In DB the column is passwordHash, in model we just call it password
        c.setPassword(rs.getString("passwordHash"));

        Object addr = rs.getObject("addressID");
        if (addr == null) {
            c.setAddressId(null);
        } else {
            c.setAddressId(((Number) addr).intValue());
        }
        return c;
    }

    /**
     * Find a customer by email. Returns null if not found.
     */
    public static Customer findByEmail(String email) {
        String sql = "SELECT * FROM customer WHERE email = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            System.out.println("DEBUG findByEmail email = " + email);
            System.out.println("DEBUG DB catalog = " + conn.getCatalog());

            ps.setString(1, email);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    System.out.println("DEBUG: user FOUND with that email");
                    return mapRow(rs);
                } else {
                    System.out.println("DEBUG: NO user with that email");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    /**
     * Find a customer by id. Returns null if not found.
     */
    public static Customer findById(int id) {
        String sql = "SELECT * FROM Customer WHERE id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Create a new customer. Returns true on success.
     * NOTE: Right now we store the plain password into passwordHash column.
     * You can replace this later with a real hash.
     */
    public static boolean createCustomer(Customer c) {
        String sql = "INSERT INTO Customer " +
                "(firstName, lastName, email, passwordHash, addressID) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, c.getFirstName());
            ps.setString(2, c.getLastName());
            ps.setString(3, c.getEmail());
            ps.setString(4, c.getPassword()); // plain for now

            if (c.getAddressId() == null) {
                ps.setNull(5, Types.INTEGER);
            } else {
                ps.setInt(5, c.getAddressId());
            }

            int rows = ps.executeUpdate();
            if (rows > 0) {
                try (ResultSet keys = ps.getGeneratedKeys()) {
                    if (keys.next()) {
                        c.setId(keys.getInt(1));
                    }
                }
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Very simple login: compare plain password to stored passwordHash.
     * Replace with proper hashing later if you want.
     */
    public static Customer validateLogin(String email, String plainPassword) {
        Customer c = findByEmail(email);
        if (c != null && plainPassword != null && plainPassword.equals(c.getPassword())) {
            return c;
        }
        return null;
    }

    /**
     * Update basic customer info (no password change here).
     * You can call this from a profile update page later.
     */
    public static boolean updateCustomer(Customer c) {
        String sql = "UPDATE Customer SET firstName = ?, lastName = ?, email = ?, addressID = ? " +
                     "WHERE id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, c.getFirstName());
            ps.setString(2, c.getLastName());
            ps.setString(3, c.getEmail());

            if (c.getAddressId() == null) {
                ps.setNull(4, Types.INTEGER);
            } else {
                ps.setInt(4, c.getAddressId());
            }

            ps.setInt(5, c.getId());

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
