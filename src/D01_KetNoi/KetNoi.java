/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package D01_KetNoi;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.sql.SQLException;
import java.sql.Connection;


/**
 *
 * @author HP
 */
public class KetNoi {
    
//    public static Connection connection;    

    public static Connection getConnection(String user, String pass) {
            SQLServerDataSource ds = new SQLServerDataSource();
            ds.setUser(user);
            ds.setPassword(pass);
            ds.setPortNumber(Integer.parseInt("1433")); //1433
            ds.setDatabaseName("QLBH");
            Connection connection = null;
            try {
                connection = ds.getConnection();  
                System.out.println("Connected");
            } catch (SQLException ex) {
//                connection = null;
                System.out.println("Can not connect to database");
                System.out.println(ex);
            }
            return connection;
        }
    public static Connection getNewConnection() {
        SQLServerDataSource ds = new SQLServerDataSource();
            ds.setUser("sa");
            ds.setPassword("sa");
            ds.setPortNumber(Integer.parseInt("1433")); //1433
            ds.setDatabaseName("QLBH");
            Connection conn = null;
            try {
                conn = ds.getConnection();
            } catch (SQLException ex) {
                System.out.println("Can not connect to database");
                System.out.println(ex);
            }
            return conn;
    }
}
