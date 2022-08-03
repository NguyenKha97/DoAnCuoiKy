/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package D02_ThucThi;

import D01_KetNoi.KetNoi;
import Screens.Login;
import static Screens.Login.statusLogin;
import Screens.Main;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author admin
 */
public class LoginRun {
    
    public static Connection con;
    
    public static void execute(String username, String pass) throws SQLException{
        Login frmLogin = new Login();

        con = KetNoi.getConnection(username, pass);
        if(con != null){
            statusLogin = true;   
        }
        else
            JOptionPane.showMessageDialog(null, "ĐĂNG NHẬP KHÔNG THÀNH CÔNG:\n" + "Mật khẩu hoặc tên tài khoản không đúng, vui lòng nhập lại");

        if(statusLogin == true){
            Main frmMain = new Main();
            frmLogin.setVisible(false);
            frmMain.setVisible(true);
        }
    }
    
}
