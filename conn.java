// ==================== FILE 1: conn.java ====================
package hotel.management.system;

import java.sql.*;

public class conn {
    Connection c;
    Statement s;
    
    public conn() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/hms", "root", "");
            s = c.createStatement();
        } catch (Exception e) {
            System.out.println("Database Connection Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void closeConnection() {
        try {
            if (s != null) s.close();
            if (c != null) c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}