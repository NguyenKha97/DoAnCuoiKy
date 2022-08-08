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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author NEMO
 */
public class QuanLyCTHD extends QuanLy {

    static int index;

    @Override
    public DefaultTableModel taiTT() {
        DefaultTableModel table = new DefaultTableModel();
        try {
            Connection conn = LoginRun.con;
            CallableStatement cstmt = conn.prepareCall("SELECT * from CTHD WHERE XOA = 0");
            ResultSet rs = cstmt.executeQuery();

            String[] colsName = {"STT", "Số HD", "Mã Sản Phẩm", "Số Lượng"};
            table.setColumnIdentifiers(colsName);
            index = 1;
            try {
                while (rs.next()) { // nếu còn đọc tiếp được một dòng dữ liệu
                    String rows[] = new String[4];
                    rows[0] = Integer.toString(index);
                    rows[1] = rs.getString(1); // lấy dữ liệu tại cột số 1 (ứng với mã hàng) 
                    rows[2] = rs.getString(2); // lấy dữ liệu tai cột số 2 ứng với tên hàng
                    rows[3] = rs.getString(3);

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
    public void xoaDongTrenSQL(String ma) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public boolean themCTTD(int mahd, String masp, String soluong) {
        try {
            Connection conn = LoginRun.con;
            CallableStatement cstmt = conn.prepareCall("INSERT INTO CTHD VALUES ('" + mahd + "', '" + masp + "', '" + soluong + "', '" + 0 + "')");
            cstmt.execute();
            return true;
        } catch (SQLException ex) {
            System.err.println("Cannot connect database, " + ex);
            return false;
        }
    }

    public DefaultTableModel getCTTheoHD(String sohd) {
        DefaultTableModel table = new DefaultTableModel();
        try {
            Connection conn = LoginRun.con;
            CallableStatement cstmt = conn.prepareCall("SELECT * from CTHD WHERE XOA = 0 AND SOHD ='" + sohd + "'");
            ResultSet rs = cstmt.executeQuery();

            String[] colsName = {"STT", "Số HD", "Mã Sản Phẩm", "Số Lượng"};
            table.setColumnIdentifiers(colsName);
            index = 1;
            try {
                while (rs.next()) { // nếu còn đọc tiếp được một dòng dữ liệu
                    String rows[] = new String[4];
                    rows[0] = Integer.toString(index);
                    rows[1] = rs.getString(1); // lấy dữ liệu tại cột số 1 (ứng với mã hàng) 
                    rows[2] = rs.getString(2); // lấy dữ liệu tai cột số 2 ứng với tên hàng
                    rows[3] = rs.getString(3);

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

    public boolean capnhatCTHD(String sohd, String sl, String masp) {
        try {
            Connection conn = LoginRun.con;
            CallableStatement cstmt = conn.prepareCall("UPDATE CTHD SET SL = '" + sl + "' WHERE MASP ='" + masp + "' AND SOHD ='" + sohd + "'");
            cstmt.execute();
            return true;
        } catch (SQLException ex) {
            System.err.println("Cannot connect database, " + ex);
            return false;
        }

    }

}
