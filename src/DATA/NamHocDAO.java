package DATA;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mysql.cj.protocol.Resultset;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import DATABASE.MyConnection;
import DATABASE.MySQLConnect;
import DTO.NamHocDTO;

public class NamHocDAO {
    private MySQLConnect mySQL=new MySQLConnect();

    public NamHocDAO(){}
   
    public ArrayList <NamHocDTO> list(){
        ArrayList <NamHocDTO> dsNH=new ArrayList<>();
        try{
            String sql="select * from namhoc";
            ResultSet rs=mySQL.executeQuery(sql);
            
            while(rs.next()){
                String id= rs.getString(1);
                int bd=rs.getInt(2);
                int kt= rs.getInt(3);
                String hocky = rs.getString(4);

                NamHocDTO ctd=new NamHocDTO(id,bd, kt,hocky);
                dsNH.add(ctd);
            }
            rs.close();
            mySQL.disConnect();
        }catch(SQLException ex){
            Logger.getLogger(NamHocDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dsNH;
    }
    
    public void set(NamHocDTO ctd) {
            MySQLConnect mySQL = new MySQLConnect();
            String sql = "UPDATE namhoc SET ";
            sql += "NamHocid='"+ctd.getNamHocID()+"', ";
            sql += "HocKyid='"+ctd.getNamHocBatDau()+"', ";
            sql += "MonHocid='"+ctd.getNamHocKetThuc()+"', ";
            sql += " WHERE NamHocid='"+ctd.getNamHocID()+"'";
            System.out.println(sql);
            mySQL.executeUpdate(sql);
    }
    
    public void add(NamHocDTO nh) {
        MySQLConnect mySQL = new MySQLConnect();
         String sql = "INSERT INTO namhoc VALUES (";
                sql += "'"+nh.getNamHocID()+"',";
                sql += "'"+nh.getNamHocBatDau()+"',";
                sql += "'"+nh.getNamHocKetThuc()+"',";
         System.out.println(sql);
         mySQL.executeUpdate(sql);
    }

    public void delete(String id) {
        String sql = "DELETE FROM namhoc WHERE NamHocid  = ?";
        java.sql.Connection con = null;
        java.sql.PreparedStatement ps = null;
        try {
            con = MyConnection.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, id);
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Successfully deleted MonHocid: " + id);
                } else {
                    System.out.println("No record found with MonHocid: " + id);
                }
            } else {
                System.out.println("Failed to establish connection.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                if (con != null) MyConnection.closeConnection(con);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    //SQL thêm
    public void Add(NamHocDTO nh) {
        String sql = "INSERT INTO namhoc (NamHocid  , NamBatDau , NamKetThuc , HocKy, enable) VALUES ( ?, ?, ?, ?, ?)";
        try (java.sql.PreparedStatement ps = mySQL.getConnection().prepareStatement(sql)) {
            ps.setString(1, nh.getNamHocID());
            ps.setInt(2, nh.getNamHocBatDau());
            ps.setInt(3, nh.getNamHocKetThuc());
            ps.setString(4, nh.getHocKy());
            ps.setInt(5, nh.getEnable());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //sql cập nhật
    // public void Update(NamHocDTO nh) {
    //     MySQLConnect mysql = new MySQLConnect();
    //     String sql = "UPDATE namhoc SET ";
    //     sql += "NamBatDau = '" + nh.getNamHocID() + "' ,";
    //     sql += "NamKetThuc = '" + nh.getNamHocBatDau() + "' ,";  
    //     sql += "WHERE NamHocid ='" + nh.getNamHocKetThuc() + "'";
    //     mysql.executeUpdate(sql);
    //     System.out.println(sql); // Đoạn này để kiểm tra xem câu lệnh SQL có đúng không
    // }
    //  public ArrayList<NamHocDTO> checkMaNH() {
    //     ArrayList<NamHocDTO> dsnh = new ArrayList<>();

    //     String sql = "SELECT NamHocid  FROM namhoc";
    //     ResultSet rs = mySQL.executeQuery(sql);
    //     try {
    //         while (rs.next()) {
    //             String manh = rs.getString("NamHocid ");

    //             NamHocDTO hocsinh = new NamHocDTO(manh, 0, 0);
    //             dsnh.add(hocsinh);
    //         }
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    //     return dsnh;
    // }

    public void Update(NamHocDTO nh) {
        String sql = "UPDATE namhoc SET NamBatDau = ?, NamKetThuc = ? WHERE NamHocid = ?, WHERE HocKy = ?";
        java.sql.Connection con = null;
        java.sql.PreparedStatement ps = null;

        try {
            con = MyConnection.getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setInt(1, nh.getNamHocBatDau());
                ps.setInt(2, nh.getNamHocKetThuc());
                ps.setString(3, nh.getNamHocID());
                ps.setString(4, nh.getHocKy());

                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Successfully updated NamHocID: " + nh.getNamHocID());
                } else {
                    System.out.println("No record found with NamHocID: " + nh.getNamHocID());
                }
            } else {
                System.out.println("Failed to establish connection.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null)
                    ps.close();
                if (con != null)
                    MyConnection.closeConnection(con);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public int ktraEnable(String manamhoc) throws SQLException {
        int enable = 0; // Khởi tạo biến enable
    
        String sql = "select enable from namhoc where NamHocid = '"+manamhoc+"'";
    
        ResultSet rs = mySQL.executeQuery(sql);
        
        if (rs.next()) {
            enable = rs.getInt(1); 
        }
        return enable; 
    }
    
    public void updateEnable(String namhocid) {
        String sql = "UPDATE namhoc SET enable = 1 WHERE NamHocid = ?";
        
        try (java.sql.Connection con = MyConnection.getConnection();
             java.sql.PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, namhocid);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
