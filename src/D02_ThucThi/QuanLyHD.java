/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package D02_ThucThi;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author NEMO
 */
public class QuanLyHD extends QuanLy {

    static int index;

    @Override
    public DefaultTableModel taiTT() {
        DefaultTableModel table = new DefaultTableModel();
        try {
            Connection conn = LoginRun.con;
            CallableStatement cstmt = conn.prepareCall("SELECT * from HOADON WHERE XOA = 0");
            ResultSet rs = cstmt.executeQuery();

            String[] colsName = {"STT", "Số HD", "Ngày Lập", "Mã KH", "Mã NV", "Trị Giá"};
            table.setColumnIdentifiers(colsName);
            index = 1;
            try {
                while (rs.next()) { // nếu còn đọc tiếp được một dòng dữ liệu
                    String rows[] = new String[6];
                    rows[0] = Integer.toString(index);
                    rows[1] = rs.getString(1); // lấy dữ liệu tại cột số 1 (ứng với mã hàng) 
                    rows[2] = rs.getString(2); // lấy dữ liệu tai cột số 2 ứng với tên hàng
                    rows[3] = rs.getString(3);
                    rows[4] = rs.getString(4);
                    rows[5] = rs.getString(5);

                    table.addRow(rows); // đưa dòng dữ liệu vào tableModel để hiện thị lên jtable
                    //mỗi lần có sự thay đổi dữ liệu ở tableModel thì Jtable sẽ tự động update lại trên frame
                    index++;
//                System.out.println(index);
                }
                System.out.println("index sau taitt = " + index);
            } catch (SQLException e) {
            }

        } catch (SQLException ex) {
            System.err.println("Cannot connect database, " + ex);
        }
        return table;
    }

    @Override
    public DefaultTableModel xoaDong(int i, DefaultTableModel dfTable, int countButton) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void xoaDongTrenSQL(int i) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void getTriGia(String mahd) {
        try {
            Connection conn = LoginRun.con;
            CallableStatement cstmt = conn.prepareCall("SELECT TRIGIA FROM HOADON WHERE XOA = 0 AND SOHD ='" + mahd + "'");
            ResultSet rs = cstmt.executeQuery();
            rs.next();
            
            System.out.println(rs.getString(1));
//            return rs.getS;
        } catch (SQLException ex) {
            System.err.println("Cannot connect database, " + ex);
//            return null;
        }
    }

    public boolean capNhatNgayHD(String mahd, Date ngayhd) {
        try {
            Connection conn = LoginRun.con;
            CallableStatement cstmt = conn.prepareCall("UPDATE HOADON SET NGHD = '" + new java.sql.Date(ngayhd.getTime()) + "' WHERE SOHD ='" + mahd + "'");
            cstmt.execute();
            return true;
        } catch (SQLException ex) {
            System.err.println("Cannot connect database, " + ex);
            return false;
        }
    }

    public boolean themHD(int mahd, Date ngayhd, String makh, String manv, Double trigia) {
        try {
            System.out.println(mahd);
            Connection conn = LoginRun.con;
            CallableStatement cstmt = conn.prepareCall("INSERT INTO HOADON VALUES ('" + mahd + "', '" + new java.sql.Date(ngayhd.getTime()) + "', '" + makh + "', '" + manv + "', '" + trigia + "', '" + 0 + "')");
            cstmt.execute();
            return true;
        } catch (SQLException ex) {
            System.err.println("Cannot connect database, " + ex);
            return false;
        }
    }

    public int getNewSoHD() {
        try {
            Connection conn = LoginRun.con;
            CallableStatement cstmt = conn.prepareCall("SELECT TOP 1 SOHD FROM HOADON ORDER BY SOHD DESC");
            ResultSet rs = cstmt.executeQuery();
            rs.next();
            int newSOHD = Integer.parseInt(rs.getString(1));
            return ++newSOHD;
        } catch (SQLException ex) {
            System.err.println("Cannot connect database, " + ex);
            return -1;
        }
    }
    public boolean capnhatTriGia(String sohd, double trigiaNew){
         try {
            Connection conn = LoginRun.con;
            CallableStatement cstmt = conn.prepareCall("UPDATE HOADON SET TRIGIA = '" + trigiaNew + "' WHERE XOA = 0 AND SOHD ='" + sohd + "'");
            cstmt.execute();
            return true;
        } catch (SQLException ex) {
            System.err.println("Cannot connect database, " + ex);
            return false;
        }
    }

}
