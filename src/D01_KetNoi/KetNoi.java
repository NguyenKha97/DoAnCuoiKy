/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package D01_KetNoi;

import Screens.Login;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.sql.SQLException;
import java.sql.Connection;


/**
 *
 * @author HP
 */
public class KetNoi {
    
    private static Connection connection;
    
    public static Connection getConnection(String user, String pass){
        
//        try {
//                if (connection != null && !connection.isClosed()) {
//                    return connection;
//		}
//            } catch (SQLException e1) {
//                // TODO Auto-generated catch block
//		e1.printStackTrace();
//            }
        
        SQLServerDataSource ds = new SQLServerDataSource();
        
        ds.setUser(user);
        ds.setPassword(pass);        
//        ds.setServerName("DESKTOP-0RC4ARU\\SQLEXPRESS");
        ds.setPortNumber(Integer.parseInt("1433")); //1433
        ds.setDatabaseName("QLBH");
        
        try {
            connection = ds.getConnection();
            System.out.println("Connected");
        } catch (SQLException ex) {
            connection = null;
            System.out.println("Can not connect");
            System.out.println(ex);
        }
        return connection;
        
    }
}
