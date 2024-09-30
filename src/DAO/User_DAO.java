package DAO;

import DTO.user;
import DATABASE.MySQLConnect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class User_DAO {
    private MySQLConnect mySQL = new MySQLConnect();

    // Method to get the list of all users
    public ArrayList<user> list() {
        ArrayList<user> dsUser = new ArrayList<>();
        String sql = "SELECT * FROM user";
        try (PreparedStatement ps = mySQL.getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                String role = rs.getString("role");
                String enable = rs.getString("enable");
                user usr = new user(username, password, role, enable);
                dsUser.add(usr);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi ở Data-User_DAO");
            e.printStackTrace();
        }
        return dsUser;
    }

    // Method to update a user
    public void update(user usr) {
        String sql = "UPDATE user SET password = ?, role = ?, enable = ? WHERE username = ?";
        try (PreparedStatement ps = mySQL.getConnection().prepareStatement(sql)) {
            ps.setString(1, usr.getpassword());
            ps.setString(2, usr.getrole());
            ps.setString(3, usr.getenable());
            ps.setString(4, usr.getusername());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to add a new user
    public void add(user usr) {
        String sql = "INSERT INTO user (username, password, role, enable) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = mySQL.getConnection().prepareStatement(sql)) {
            ps.setString(1, usr.getusername());
            ps.setString(2, usr.getpassword());
            ps.setString(3, usr.getrole());
            ps.setString(4, usr.getenable());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to delete a user
    public void delete(String username) {
        String sql = "DELETE FROM user WHERE username = ?";
        try (PreparedStatement ps = mySQL.getConnection().prepareStatement(sql)) {
            ps.setString(1, username);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
