
package DATA;

import DTO.MonHocDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import DATABASE.MySQLConnect;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import DATABASE.MySQLConnect;

/**
 *
 * @author PHUONG ANH
 */
public class MonHocDAO {
    private MySQLConnect mySQL=new MySQLConnect();

    public MonHocDAO(){}
    
    public ArrayList <MonHocDTO> list(){
        ArrayList <MonHocDTO> ds=new ArrayList<>();
        try{
            String sql="select * from monhoc";
            ResultSet rs=mySQL.executeQuery(sql);
            while(rs.next()){
                String id= rs.getString("MonHocid");
                String idlop=rs.getString("TenMonHoc");
                MonHocDTO ml=new MonHocDTO(id,idlop);
                ds.add(ml);
            }
            rs.close();
            mySQL.disConnect();
        }catch(SQLException ex){
            Logger.getLogger(MonHocDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ds;
    }
    
    public void set(MonHocDTO ml) {
            MySQLConnect mySQL = new MySQLConnect();
            String sql = "UPDATE monhoc SET ";
            sql += "MonHocid='"+ml.getMonHocID()+"', ";
            
            sql += "TenMonHoc='"+ml.getTenMonHoc()+"', ";

            sql += " WHERE MonHocid='"+ml.getMonHocID()+"'";
            System.out.println(sql);
           
            mySQL.executeUpdate(sql);
    }

    public void add(MonHocDTO mh) {
        String sql = "INSERT INTO monhoc (MonHocid , TenMonHoc) VALUES ( ?, ?)";
        try (java.sql.PreparedStatement ps = mySQL.getConnection().prepareStatement(sql)) {
            ps.setString(1, mh.getMonHocID());
            ps.setString(2, mh.getTenMonHoc());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    
    public void delete(String id) {
        String sql = "DELETE FROM monhoc WHERE MonHocid = ?";
        java.sql.Connection con = null;
        java.sql.PreparedStatement ps = null;
        try {
            con = new MySQLConnect().getConnection();
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
               
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }



    public void Update(MonHocDTO mh) {
        String sql = "UPDATE monhoc SET TenMonHoc = ? WHERE MonHocid = ?";
        java.sql.Connection con = null;
        java.sql.PreparedStatement ps = null;
       
        try {
            con = new MySQLConnect().getConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, mh.getTenMonHoc());
                ps.setString(2, mh.getMonHocID());
                
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Successfully updated MonHocid: " + mh.getMonHocID());
                } else {
                    System.out.println("No record found with MonHocid: " + mh.getMonHocID());
                }
            } else {
                System.out.println("Failed to establish connection.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<MonHocDTO> checkMaMH() {
        ArrayList<MonHocDTO> dsmh = new ArrayList<>();

        String sql = "SELECT MonHocid  FROM monhoc";
        ResultSet rs = mySQL.executeQuery(sql);
        try {
            while (rs.next()) {
                String mamh = rs.getString("MonHocid");
                MonHocDTO monhoc = new MonHocDTO(mamh, "");            
                dsmh.add(monhoc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsmh;
    }
    
    public Integer CountMH() {
        String sql = "SELECT COUNT(*) AS count FROM monhoc";
        Integer count = 0;

        try {
            ResultSet rs = mySQL.executeQuery(sql);

            if (rs.next()) {
                count = rs.getInt("count");
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý ngoại lệ
        }
        return count;
    }
}
