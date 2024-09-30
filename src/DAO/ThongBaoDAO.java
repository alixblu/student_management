package DAO;

import DATABASE.MySQLConnect;
import DTO.ThongBaoDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ThongBaoDAO {
    private MySQLConnect mySQL = new MySQLConnect();
    public ArrayList<ThongBaoDTO> list() {
        ArrayList<ThongBaoDTO> dsThongBao = new ArrayList<>();

        String sql = "SELECT * FROM thongbao";
        try (PreparedStatement ps = mySQL.getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                ThongBaoDTO tb = new ThongBaoDTO();
                tb.setIdnguoigui(rs.getString("idnguoigui"));
                tb.setTieudetb(rs.getString("tieudetb"));
                tb.setNoidungtb(rs.getString("noidungtb"));
                tb.setThoigiantb(rs.getString("thoigiantb"));
                tb.setLoaitb(rs.getString("loaitb"));
                dsThongBao.add(tb);
            }
        } catch (SQLException e) {
            System.err.println("Error in ThongBaoDAO.list()");
            e.printStackTrace();
        }
        return dsThongBao;
    }
    public void set(ThongBaoDTO tb) {
        String sql = "UPDATE thongbao SET tieudetb = ?, noidungtb = ?, thoigiantb = ?, loaitb = ? WHERE idnguoigui = ?";
        try (PreparedStatement ps = mySQL.getConnection().prepareStatement(sql)) {
            ps.setString(1, tb.getTieudetb());
            ps.setString(2, tb.getNoidungtb());
            ps.setString(3, tb.getThoigiantb());
            ps.setString(4, tb.getLoaitb());
            ps.setString(5, tb.getIdnguoigui());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error in ThongBaoDAO.set()");
            e.printStackTrace();
        }
    }

    public void add(ThongBaoDTO tb) {
        String sql = "INSERT INTO thongbao (idnguoigui, tieudetb, noidungtb, thoigiantb, loaitb) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = mySQL.getConnection().prepareStatement(sql)) {
            ps.setString(1, tb.getIdnguoigui());
            ps.setString(2, tb.getTieudetb());
            ps.setString(3, tb.getNoidungtb());
            ps.setString(4, tb.getThoigiantb());
            ps.setString(5, tb.getLoaitb());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error in ThongBaoDAO.add()");
            e.printStackTrace();
        }
    }

    public void delete(String idnguoigui) {
        String sql = "DELETE FROM thongbao WHERE idnguoigui = ?";
        try (PreparedStatement ps = mySQL.getConnection().prepareStatement(sql)) {
            ps.setString(1, idnguoigui);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error in ThongBaoDAO.delete()");
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        ThongBaoDAO thongBaoDAO = new ThongBaoDAO();
        ArrayList<ThongBaoDTO> dsThongBao = thongBaoDAO.list();

        for (ThongBaoDTO tb : dsThongBao) {
            System.out.println("ID Người gửi: " + tb.getIdnguoigui());
            System.out.println("Tiêu đề: " + tb.getTieudetb());
            System.out.println("Nội dung: " + tb.getNoidungtb());
            System.out.println("Thời gian: " + tb.getThoigiantb());
            System.out.println("Loại: " + tb.getLoaitb());
            System.out.println();
        }
    }
}

