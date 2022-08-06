/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package doancuoiky;

import Screens.Login;


/**
 *
 * @author admin
 */
public class DoAnCuoiKy {

    /**
     * @param args the command line arguments
     */
    
        public String tangMa(String temp){
        String haiKiTuDau = temp.substring(0, 2);
        String haiKiTuSau = temp.substring(2);
        int x = Integer.parseInt(haiKiTuSau);
        String so = "0";
        if(++x < 9)
            so = so.concat(Integer.toString(x));
        else
            so = Integer.toString(x);
        temp = haiKiTuDau;
        temp = temp.concat(so);
        return temp;
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        Login frmlogin = new Login();
        frmlogin.setVisible(true);
        
    }
            
    
}
