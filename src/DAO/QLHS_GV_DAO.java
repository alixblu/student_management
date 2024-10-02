package DAO;

import DTO.HocSinhDTO;
import DATABASE.MySQLConnect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QLHS_GV_DAO {
    private MySQLConnect mySQL = new MySQLConnect();

    public ArrayList<HocSinhDTO> list() {
        ArrayList<HocSinhDTO> dshs = new ArrayList<>();
        String sql = "SELECT * FROM hocsinh";
        try (PreparedStatement ps = mySQL.getConnection().prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String maHS = rs.getString("HocSinhid");
                String tenHS = rs.getString("HoVaTen");
                String gioiTinh = rs.getString("GioiTinh");
                String namSinh = rs.getString("NgaySinh");
                String diaChi = rs.getString("DiaChi");
                String dienThoai = rs.getString("DienThoai");
                String hocphi = rs.getString("HocPhi");
                String IMG = rs.getString("IMG");

                HocSinhDTO hs = new HocSinhDTO(maHS, tenHS, gioiTinh, namSinh, diaChi, dienThoai);
                hs.setHocPhi(hocphi);
                hs.setIMG(IMG);
                dshs.add(hs);
            }
        } catch (SQLException e) {
            System.err.println("Loi o qlhs-dao");
            e.printStackTrace();
        }
        return dshs;
    }
}
