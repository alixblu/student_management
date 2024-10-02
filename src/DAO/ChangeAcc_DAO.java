package DAO;

import DTO.Account_DTO;
import DATABASE.MySQLConnect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ChangeAcc_DAO {
    private MySQLConnect mySQL = new MySQLConnect();

    public ArrayList<Account_DTO> list() {
        ArrayList<Account_DTO> dsAcc = new ArrayList<>();
        String sql = "SELECT * FROM user";
        try (PreparedStatement ps = mySQL.getConnection().prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                Account_DTO acc = new Account_DTO(username, password);
                dsAcc.add(acc);
            }
        } catch (SQLException e) {
            System.err.println("Loi o Data-ChangeAccount");
            e.printStackTrace();
        }
        return dsAcc;
    }

    public void Update(Account_DTO acc) {
        MySQLConnect mysql = new MySQLConnect();
        String sql = "UPDATE user SET ";
        sql += "password = '" + acc.getPassword() + "'";
        sql += " WHERE username='" + acc.getUsername() + "'";
        System.out.println(sql); // This part is for checking if the SQL statement is correct
        mysql.executeUpdate(sql);
    }

    public void Add(Account_DTO acc) {
        MySQLConnect mysql = new MySQLConnect();
        String sql = "INSERT INTO user VALUE (";
        sql += "'" + acc.getUsername() + "',";
        sql += "'" + acc.getPassword() + "',";
        sql += "'" + "HS" + "',";
        sql += "'" + "1" + "')";
        mysql.executeUpdate(sql);
        System.out.println(sql);
    }

}
