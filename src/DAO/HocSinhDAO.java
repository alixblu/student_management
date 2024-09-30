/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import DTO.HocSinhDTO;
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
public class HocSinhDAO {
    private MySQLConnect mySQL=new MySQLConnect();
    public HocSinhDAO(){
    }
    
    public ArrayList <HocSinhDTO> list(){
        ArrayList <HocSinhDTO> dsHS=new ArrayList<>();
        try{
            String sql="select * from hocsinh where enable = 1";
            ResultSet rs=mySQL.executeQuery(sql);
            
            while(rs.next()){
                String id= rs.getString("HocSinhid");
                String hoten=rs.getString("HoVaTen");
                String gt=rs.getString("GioiTinh");
                String ns=rs.getString("NgaySinh");
                String dt=rs.getString("DienThoai");
                String dc=rs.getString("DiaChi");
                String hp=rs.getString("HocPhi");
                String img=  rs.getString("IMG");
                HocSinhDTO hs=new HocSinhDTO(id,hoten,gt,ns,dt,dc);
                hs.setHocPhi(hp);
                hs.setIMG(img);
                dsHS.add(hs);
            }
            rs.close();
            mySQL.disConnect();
        }catch(SQLException ex){
            Logger.getLogger(HocSinhDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dsHS;
    }
    
    public void set(HocSinhDTO hs) {
            MySQLConnect mySQL = new MySQLConnect();
            String sql = "UPDATE hocsinh SET ";
            sql += "HocSinhid='"+hs.getHocSinhID()+"', ";
            sql += "HoVaTen='"+hs.getTenHocSinh()+"', ";
            sql += "GioiTinh='"+hs.getGioiTinh()+"', ";
            sql += "NgaySinh='"+hs.getNgaySinh()+"', ";
            sql += "DienThoai='"+hs.getDienThoai()+"', ";
            sql += "DiaChi='"+hs.getDiaChi()+"', ";
            sql += "HocPhi='"+hs.getHocPhi()+"', ";
            sql += "IMG='"+hs.getIMG()+"' ";
            sql += " where HocSinhid='"+hs.getHocSinhID()+"'";
            System.out.println(sql);
            
            mySQL.executeUpdate(sql);
    }
    
    public void add(HocSinhDTO hs) {
        MySQLConnect mySQL = new MySQLConnect();
         String sql = "INSERT INTO hocsinh VALUES (";
                sql += "'"+hs.getHocSinhID()+"',";
                sql += "'"+hs.getTenHocSinh()+"',";
                sql += "'"+hs.getGioiTinh()+"',";
                sql += "'"+hs.getNgaySinh()+"',";
                sql += "'"+hs.getDienThoai()+"',";
                sql += "'"+hs.getDiaChi()+"',";
                sql += "'"+hs.getHocPhi()+"',";
                sql += "'"+hs.getIMG()+"',";
                sql += "'1')";
         System.out.println(sql);
         mySQL.executeUpdate(sql);
    }
    
    public void delete(String id){
    
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "UPDATE hocsinh SET enable = 0 WHERE HocSinhid='"+id+"'";
        mySQL.executeUpdate(sql);
        System.out.println(sql);
    }

    public void addToNextGrade(){
        
    }
    public static void main(String[] args) {
    HocSinhDAO test = new HocSinhDAO();
    ArrayList<HocSinhDTO> list = test.list();
    
    // Print the data of each HocSinhDTO object
    for (HocSinhDTO hs : list) {
        System.out.println("HocSinhID: " + hs.getHocSinhID());
        System.out.println("HoVaTen: " + hs.getTenHocSinh());
        System.out.println("GioiTinh: " + hs.getGioiTinh());
        System.out.println("NgaySinh: " + hs.getNgaySinh());
        System.out.println("DienThoai: " + hs.getDienThoai());
        System.out.println("DiaChi: " + hs.getDiaChi());
        System.out.println("HocPhi: " + hs.getHocPhi());
        System.out.println("IMG: " + hs.getIMG());
        System.out.println(); // for readability
    }

    }

}
