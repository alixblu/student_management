/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.PhanLopDTO;
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
public class PhanLopDAO {
    private MySQLConnect mySQL=new MySQLConnect();

    public PhanLopDAO(){}
    
    public ArrayList <PhanLopDTO> list(){
        ArrayList <PhanLopDTO> ds=new ArrayList<>();
        try{
            String sql="select * from phanlop";
            ResultSet rs=mySQL.executeQuery(sql);
            
            while(rs.next()){
                String id= rs.getString("HocSinhid");
                String idlop=rs.getString("Lopid");
                String idnam=rs.getString("NamHocid");

                PhanLopDTO pl=new PhanLopDTO(id,idlop,idnam);
                ds.add(pl);
            }
            rs.close();
            mySQL.disConnect();
        }catch(SQLException ex){
            Logger.getLogger(PhanLopDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ds;
    }

    public void set(PhanLopDTO pl) {
            MySQLConnect mySQL = new MySQLConnect();
            String sql = "UPDATE phanlop SET ";
            sql += "HocSinhid='"+pl.getHocSinhID()+"', ";
            sql += "Lopid='"+pl.getLopID()+"', ";
            sql += "NamHocid='"+pl.getNamHocID()+"', ";

            sql += " WHERE HocSinhid='"+pl.getHocSinhID()+"'";
            System.out.println(sql);
            
            mySQL.executeUpdate(sql);
    }
    
    public void add(PhanLopDTO pl) {
        MySQLConnect mySQL = new MySQLConnect();
         String sql = "INSERT INTO phanlop VALUES (";
                sql += "'"+pl.getHocSinhID()+"',";
                sql += "'"+pl.getLopID()+"',";
                sql += "'"+pl.getNamHocID()+"')";
         mySQL.executeUpdate(sql);
    }
    
    public void delete(PhanLopDTO pl){
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "delete phanlop where HocSinhid = '"+pl.getHocSinhID()+"'";
        mySQL.executeUpdate(sql);

    }
}
