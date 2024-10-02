package DAO;

import DATABASE.MySQLConnect;
import DTO.QLPhanCongDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QLPhanCongDAO {

    private MySQLConnect mySQL = new MySQLConnect();

    public ArrayList<QLPhanCongDTO> list() {
        ArrayList<QLPhanCongDTO> dspc = new ArrayList<>();
        String sql = "SELECT g.GiaoVienid, g.TenGiaoVien, l.TenLop " +
                "FROM phancong pc " +
                "JOIN giaovien g ON pc.GiaoVienid = g.GiaoVienid " +
                // "JOIN monhoc m ON pc.MonHocid = m.MonHocid " +
                "JOIN lop l ON pc.Lopid = l.Lopid " +
                "WHERE pc.enable = 1";

        try (PreparedStatement ps = mySQL.getConnection().prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String magv = rs.getString("GiaoVienid");
                String tengv = rs.getString("TenGiaoVien");
                String tenlop = rs.getString("TenLop");
                // String tenmon = rs.getString("TenMonHoc");
                QLPhanCongDTO pc = new QLPhanCongDTO(magv, tengv, tenlop);

                dspc.add(pc);
            }
        } catch (SQLException e) {
            System.err.println("Loi o qlhs-dao");
            e.printStackTrace();
        }
        return dspc;
    }

    public void Update(QLPhanCongDTO pc) {
        MySQLConnect mySQL = new MySQLConnect();
        // String mamonhoc = null;
        String malop = null;

        // String sql1 = "SELECT MonHocid FROM monhoc WHERE TenMonHoc = ?";
        // try (PreparedStatement ps1 = mySQL.getConnection().prepareStatement(sql1)) {
        //     ps1.setString(1, pc.getMon());
        //     try (ResultSet rs1 = ps1.executeQuery()) {
        //         if (rs1.next()) {
        //             mamonhoc = rs1.getString("MonHocid");
        //         }
        //     }
        // } catch (SQLException e) {
        //     System.err.println("Lỗi ở QLPCBUS trong phần MonHoc: " + e.getMessage());
        // }

        String sql2 = "SELECT Lopid FROM lop WHERE TenLop = ?";
        try (PreparedStatement ps2 = mySQL.getConnection().prepareStatement(sql2)) {
            ps2.setString(1, pc.getLop());
            try (ResultSet rs2 = ps2.executeQuery()) {
                if (rs2.next()) {
                    malop = rs2.getString("Lopid");
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi ở QLPCBUS trong phần Lop: " + e.getMessage());
        }

        String sql = "UPDATE phancong SET ";
        // sql += "MonHocid = '" + mamonhoc + "' ,";
        sql += "Lopid = '" + malop + "'";
        sql += "WHERE GiaoVienid='" + pc.getMagv() + "'";
        mySQL.executeUpdate(sql);
        System.out.println(sql); // Đoạn này để kiểm tra xem câu lệnh SQL có đúng không
    }

    public void Add(QLPhanCongDTO pc) {
        MySQLConnect mySQL = new MySQLConnect();
        // String mamonhoc = null;
        String malop = "";

        // String sql1 = "SELECT MonHocid FROM monhoc WHERE TenMonHoc = ?";
        // try (PreparedStatement ps1 = mySQL.getConnection().prepareStatement(sql1)) {
        //     ps1.setString(1, pc.getMon());
        //     try (ResultSet rs1 = ps1.executeQuery()) {
        //         if (rs1.next()) {
        //             mamonhoc = rs1.getString("MonHocid");
        //         }
        //     }
        // } catch (SQLException e) {
        //     System.err.println("Lỗi ở QLPCBUS trong phần MonHoc: " + e.getMessage());
        // }

        String sql2 = "SELECT Lopid FROM lop WHERE TenLop = ?";
        try (PreparedStatement ps2 = mySQL.getConnection().prepareStatement(sql2)) {
            ps2.setString(1, pc.getLop());
            try (ResultSet rs2 = ps2.executeQuery()) {
                if (rs2.next()) {
                    malop = rs2.getString("Lopid");
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi ở QLPCBUS trong phần Lop: " + e.getMessage());
        }

        String n = pc.getMagv();
        System.out.println(n);
        if ( malop != null) {
            String sql = "INSERT INTO phancong (Giaovienid, Lopid , enable)  VALUES (?, ? ,?)";
            try (PreparedStatement ps = mySQL.getConnection().prepareStatement(sql)) {
                ps.setString(1, pc.getMagv());
                ps.setString(2, malop);
                ps.setInt(3, 1);
                ps.executeUpdate();
                System.out.println("Thêm thành công");
            } catch (SQLException e) {
                System.err.println("Lỗi ở QLPCBUS trong phần thêm: " + e.getMessage());
            }
        } else {
            System.err.println("Không tìm thấy Lopid phù hợp");
        }
    }

    public void delete(QLPhanCongDTO pc) {
        MySQLConnect mySQL = new MySQLConnect();
        String malop = null;

        String sql2 = "SELECT Lopid FROM lop WHERE TenLop = ?";
        try (PreparedStatement ps2 = mySQL.getConnection().prepareStatement(sql2)) {
            ps2.setString(1, pc.getLop());
            try (ResultSet rs2 = ps2.executeQuery()) {
                if (rs2.next()) {
                    malop = rs2.getString("Lopid");
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi ở QLPCBUS trong phần Lop: " + e.getMessage());
        }

        String sql = "UPDATE phancong SET enable = '0' WHERE GiaoVienid = ? AND Lopid = ?";
        try (PreparedStatement ps = mySQL.getConnection().prepareStatement(sql)) {
            ps.setString(1, pc.getMagv());
            // ps.setString(2, mamonhoc);
            ps.setString(2, malop);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Lỗi ở QLPCBUS trong phần PhanCong: " + e.getMessage());
        }

        System.out.println(sql);
    }

    public ArrayList<QLPhanCongDTO> checkMaGV() {
        ArrayList<QLPhanCongDTO> dshs = new ArrayList<>();

        String sql = "SELECT HocSinhId FROM hocsinh";
        ResultSet rs = mySQL.executeQuery(sql);
        try {
            while (rs.next()) {
                String magv = rs.getString("GiaoVienid");

                QLPhanCongDTO hocsinh = new QLPhanCongDTO(magv, "", "");
                dshs.add(hocsinh);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dshs;
    }

    // public Integer CountHS() {
    // String sql = "SELECT COUNT(*) AS count FROM hocsinh";
    // Integer count = 0;

    // try {
    // ResultSet rs = mySQL.executeQuery(sql);

    // if (rs.next()) {
    // count = rs.getInt("count");
    // }
    // rs.close();
    // } catch (SQLException e) {
    // e.printStackTrace(); // Xử lý ngoại lệ
    // }
    // return count;
    // }

    public ArrayList getMaGV() {
        ArrayList<String> magvList = new ArrayList<>();
        String sql = " SELECT * FROM giaovien WHERE enable = 1";
        magvList.add("None");
        try (PreparedStatement ps = mySQL.getConnection().prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String magv = rs.getString("GiaoVienid");
                magvList.add(magv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return magvList;
    }

    // public ArrayList getTenMonHoc() {
    //     ArrayList<String> tenmhList = new ArrayList<>();
    //     String sql = " SELECT * FROM monhoc WHERE 1";
    //     tenmhList.add("None");
    //     try (PreparedStatement ps = mySQL.getConnection().prepareStatement(sql);
    //             ResultSet rs = ps.executeQuery()) {
    //         while (rs.next()) {
    //             String tenmh = rs.getString("TenMonHoc");
    //             tenmhList.add(tenmh);
    //         }
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    //     return tenmhList;
    // }

    public ArrayList getTenLop() {
        ArrayList<String> tenlopList = new ArrayList<>();
        String sql = " SELECT * FROM lop WHERE 1";
        tenlopList.add("None");
        try (PreparedStatement ps = mySQL.getConnection().prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String tenlop = rs.getString("TenLop");
                tenlopList.add(tenlop);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tenlopList;
    }

    public String returnName(String magv) {
        String sql = "SELECT TenGiaoVien FROM giaovien WHERE GiaoVienid = '" + magv + "'";
        String tengv = "";
        try (PreparedStatement ps = mySQL.getConnection().prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                tengv = rs.getString("TenGiaoVien");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tengv;
    }

    public boolean checkExist(QLPhanCongDTO pc) {
        MySQLConnect mySQL = new MySQLConnect();
        String malop = null;
    
        // Lấy Lopid từ bảng lop
        String sql2 = "SELECT Lopid FROM lop WHERE TenLop = ?";
        try (PreparedStatement ps2 = mySQL.getConnection().prepareStatement(sql2)) {
            ps2.setString(1, pc.getLop());
            try (ResultSet rs2 = ps2.executeQuery()) {
                if (rs2.next()) {
                    malop = rs2.getString("Lopid");
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi ở QLPCBUS trong phần Lop: " + e.getMessage());
        }
    
        if (malop != null) {
            // Kiểm tra sự tồn tại của Giaovienid và Lopid trong bảng phancong
            String checkSql = "SELECT * FROM phancong WHERE Giaovienid = ? AND Lopid = ?";
            try (PreparedStatement checkPs = mySQL.getConnection().prepareStatement(checkSql)) {
                checkPs.setString(1, pc.getMagv());
                checkPs.setString(2, malop);
                ResultSet rs = checkPs.executeQuery();
                if (rs.next()) {
                    // Bản ghi đã tồn tại
                    return true;  // Phân công đã tồn tại
                }
            } catch (SQLException e) {
                System.err.println("Lỗi kiểm tra sự tồn tại của bản ghi: " + e.getMessage());
            }
        } else {
            System.err.println("Không tìm thấy Lopid phù hợp");
        }
        
        return false;  // Phân công chưa tồn tại
    }
    
    

    public String getIMG(String magv) {
        MySQLConnect mysql = new MySQLConnect();
        String sql = "SELECT IMG FROM giaovien WHERE GiaoVienid = '" + magv + "'";
        String img = null;
        try (PreparedStatement ps = mysql.getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                img = rs.getString("IMG");
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return img;
    }

}
