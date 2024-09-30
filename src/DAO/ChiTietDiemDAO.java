
package DAO;

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
                String idnam = rs.getString(4);
                double diem1= rs.getDouble(5);
                double diem2= rs.getDouble(6);
                double diem3= rs.getDouble(7);
                double dtbMon= rs.getDouble(8);

                ChiTietDiemDTO ctd=new ChiTietDiemDTO(id,idmh,idhk, idnam);
                ctd.setDiem1(diem1);
                ctd.setDiem2(diem2);
                ctd.setDiem3(diem3);
                ctd.setDtbMon(dtbMon);
                ds.add(ctd);
            }
            rs.close();
            mySQL.disConnect();
        }catch(SQLException ex){
            Logger.getLogger(ChiTietDiemDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ds;
    }
    public void add(ChiTietDiemDTO ctd) {

        MySQLConnect mySQL = new MySQLConnect();
         String sql = "INSERT INTO chitietdiem VALUES (";
                sql += "'"+ctd.getHocSinhID()+"',";
                sql += "'"+ctd.getMonHocID()+"',";
                sql += "'"+ctd.getHocKyID()+"',";
                sql += "'"+ctd.getNamHocID()+"',";
                sql += "'"+ctd.getDiem1()+"',";
                sql += "'"+ctd.getDiem2()+"',";
                sql += "'"+ctd.getDiem3()+"',";
                sql += "'"+ctd.getDtbMon()+"')";
         System.out.println(sql);
         mySQL.executeUpdate(sql);
    }
    public void set(ChiTietDiemDTO ctd) {
        MySQLConnect mySQL = new MySQLConnect();

        String sql = "UPDATE chitietdiem SET ";
        sql += "HocSinhid='" + ctd.getHocSinhID() + "', ";
        sql += "MonHocid='" + ctd.getMonHocID() + "', ";
        sql += "HocKyid='" + ctd.getHocKyID() + "', ";
        sql += "Diem1='" + ctd.getDiem1() + "', ";
        sql += "Diem2='" + ctd.getDiem2() + "', ";
        sql += "Diem3='" + ctd.getDiem3() + "', ";
        sql += "dtbMon='" + ctd.getDtbMon() + "', ";
        sql += "NamHocid='" + ctd.getNamHocID() + "' ";
        
        // Concatenating conditions for WHERE clause
        sql += " WHERE HocSinhid='" + ctd.getHocSinhID() + "' AND ";
        sql += "MonHocid='" + ctd.getMonHocID() + "' AND ";
        sql += "HocKyid='" + ctd.getHocKyID() + "' AND ";
        sql += "NamHocid='" + ctd.getNamHocID() + "'";
        
        System.out.println(sql);
        
        mySQL.executeUpdate(sql);
    }
    
    
    public void delete(ChiTietDiemDTO ctd) {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "UPDATE chitietdiem SET ";
        sql += "Diem=NULL "; // Set Diem to empty string
        
        // Concatenating conditions for all attributes except Diem
        sql += "WHERE HocSinhid='" + ctd.getHocSinhID() + "' AND ";
        sql += "MonHocid='" + ctd.getMonHocID() + "' AND ";
        sql += "HocKyid='" + ctd.getHocKyID() + "' AND ";
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
