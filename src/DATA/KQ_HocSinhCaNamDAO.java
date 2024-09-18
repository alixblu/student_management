package DATA;
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
                Float diemtb = rs.getFloat(5);
                String kq = rs.getString(6);

                KQ_HocSinhCaNamDTO k = new KQ_HocSinhCaNamDTO(id, namhocid, hocluc, hanhkiem, diemtb, kq);
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
        System.out.println("error here");
        MySQLConnect mySQL = new MySQLConnect();
        Float diemTrungBinhNam = kqHS.getDiemTrungBinhNam();
        
        // Check if Diemtb is null or less than 0, if so, set it to NULL
        if (diemTrungBinhNam == null || diemTrungBinhNam < 0) {
            diemTrungBinhNam = null;
        }
        
        String sql = "UPDATE kqhocsinhcanam SET ";
        sql += "HocSinhid='" + kqHS.getHocSinhID() + "', ";
        sql += "NamHocid='" + kqHS.getNamHocID() + "', ";
        sql += "HocLuc='" + kqHS.getHocLuc() + "', ";
        sql += "HanhKiem='" + kqHS.getHanhKiem() + "', ";
        // Handling NULL value for Diemtb
        if (diemTrungBinhNam != null) {
            sql += "Diemtb=" + diemTrungBinhNam + ", ";
        } else {
            sql += "Diemtb=NULL, ";
        }
        sql += "KetQua='" + kqHS.getKetQua() + "' ";
        
        // Concatenating conditions for WHERE clause
        sql += " WHERE HocSinhid='" + kqHS.getHocSinhID() + "' AND ";
        sql += "NamHocid='" + kqHS.getNamHocID() + "'";
        
        System.out.println(sql);
        
        mySQL.executeUpdate(sql);
    }
    

    public void add(KQ_HocSinhCaNamDTO kqHS) {
        float diemFloat = kqHS.getDiemTrungBinhNam() != null ? kqHS.getDiemTrungBinhNam() : -1; // Default value if null
        String diem = diemFloat >= 0 ? String.valueOf(diemFloat) : "NULL";
    
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "INSERT INTO kqhocsinhcanam (HocSinhID, NamHocID, HocLuc, HanhKiem, Diemtb, KetQua) VALUES (";
        sql += "'" + kqHS.getHocSinhID() + "',";
        sql += "'" + kqHS.getNamHocID() + "',";
        sql += "'" + kqHS.getHocLuc() + "',";
        sql += "'" + kqHS.getHanhKiem() + "',";
        sql += (diem.equals("NULL") ? diem : "'" + diem + "'") + ",";
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


