
package DATA;

import DTO.ChiTietDiemDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import DATABASE.MySQLConnect;

/**
 *
 * @author PHUONG ANH
 */
public class ChiTietDiemDAO {
    private MySQLConnect mySQL=new MySQLConnect();

    public ChiTietDiemDAO(){}
    
    public ArrayList <ChiTietDiemDTO> list(){
        ArrayList <ChiTietDiemDTO> ds=new ArrayList<>();
        try{
            String sql="select * from chitietdiem";
            ResultSet rs=mySQL.executeQuery(sql);
            
            while(rs.next()){
                String id= rs.getString(1);
                String idmh=rs.getString(2);
                String idhk= rs.getString(3);
                int idheso= rs.getInt(4);
                String idnam = rs.getString(5);
                Float diem= rs.getFloat(6);

                ChiTietDiemDTO ctd=new ChiTietDiemDTO(id,idmh,idhk, idheso,idnam,diem);
                ds.add(ctd);
            }
            rs.close();
            mySQL.disConnect();
        }catch(SQLException ex){
            Logger.getLogger(ChiTietDiemDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ds;
    }
    
    public void set(ChiTietDiemDTO ctd) {
        MySQLConnect mySQL = new MySQLConnect();
        String diem = String.valueOf(ctd.getDiem());
        // Check if Diem is less than 0, if so, set it to empty string
        if (Float.parseFloat(diem) < 0 || String.valueOf(ctd.getDiem())==null) {
            diem = "NULL";
        }
        
        String sql = "UPDATE chitietdiem SET ";
        sql += "HocSinhid='" + ctd.getHocSinhID() + "', ";
        sql += "MonHocid='" + ctd.getMonHocID() + "', ";
        sql += "HocKyid='" + ctd.getHocKyID() + "', ";
        sql += "HeSoid='" + ctd.getHeSoID() + "', ";
        sql += "Diem='" + diem + "', ";
        sql += "NamHocid='" + ctd.getNamHocID() + "' ";
        
        // Concatenating conditions for WHERE clause
        sql += " WHERE HocSinhid='" + ctd.getHocSinhID() + "' AND ";
        sql += "MonHocid='" + ctd.getMonHocID() + "' AND ";
        sql += "HocKyid='" + ctd.getHocKyID() + "' AND ";
        sql += "HeSoid='" + ctd.getHeSoID() + "' AND ";
        sql += "NamHocid='" + ctd.getNamHocID() + "'";
        
        System.out.println(sql);
        
        mySQL.executeUpdate(sql);
    }
    
    public void createChiTietDiem(String hocSinhId, String namHocId) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rsMonHoc = null;
        ResultSet rsHocKy = null;

        String monHocQuery = "SELECT MonHocid FROM monhoc";
        String hocKyQuery = "SELECT HocKyid FROM hocky";
        String insertQuery = "INSERT INTO chitietdiem (HocSinhid, MonHocid, HocKyid, HeSoid, NamHocid, Diem) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            // Open connection
            con = mySQL.getConnection();

            // Fetch all MonHocid
            ps = con.prepareStatement(monHocQuery);
            rsMonHoc = ps.executeQuery();
            ArrayList<String> monHocIds = new ArrayList<>();
            while (rsMonHoc.next()) {
                monHocIds.add(rsMonHoc.getString("MonHocid"));
            }

            // Fetch all HocKyid
            ps = con.prepareStatement(hocKyQuery);
            rsHocKy = ps.executeQuery();
            ArrayList<String> hocKyIds = new ArrayList<>();
            while (rsHocKy.next()) {
                hocKyIds.add(rsHocKy.getString("HocKyid"));
            }

            // Now insert for each MonHocid and HocKyid combination
            ps = con.prepareStatement(insertQuery);

            for (String monHocId : monHocIds) {
                for (String hocKyId : hocKyIds) {
                    for (int heSoId = 1; heSoId <= 3; heSoId++) {
                        ps.setString(1, hocSinhId);  // HocSinhid
                        ps.setString(2, monHocId);   // MonHocid
                        ps.setString(3, hocKyId);    // HocKyid
                        ps.setInt(4, heSoId);        // HeSoid (1 to 3)
                        ps.setString(5, namHocId);   // NamHocid

                        // Set Diem as NULL
                        ps.setNull(6, java.sql.Types.FLOAT); 

                        // Execute the insert
                        ps.executeUpdate();
                    }
                }
            }

            System.out.println("Records inserted successfully with Diem set to NULL!");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (rsMonHoc != null) rsMonHoc.close();
                if (rsHocKy != null) rsHocKy.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void delete(ChiTietDiemDTO ctd) {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "UPDATE chitietdiem SET ";
        sql += "Diem=NULL "; // Set Diem to empty string
        
        // Concatenating conditions for all attributes except Diem
        sql += "WHERE HocSinhid='" + ctd.getHocSinhID() + "' AND ";
        sql += "MonHocid='" + ctd.getMonHocID() + "' AND ";
        sql += "HocKyid='" + ctd.getHocKyID() + "' AND ";
        sql += "HeSoid='" + ctd.getHeSoID() + "' AND ";
        sql += "NamHocid='" + ctd.getNamHocID() + "'";
        
        System.out.println(sql);
        
        mySQL.executeUpdate(sql);
    }
    public boolean isAnyDiemNull(String magv) {
        String sql = "SELECT Diem FROM chitietdiem "
                   + "JOIN namhoc ON namhoc.NamHocid = chitietdiem.NamHocid "
                   + "AND chitietdiem.HocKyid = namhoc.HocKy join monhoc on monhoc.MonHocid = chitietdiem.MonHocid JOIN giaovien on giaovien.PhanMon = monhoc.TenMonHoc where namhoc.enable = 1 and giaovien.GiaoVienid = ? and Diem IS NULL";
        
        java.sql.Connection con = null;
        java.sql.PreparedStatement ps = null;
        ResultSet rs = null;
    
        try {
            // Connect to the database
            con = new MySQLConnect().getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, magv);
            // Execute the query
            rs = ps.executeQuery();
            
            // If the result set contains any records, it means Diem is NULL for some records
            if (rs.next()) {
                return true; // Diem IS NULL exists
            }
            
        } catch (SQLException e) {
            e.printStackTrace();  // Log SQL error details
        } finally {
            // Close the resources properly
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return false; // No Diem IS NULL found
    }
    
}
