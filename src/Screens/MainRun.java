/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Screens;

/**
 *
 * @author HP
 */
public class MainRun {
    public static void main(String[] args) {
        Login frmLogin = new Login();
//        Main frmMain = new Main();
        frmLogin.setVisible(true);
        System.err.println("Trạng thái kết nối " + frmLogin.statusLogin);
//        if(frmLogin.statusLogin == true){
//            frmLogin.setVisible(false);
//                      
//            frmMain.setVisible(true);
//        }
    }
}
