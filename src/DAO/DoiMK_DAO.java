package DAO;

import DATABASE.MySQLConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DoiMK_DAO {
    private static final Logger LOGGER = Logger.getLogger(DoiMK_DAO.class.getName());
    private MySQLConnect mySQL = new MySQLConnect();

    public boolean changePassword(String username, String oldPassword, String newPassword) {
        String checkPasswordSql = "SELECT password FROM user WHERE username = ?";
        String updatePasswordSql = "UPDATE user SET password = ? WHERE username = ?";

        try (Connection conn = mySQL.getConnection();
             PreparedStatement checkPs = conn.prepareStatement(checkPasswordSql);
             PreparedStatement updatePs = conn.prepareStatement(updatePasswordSql)) {

            // Kiểm tra mật khẩu cũ
            checkPs.setString(1, username);
            ResultSet rs = checkPs.executeQuery();
            if (rs.next()) {
                String currentPassword = rs.getString("password");
                if (!currentPassword.equals(oldPassword)) {
                    LOGGER.log(Level.WARNING, "Mật khẩu cũ không đúng cho username: {0}", username);
                    rs.close();  // Đóng ResultSet sau khi sử dụng
                    return false;
                }
            } else {
                LOGGER.log(Level.WARNING, "Không tìm thấy username: {0}", username);
                rs.close();  // Đóng ResultSet sau khi sử dụng
                return false;
            }
            rs.close();  // Đóng ResultSet sau khi sử dụng

            // Cập nhật mật khẩu mới
            updatePs.setString(1, newPassword);
            updatePs.setString(2, username);
            int rowsAffected = updatePs.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Lỗi trong phương thức changePassword của DoiMK_DAO", e);
            return false;
        }
    }
}
