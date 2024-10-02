package DAO;

import DTO.YKienDTO;
import DATABASE.MySQLConnect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class YKienDAO {
    private MySQLConnect mySQL = new MySQLConnect();

    public ArrayList<YKienDTO> list() {
        ArrayList<YKienDTO> dsYKien = new ArrayList<>();
        String sql = "SELECT y.idnguoigui, y.tieudeyk, y.noidungyk, y.thoigianyk, h.HoVaTen, y.trangthai " +
                     "FROM ykien y " +
                     "INNER JOIN hocsinh h ON y.idnguoigui = h.HocSinhid " ;
    
        try (PreparedStatement ps = mySQL.getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                YKienDTO yk = new YKienDTO();
                yk.setIdnguoigui(rs.getString("idnguoigui"));
                yk.setTieudeyk(rs.getString("tieudeyk"));
                yk.setNoidungyk(rs.getString("noidungyk"));
                yk.setThoigianyk(rs.getString("thoigianyk"));
                yk.setTenhs(rs.getString("HoVaTen"));
                yk.setTrangthai(rs.getString("trangthai"));
                dsYKien.add(yk);
            }
        } catch (SQLException e) {
            System.err.println("Error in YKienDAO.list()");
            e.printStackTrace();
        }
        return dsYKien;
    }

    public void set(YKienDTO yk) {
        String sql = "UPDATE ykien SET tieudeyk = ?, noidungyk = ?, thoigianyk = ?, tenhs = ?, trangthai = ? WHERE idnguoigui = ?";
        try (PreparedStatement ps = mySQL.getConnection().prepareStatement(sql)) {
            ps.setString(1, yk.getTieudeyk());
            ps.setString(2, yk.getNoidungyk());
            ps.setString(3, yk.getThoigianyk());
            ps.setString(4, yk.getTenhs());
            ps.setString(5, yk.getTrangthai());
            ps.setString(6, yk.getIdnguoigui());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error in YKienDAO.set()");
            e.printStackTrace();
        }
    }

    public void add(YKienDTO yk) {
        String sql = "INSERT INTO ykien (idnguoigui, tieudeyk, noidungyk, thoigianyk, tenhs, trangthai) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = mySQL.getConnection().prepareStatement(sql)) {
            ps.setString(1, yk.getIdnguoigui());
            ps.setString(2, yk.getTieudeyk());
            ps.setString(3, yk.getNoidungyk());
            ps.setString(4, yk.getThoigianyk());
            ps.setString(5, yk.getTenhs());
            ps.setString(6, yk.getTrangthai());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error in YKienDAO.add()");
            e.printStackTrace();
        }
    }

    public void delete(String idnguoigui) {
        String sql = "DELETE FROM ykien WHERE idnguoigui = ?";
        try (PreparedStatement ps = mySQL.getConnection().prepareStatement(sql)) {
            ps.setString(1, idnguoigui);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error in YKienDAO.delete()");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        YKienDAO ykienDAO = new YKienDAO();
        ArrayList<YKienDTO> dsYKien = ykienDAO.list();

        for (YKienDTO yk : dsYKien) {
            System.out.println("ID Người gửi: " + yk.getIdnguoigui());
            System.out.println("Tiêu đề: " + yk.getTieudeyk());
            System.out.println("Nội dung: " + yk.getNoidungyk());
            System.out.println("Thời gian: " + yk.getThoigianyk());
            System.out.println("Tên học sinh: " + yk.getTenhs());
            System.out.println("Trạng thái: " + yk.getTrangthai());
            System.out.println();
        }
    }
}
