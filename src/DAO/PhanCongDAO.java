/*
 * Click nbfs://nbhost/SystemFileSystem/Tempcates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Tempcates/Classes/Class.java to edit this tempcate
 */
package DAO;

import DTO.PhanCongDTO;
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
public class PhanCongDAO {
    private MySQLConnect mySQL=new MySQLConnect();

    public PhanCongDAO(){}
    
    public ArrayList <PhanCongDTO> list(){
        ArrayList <PhanCongDTO> ds=new ArrayList<>();
        try{
            String sql="select * from phancong where enable = 1";
            ResultSet rs=mySQL.executeQuery(sql);
            
            while(rs.next()){
                String id= rs.getString("GiaoVienid");
                String idlop=rs.getString("Lopid");
                // String idmh=rs.getString("MonHocid");

                // PhanCongDTO pc=new PhanCongDTO(id,idlop,idmh);
                PhanCongDTO pc=new PhanCongDTO(id,idlop);
                ds.add(pc);
            }
            rs.close();
            mySQL.disConnect();
        }catch(SQLException ex){
            Logger.getLogger(PhanCongDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ds;
    }

    public void set(PhanCongDTO pc) {
            MySQLConnect mySQL = new MySQLConnect();
            String sql = "UPDATE phancong SET ";
            sql += "GiaoVienid='"+pc.getGiaoVienID()+"', ";
            sql += "Lopid='"+pc.getLopID()+"', ";
            // sql += "MonHocid='"+pc.getMonHocID()+"', ";

            sql += " WHERE GiaoVienid='"+pc.getGiaoVienID()+"'";
            System.out.println(sql);
            
            mySQL.executeUpdate(sql);
    }
    
    public void add(PhanCongDTO pc) {
        MySQLConnect mySQL = new MySQLConnect();
         String sql = "INSERT INTO phancong VALUES (";
                sql += "'"+pc.getGiaoVienID()+"',";
                sql += "'"+pc.getLopID()+"',";
                // sql += "'"+pc.getMonHocID()+"')";

         System.out.println(sql);
         mySQL.executeUpdate(sql);
    }
    
    public void delete(PhanCongDTO pc){
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "delete phancong where GiaoVienid = '"+pc.getGiaoVienID()+"'";
        mySQL.executeUpdate(sql);

    }
}
