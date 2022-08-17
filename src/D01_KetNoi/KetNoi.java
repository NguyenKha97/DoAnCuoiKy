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
    private static final String user = "sa";
    private static final String pass = "sa";

    public static Connection getConnection() {
        SQLServerDataSource ds = new SQLServerDataSource();
            ds.setUser(user);
            ds.setPassword(pass);
            ds.setPortNumber(Integer.parseInt("1433")); //1433
            ds.setDatabaseName("QLBH");
            Connection connection = null;
            try {
                connection = ds.getConnection();
            } catch (SQLException ex) {
                System.out.println("Can not connect to database");
                System.out.println(ex);
            }
            return connection;
    }
}
