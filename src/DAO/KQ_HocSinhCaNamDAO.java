package DAO;
import DTO.KQ_HocSinhCaNamDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import DATABASE.MySQLConnect;

public class KQ_HocSinhCaNamDAO {
    private MySQLConnect mySQL=new MySQLConnect();

    public KQ_HocSinhCaNamDAO(){

    }

    public ArrayList <KQ_HocSinhCaNamDTO> list(){
        ArrayList <KQ_HocSinhCaNamDTO> ds = new ArrayList<>();
        try{
            String sql = "select * from kqhocsinhcanam";

            ResultSet rs=mySQL.executeQuery(sql);
            
            while(rs.next()){
                String id = rs.getString(1);
                String namhocid = rs.getString(2);
                String hocluc = rs.getString(3);
                String hanhkiem = rs.getString(4);
                Double diem = rs.getDouble(5);
                String kq = rs.getString(6);

                KQ_HocSinhCaNamDTO k = new KQ_HocSinhCaNamDTO(id, namhocid);
                k.setHocLuc(hocluc);
                k.setHanhKiem(hanhkiem);
                k.setKetQua(kq);
                k.setDiemTrungBinhNam(diem);
                ds.add(k);
            }
            rs.close();
            mySQL.disConnect();

        }catch(SQLException e){
            Logger.getLogger(KQ_HocSinhCaNamDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return ds;
    }

    public void set(KQ_HocSinhCaNamDTO kqHS) {
        System.out.println("error here set(KQ_HocSinhCaNamDTO kqHS)");
        MySQLConnect mySQL = new MySQLConnect();        
        String sql = "UPDATE kqhocsinhcanam SET ";
        sql += "HocSinhid='" + kqHS.getHocSinhID() + "', ";
        sql += "NamHocid='" + kqHS.getNamHocID() + "', ";
        sql += "HocLuc='" + kqHS.getHocLuc() + "', ";
        sql += "HanhKiem='" + kqHS.getHanhKiem() + "', ";
        sql += "KetQua='" + kqHS.getKetQua() + "', ";
        sql += "Diemtb='" + kqHS.getDiemTrungBinhNam() + "', ";

        sql += " WHERE HocSinhid='" + kqHS.getHocSinhID() + "' AND ";
        sql += "NamHocid='" + kqHS.getNamHocID() + "'";

        System.out.println(sql);
        
        mySQL.executeUpdate(sql);
    }
    

    public void add(KQ_HocSinhCaNamDTO kqHS) {

        MySQLConnect mySQL = new MySQLConnect();
        String sql = "INSERT INTO kqhocsinhcanam (HocSinhID, NamHocID, HocLuc, HanhKiem, Diemtb, KetQua) VALUES (";
        sql += "'" + kqHS.getHocSinhID() + "',";
        sql += "'" + kqHS.getNamHocID() + "',";
        sql += "'" + kqHS.getHocLuc() + "',";
        sql += "'" + kqHS.getHanhKiem() + "',";
        sql +=  kqHS.getDiemTrungBinhNam() + ",";
        sql += "'" + kqHS.getKetQua() + "')";
    
        System.out.println(sql);
        mySQL.executeUpdate(sql);
    }
    

    public void delete(KQ_HocSinhCaNamDTO kqHS) {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "UPDATE kqhocsinhcanam SET ";
        sql += "HocLuc = NULL, ";
        sql += "Diemtb = NULL, ";
        sql += "KetQua = NULL ";
        
        // Concatenating conditions for WHERE clause
        sql += "WHERE HocSinhid='" + kqHS.getHocSinhID() + "' AND ";
        sql += "NamHocid='" + kqHS.getNamHocID() + "'";
        
        System.out.println(sql);
        
        mySQL.executeUpdate(sql);
    }
    

}


