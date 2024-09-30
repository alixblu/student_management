package DAO;


import DTO.HocPhiDTO;
import DATABASE.MySQLConnect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HocPhiDAO {
    private MySQLConnect mySQL = new MySQLConnect();

    public ArrayList<HocPhiDTO> list() {
        ArrayList<HocPhiDTO> dsHocPhi = new ArrayList<>();
        String sql = "SELECT idhs, idnh, thoigian, status FROM hocphi";

        try (PreparedStatement ps = mySQL.getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                HocPhiDTO hocPhi = new HocPhiDTO();
                hocPhi.setIdhs(rs.getString("idhs"));
                hocPhi.setIdnh(rs.getString("idnh"));
                hocPhi.setThoigian(rs.getString("thoigian"));
                hocPhi.setStatus(rs.getInt("status"));
                dsHocPhi.add(hocPhi);
            }
        } catch (SQLException e) {
            System.err.println("Error in HocPhiDAO.list()");
            e.printStackTrace();
        }
        return dsHocPhi;
    }

    public void set(HocPhiDTO hocPhi) {
        String sql = "UPDATE hocphi SET thoigian = ?, status = ? WHERE idhs = ?";
        try (PreparedStatement ps = mySQL.getConnection().prepareStatement(sql)) {
            ps.setString(1, hocPhi.getThoigian());
            ps.setInt(2, hocPhi.getStatus());
            ps.setString(3, hocPhi.getIdhs());
            ps.executeUpdate();
            
            // Update the HocPhi field in the hocsinh table only if status is 1
            if (0==(hocPhi.getStatus())) {
                String updateHocPhiSql = "UPDATE hocsinh SET HocPhi = 'Chưa thanh toán' WHERE HocSinhid = ?";
                try (PreparedStatement updatePs = mySQL.getConnection().prepareStatement(updateHocPhiSql)) {
                    updatePs.setString(1, hocPhi.getIdhs());
                    updatePs.executeUpdate();
                }
            }
        } catch (SQLException e) {
            System.err.println("Error in HocPhiDAO.set()");
            e.printStackTrace();
        }
    }
    

    public void add(HocPhiDTO hocPhi) {
        String sql = "INSERT INTO hocphi (idhs, idnh, thoigian, status) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = mySQL.getConnection().prepareStatement(sql)) {
            ps.setString(1, hocPhi.getIdhs());
            ps.setString(2, hocPhi.getIdnh());
            ps.setString(3, hocPhi.getThoigian());
            ps.setInt(4, hocPhi.getStatus());
            ps.executeUpdate();
            
            // Update the HocPhi field in the hocsinh table only if status is 1
            if (1 == hocPhi.getStatus()) {
                String updateHocPhiSql = "UPDATE hocsinh SET HocPhi = 'Đã thanh toán' WHERE HocSinhid = ?";
                try (PreparedStatement updatePs = mySQL.getConnection().prepareStatement(updateHocPhiSql)) {
                    updatePs.setString(1, hocPhi.getIdhs());
                    updatePs.executeUpdate();
                }
            }
        } catch (SQLException e) {
            System.err.println("Error in HocPhiDAO.add()");
            e.printStackTrace();
        }
    }
    

    public void delete(int idhs) {
        String sql = "DELETE FROM hocphi WHERE idhs = ?";
        try (PreparedStatement ps = mySQL.getConnection().prepareStatement(sql)) {
            ps.setInt(1, idhs);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error in HocPhiDAO.delete()");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        HocPhiDAO hocPhiDAO = new HocPhiDAO();
        ArrayList<HocPhiDTO> dsHocPhi = hocPhiDAO.list();

        for (HocPhiDTO hocPhi : dsHocPhi) {
            System.out.println("ID Học Sinh: " + hocPhi.getIdhs());
            System.out.println("ID Năm Học: " + hocPhi.getIdnh());
            System.out.println("Thời gian: " + hocPhi.getThoigian());
            System.out.println("Trạng thái: " + hocPhi.getStatus());
            System.out.println();
        }
    }
}
