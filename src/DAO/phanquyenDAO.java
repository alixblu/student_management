package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import DATABASE.MySQLConnect;
import DTO.chitietquyenDTO;
import DTO.chucnangDTO;
import DTO.phanquyenDTO;

public class phanquyenDAO {
    MySQLConnect mysql = new MySQLConnect();

    public ArrayList<phanquyenDTO> listQuyen() {
        ArrayList<phanquyenDTO> dsquyen = new ArrayList<>();
        String sql = "SELECT * FROM quyen WHERE enable = 1"; // Chỉnh sửa câu truy vấn SQL

        try {
            ResultSet rs = mysql.executeQuery(sql); // Thực thi truy vấn và nhận kết quả

            // Duyệt qua kết quả và thêm vào danh sách quyền
            while (rs.next()) {
                String maQuyen = rs.getString("maquyen");
                String tenQuyen = rs.getString("tenquyen");
                phanquyenDTO quyen = new phanquyenDTO(maQuyen, tenQuyen);
                dsquyen.add(quyen);
            }

            rs.close(); // Đóng ResultSet sau khi sử dụng
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dsquyen;
    }

    public ArrayList<chitietquyenDTO> listChiTietQuyen(String maquyen) {
        ArrayList<chitietquyenDTO> dschitietquyen = new ArrayList<>();
        String sql = "SELECT * FROM chitietquyen WHERE maquyen = '" + maquyen + "' AND enable = 1"; // Chỉnh sửa câu
                                                                                                    // truy
                                                                                                    // vấn SQL

        try {
            ResultSet rs = mysql.executeQuery(sql); // Thực thi truy vấn và nhận kết quả

            // Duyệt qua kết quả và thêm vào danh sách quyền
            while (rs.next()) {
                String maQuyen = rs.getString("maquyen");
                String tenQuyen = rs.getString("machucnang");
                chitietquyenDTO ctquyen = new chitietquyenDTO(maQuyen, tenQuyen);
                dschitietquyen.add(ctquyen);
            }

            rs.close(); // Đóng ResultSet sau khi sử dụng
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dschitietquyen;
    }

    public ArrayList<chucnangDTO> listChucNang() {
        ArrayList<chucnangDTO> dsquyen = new ArrayList<>();
        String sql = "SELECT * FROM chucnang WHERE  1"; // Chỉnh sửa câu truy vấn SQL

        try {
            ResultSet rs = mysql.executeQuery(sql); // Thực thi truy vấn và nhận kết quả

            // Duyệt qua kết quả và thêm vào danh sách quyền
            while (rs.next()) {
                String machucnang = rs.getString("machucnang");
                String tenchucnang = rs.getString("tenchucnang");
                String img = rs.getString("img");
                chucnangDTO cn = new chucnangDTO(machucnang, tenchucnang, img);
                dsquyen.add(cn);
            }

            rs.close(); // Đóng ResultSet sau khi sử dụng
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dsquyen;
    }

    public void addQuyen(phanquyenDTO quyen) {
        MySQLConnect mysql = new MySQLConnect();
        String sql = "INSERT INTO quyen (maquyen, tenquyen, enable) VALUES ('" + quyen.getMaquyen() + "', '"
                + quyen.getTenquyen() + "', 1);";
        mysql.executeUpdate(sql);
        System.out.println(sql);
    }

    public boolean checkExist(String maquyen) {
        MySQLConnect mysql = new MySQLConnect();
        String sql = "SELECT * FROM quyen WHERE enable = 1";
        try {
            ResultSet rs = mysql.executeQuery(sql);
            while (rs.next()) {
                String maQuyen = rs.getString("maquyen");
                String tenQuyen = rs.getString("tenquyen");
                phanquyenDTO quyen = new phanquyenDTO(maQuyen, tenQuyen);
                if (quyen.getMaquyen().equals(maquyen)) {
                    return true;
                }
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public void addChitietquyen(chitietquyenDTO ctq) {
        MySQLConnect mysql = new MySQLConnect();
        String sql = "INSERT INTO chitietquyen (maquyen, machucnang) VALUES ('" + ctq.getMaquyen() + "', '"
                + ctq.getMachucnang() + "')";
        mysql.executeUpdate(sql);
        System.out.println(sql);
    }

    public void deleteQuyen(String maquyen) {
        MySQLConnect mysql = new MySQLConnect();
        String sql = "UPDATE quyen SET enable = 0 WHERE maquyen = '" + maquyen + "'";
        mysql.executeUpdate(sql);
        System.out.println(sql);
    }

    public void deteleChitietQuyen(String maquyen) {
        MySQLConnect mysql = new MySQLConnect();
        String sql = "UPDATE chitietquyen SET enable = 0 WHERE maquyen = '" + maquyen + "'";
        mysql.executeUpdate(sql);
        System.out.println(sql);
    }

}
