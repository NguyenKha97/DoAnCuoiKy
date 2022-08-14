/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package D02_ThucThi;

import D01_KetNoi.KetNoi;
import static Screens.Login.statusLogin;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author admin
 */
public class LoginRun {
    
    
    public static void execute(String username, String pass) throws SQLException {
        if (KetNoi.getConnection(username, pass)!= null) {
            statusLogin = true;
        } else {
            JOptionPane.showMessageDialog(null, "ĐĂNG NHẬP KHÔNG THÀNH CÔNG:\n" + "Quá trình kết nối gặp vấn đề. Vui lòng kiểm tra lại");
        }
    }
}
    

