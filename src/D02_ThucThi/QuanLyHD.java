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
public class QuanLyHD extends QuanLy {

    static int index;

    @Override
    public DefaultTableModel taiTT() {
        DefaultTableModel table = new DefaultTableModel();
        try {
            Connection conn = LoginRun.con;
            CallableStatement cstmt = conn.prepareCall("SELECT * from HOADON WHERE XOA = 0");
            ResultSet rs = cstmt.executeQuery();

            String[] colsName = {"STT", "Mã HD", "Ngày Lập", "Mã KH", "Mã NV", "Trị Giá"};
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
//                System.out.println("index sau taitt = " + index);
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

    public boolean capNhatTriGia(String mahd, String trigia) {

        try {
            Connection conn = LoginRun.con;
            CallableStatement cstmt = conn.prepareCall("UPDATE HOADON SET TRIGIA = '" + Double.parseDouble(trigia) + "' WHERE SOHD ='" + mahd + "'");
            cstmt.execute();
            return true;
        } catch (SQLException ex) {
            System.err.println("Cannot connect database, " + ex);
            return false;
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

    public boolean themHD(String mahd, Date ngayhd, String makh, String manv, String trigia) {
        try {
            Connection conn = LoginRun.con;
            CallableStatement cstmt = conn.prepareCall("INSERT INTO HOADON VALUES ('" + mahd + "', '" + new java.sql.Date(ngayhd.getTime()) + "', '" + makh + "', '" + manv + "', '" + Double.parseDouble(trigia) + "'");
            cstmt.execute();
            return true;
        } catch (SQLException ex) {
            System.err.println("Cannot connect database, " + ex);
            return false;
        }
    }

}
