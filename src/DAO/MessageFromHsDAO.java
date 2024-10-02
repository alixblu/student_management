package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DATABASE.MySQLConnect;
// import DTO.HocSinhDTO;
import DTO.MessageFromHsDTO;

public class MessageFromHsDAO {
    private MySQLConnect mySQL = new MySQLConnect();

    public ArrayList<MessageFromHsDTO> list() {
        ArrayList<MessageFromHsDTO> dsyk = new ArrayList<>();
        String sql = "SELECT * FROM ykien WHERE  1";
        try (PreparedStatement ps = mySQL.getConnection().prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String idnguoigui = rs.getString("idnguoigui");
                String tieudeyk = rs.getString("tieudeyk");
                String noidungyk = rs.getString("noidungyk");
                String thoigianyk = rs.getString("thoigianyk");
                String tenhs = rs.getString("tenhs");
                String trangthai = rs.getString("trangthai");

                MessageFromHsDTO yk = new MessageFromHsDTO(idnguoigui, tieudeyk, noidungyk, thoigianyk, tenhs,
                        trangthai);
                dsyk.add(yk);

            }
        } catch (SQLException e) {
            System.err.println("Loi o qlhs-dao");
            e.printStackTrace();
        }
        return dsyk;
    }

    public void Update(ArrayList<String> uptt) {
        MySQLConnect mysql = new MySQLConnect();
        String sql = "UPDATE ykien SET ";
        sql += "trangthai = '" + uptt.get(2) + "'";
        sql += " WHERE idnguoigui = '" + uptt.get(0) + "' AND noidungyk = '" + uptt.get(1) + "'";
        mysql.executeUpdate(sql);
        System.out.println(sql);
    }

    public void delete(String mahs) {
        String sql = "UPDATE hocsinh SET enable = '0' WHERE HocSinhid='" + mahs + "'";
        mySQL.executeUpdate(sql);
        System.out.println(sql);
    }

    public ArrayList<MessageFromHsDTO> getListSearch(ArrayList<String> mss) {
        ArrayList<MessageFromHsDTO> listSearch = new ArrayList<>();
        String sql = "SELECT * FROM ykien WHERE 1=1";
        ArrayList<Object> params = new ArrayList<>();

        if (mss.get(0) != null) {
            sql += " AND thoigianyk >= ?";
            params.add(mss.get(0));
        }
        if (mss.get(1) != null) {
            sql += " AND thoigianyk <= ?";
            params.add(mss.get(1));
        }
        if (mss.get(2) != null) {
            sql += " AND trangthai = ?";
            params.add(mss.get(2));
        }

        System.out.println("Executing query: " + sql);
        System.out.println("With parameters: " + params);

        try (PreparedStatement st = mySQL.getConnection().prepareStatement(sql)) {
            for (int i = 0; i < params.size(); i++) {
                st.setObject(i + 1, params.get(i));
            }

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    String idnguoigui = rs.getString("idnguoigui");
                    String tieudeyk = rs.getString("tieudeyk");
                    String noidungyk = rs.getString("noidungyk");
                    String thoigianyk = rs.getString("thoigianyk");
                    String tenhs = rs.getString("tenhs");
                    String trangthai = rs.getString("trangthai");

                    MessageFromHsDTO yk = new MessageFromHsDTO(idnguoigui, tieudeyk, noidungyk, thoigianyk, tenhs,
                            trangthai);
                    listSearch.add(yk);
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL exception occurred: " + e.getMessage());
        }

        return listSearch;
    }

    public String getIMG(String mahs) {
        MySQLConnect mysql = new MySQLConnect();
        String sql = "SELECT IMG FROM hocsinh WHERE HocSinhid = '" + mahs + "'";
        String img = null;
        try (PreparedStatement ps = mysql.getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                img = rs.getString("IMG");
            }
        } catch (Exception e) {
        }
        return img;
    }

}
