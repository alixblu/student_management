/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.LopDTO;
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
public class LopDAO {
    private MySQLConnect mySQL=new MySQLConnect();

    public LopDAO(){}
    
    public ArrayList <LopDTO> list(){
        ArrayList <LopDTO> ds=new ArrayList<>();
        try{
            String sql="select * from lop";
            ResultSet rs=mySQL.executeQuery(sql);
            
            while(rs.next()){
                
                String id=rs.getString("Lopid");
                String ten=rs.getString("TenLop");
                
                LopDTO lop=new LopDTO(id,ten);
                ds.add(lop);
            }
            rs.close();
            mySQL.disConnect();
        }catch(SQLException ex){
            Logger.getLogger(LopDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ds;
    }
    
    public void set(LopDTO lop) {
            MySQLConnect mySQL = new MySQLConnect();
            String sql = "UPDATE hocsinh SET ";
            sql += "Lopid='"+lop.getLopID()+"', ";
            sql += "TenLop='"+lop.getTenLop()+"', ";

            sql += " WHERE Lopid='"+lop.getTenLop()+"'";
            System.out.println(sql);
            
            mySQL.executeUpdate(sql);
    }
    
    public void add(LopDTO lop) {
        MySQLConnect mySQL = new MySQLConnect();
         String sql = "INSERT INTO nhanvien VALUES (";
                sql += "'"+lop.getLopID()+"',";
                sql += "'"+lop.getTenLop()+"',";

         System.out.println(sql);
         mySQL.executeUpdate(sql);
    }
    
    public void delete(LopDTO lop){
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "";
        
            //xoa phancong
            sql = "delete phancong where Lopid ='"+lop.getLopID()+"'";
            mySQL.executeUpdate(sql);

            //xoa phanlop
            sql = "delete phanlop where Lopid ='"+lop.getLopID()+"'";
            mySQL.executeUpdate(sql);
            
            //xoa lop
            sql = "delete lop where Lopid ='"+lop.getLopID()+"'";
            mySQL.executeUpdate(sql);
    }

    public ArrayList<String> list_Tenlop()
    {
        ArrayList<String> listTenLop = new ArrayList<>();
        try{
            String sql="select TenLop from lop";
            ResultSet rs=mySQL.executeQuery(sql);
            
            while(rs.next()){
                String ten=rs.getString("TenLop");
                listTenLop.add(ten);
            }
            rs.close();
            mySQL.disConnect();
        }catch(SQLException ex){
            Logger.getLogger(LopDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listTenLop;
    }

    public String getIdByCondition(String tenlop) {
        String idlop = "";
        try {
            String sql = "SELECT Lopid FROM lop WHERE tenlop = '" + tenlop + "'";
            ResultSet rs = mySQL.executeQuery(sql);
            if (rs.next()) {
                idlop = rs.getString("Lopid"); // Retrieve Lopid from the result set
            }
            mySQL.disConnect();
        } catch (SQLException ex) {
            Logger.getLogger(LopDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return idlop;
    }
    

    public ArrayList<String> list_Tenlop(String khoi)
    {
        ArrayList<String> listTenLop = list_Tenlop();
        ArrayList<String> listTenLop_Khoi = new ArrayList<>();
        for(int i=0;i<listTenLop.size();i++)
        {
            String tmp = String.valueOf(listTenLop.get(i).charAt(0)) + listTenLop.get(i).charAt(1);
            if(khoi.equals(tmp))
            {
                listTenLop_Khoi.add(listTenLop.get(i));
            }
        }
        return listTenLop_Khoi;
    }

    public static void main(String[] args) {
       String id = new LopDAO().getIdByCondition("10A1");
       System.out.println(id);
    }

}
