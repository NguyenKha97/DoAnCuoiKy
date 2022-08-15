/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package D02_ThucThi;

import D01_KetNoi.KetNoi;
import javax.swing.table.DefaultTableModel;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author admin
 */
public class QuanLyDN {
    
    public DefaultTableModel taiTT() {
        DefaultTableModel table = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        try {
            Connection conQLKH = KetNoi.getNewConnection();
            CallableStatement cstmt = conQLKH.prepareCall("SELECT * from DANGNHAP WHERE XOA = 0");
            ResultSet rs = cstmt.executeQuery();

            String[] colsName = {"USERNAME (MANV)", "PASSWORD"};
            table.setColumnIdentifiers(colsName);
            try {
                while (rs.next()) {
                    String rows[] = new String[2];
                    rows[0] = rs.getString(1);
                    rows[1] = rs.getString(3);
                    table.addRow(rows);
                }
            } catch (SQLException e) {
                System.out.println("Lỗi lấy dữ liệu resultSet trong QLKH");
            }
        } catch (SQLException ex) {
            System.err.println("Lỗi kết nỗi hoặc querry trong QLKH " + ex);
        }
        return table;
    }
    
     public String getPassAdmin() {
        try {
            Connection conQLSP = KetNoi.getNewConnection();
            CallableStatement cstmt = conQLSP.prepareCall("SELECT PASSWORD from DANGNHAP WHERE MANV = 'ADMN'");
            ResultSet rs = cstmt.executeQuery();
            rs.next();
            return rs.getString(1);
        } catch (SQLException ex) {
            System.err.println("Cannot connect database, " + ex);
        }
        return null;
    }
     
          public String getMaBaoVe() {
        try {
            Connection conQLSP = KetNoi.getNewConnection();
            CallableStatement cstmt = conQLSP.prepareCall("SELECT GHICHU from DANGNHAP WHERE MANV = 'ADMN'");
            ResultSet rs = cstmt.executeQuery();
            rs.next();
            return rs.getString(1);
        } catch (SQLException ex) {
            System.err.println("Cannot connect database, " + ex);
        }
        return null;
    }
         
    public boolean capNhatPass(String manv, String passMoi) {
        try {
            Connection conQLSP = KetNoi.getNewConnection();
            CallableStatement cstmt = conQLSP.prepareCall(" UPDATE DANGNHAP SET PASSWORD = '" + passMoi + "' WHERE MANV = '" + manv + "'");
            cstmt.execute();
            return true;
        } catch (SQLException ex) {
            System.err.println("Cannot connect database, " + ex);
        }
        return false;
    }
      
    public boolean capNhatMaBaoVe(String maBVMoi) {
        try {
            Connection conQLSP = KetNoi.getNewConnection();
            CallableStatement cstmt = conQLSP.prepareCall(" UPDATE DANGNHAP SET GHICHU = '" + maBVMoi + "' WHERE MANV = 'ADMN'");
            cstmt.execute();
            return true;
        } catch (SQLException ex) {
            System.err.println("Cannot connect database, " + ex);
        }
        return false;
    }      
        

    public boolean resetPass(String manv) {
        try {
            Connection conQLSP = KetNoi.getNewConnection();
            CallableStatement cstmt = conQLSP.prepareCall(" UPDATE DANGNHAP SET PASSWORD = '" + manv.toLowerCase() + "' WHERE MANV = '" + manv + "'");
            cstmt.execute();
            return true;
        } catch (SQLException ex) {
            System.err.println("Cannot connect database, " + ex);
        }
        return false;
    }
        
}
    

