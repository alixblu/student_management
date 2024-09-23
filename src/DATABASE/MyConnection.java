package DATABASE;
//

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
    

public class MyConnection {

    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3307/student_management?zeroDateTimeBehavior=CONVERT_TO_NULL", 
                "root", 
                ""
            );
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    public static void closeConnection(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
               e.printStackTrace();
            }
        }
    }
}