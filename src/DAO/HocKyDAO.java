package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import DATABASE.MySQLConnect;
import DTO.HocKyDTO;

public class HocKyDAO {
    private MySQLConnect mySQL=new MySQLConnect();

    public HocKyDAO(){}
    
    public ArrayList <HocKyDTO> list(){
        ArrayList <HocKyDTO> ds=new ArrayList<>();
        try{
            String sql="select * from hocky";
            ResultSet rs=mySQL.executeQuery(sql);
            
            while(rs.next()){
                String id= rs.getString(1);
                String tenhk=rs.getString(2);

                HocKyDTO ctd=new HocKyDTO(id,tenhk);
                ds.add(ctd);
            }
            rs.close();
            mySQL.disConnect();
        }catch(SQLException ex){
            Logger.getLogger(HocKyDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ds;
    }
    
    public void set(HocKyDTO ctd) {
            MySQLConnect mySQL = new MySQLConnect();
            String sql = "UPDATE hocky SET ";
            
            sql += "HocKyid='"+ctd.getHocKyID()+"', ";
            sql += "MonHocid='"+ctd.getTenHocKy()+"', ";

            sql += " WHERE HocSinhid='"+ctd.getHocKyID()+"'";
            System.out.println(sql);
            
            mySQL.executeUpdate(sql);
    }
    
    public void add(HocKyDTO ctd) {
        MySQLConnect mySQL = new MySQLConnect();
         String sql = "INSERT INTO hocky VALUES (";
                sql += "'"+ctd.getHocKyID()+"',";
                sql += "'"+ctd.getTenHocKy()+"')";

         System.out.println(sql);
         mySQL.executeUpdate(sql);
    }
    
}
