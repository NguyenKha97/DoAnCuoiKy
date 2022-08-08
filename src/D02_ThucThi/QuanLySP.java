/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package D02_ThucThi;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.event.ListDataListener;

/**
 *
 * @author admin
 */
public class QuanLySP extends QuanLy {

    static int index;

    @Override
    public DefaultTableModel taiTT() {
        DefaultTableModel table = new DefaultTableModel();
        try {
            Connection conn = LoginRun.con;
            CallableStatement cstmt = conn.prepareCall("SELECT * from SANPHAM WHERE XOA = 0");
            ResultSet rs = cstmt.executeQuery();

            String[] colsName = {"STT", "Mã SP", "Tên SP", "Đơn vị tính", "Nước sản xuất", "Giá"};
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
//                rows[5] = rs.getString(5);
                    rows[5] = String.format("%,d", rs.getLong(5));
                    table.addRow(rows); // đưa dòng dữ liệu vào tableModel để hiện thị lên jtable
                    //mỗi lần có sự thay đổi dữ liệu ở tableModel thì Jtable sẽ tự động update lại trên frame
                    index++;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException ex) {
            System.err.println("Cannot connect database, " + ex);
        }
        return table;
    }

    @Override
    public DefaultTableModel xoaDong(int i, DefaultTableModel table, int count) {
        table.removeRow(i);
        int j = i;
        for (; j < (index - count - 1); j++) {
            table.setValueAt(j + 1, j, 0);
        }
        return table;
    }

    @Override
    public void xoaDongTrenSQL(String ma) {
        try {
            Connection conn = LoginRun.con;
            CallableStatement cstmt = conn.prepareCall("UPDATE SANPHAM SET XOA =" + 1 + " WHERE MASP='" + ma + "'");
            cstmt.execute();

        } catch (SQLException ex) {
            System.err.println("Cannot connect database, " + ex);
        }
    }

    public boolean them(String MaSP, String TenSP, String Dvt, String NuocSX, String Gia) {
        try {
            Connection conn = LoginRun.con;
            CallableStatement cstmt = conn.prepareCall("INSERT INTO SANPHAM VALUES ('" + MaSP + "', '" + TenSP + "', '"
                    + Dvt + "', '" + NuocSX + "', '" + Gia + "', '" + 0 + "')");
            cstmt.execute();
            return true;

        } catch (SQLException ex) {
            System.err.println("Cannot connect database, " + ex);
            return false;
        }
    }
    public boolean capNhat(String ma, String tenSP, String dvt, String nuocSX, String gia) {
        try {
            Connection conn = LoginRun.con;
            CallableStatement cstmt = conn.prepareCall("UPDATE SANPHAM SET TENSP = '" + tenSP 
                    + "', DVT = '" + dvt + "', NUOCSX = '" + nuocSX + "', GIA = '" + gia + "' WHERE MASP = '" + ma + "'");
            cstmt.execute();
            return true;
            
        } catch (SQLException ex) {
            System.err.println("Cannot connect database, " + ex);
            return false;
        }
    }
    public DefaultComboBoxModel<String> getListMaSP(){
        try {
            DefaultComboBoxModel model = new DefaultComboBoxModel();
            Connection conn = LoginRun.con;
            CallableStatement cstmt = conn.prepareCall("SELECT MASP from SANPHAM WHERE XOA = 0");
            ResultSet rs = cstmt.executeQuery();
            int index=0;
            while(rs.next()){
                model.insertElementAt(rs.getString("MASP"), index);
                index++;
            }
            return model;
            } catch (SQLException ex) {
            System.err.println("Cannot connect database, " + ex); 
            return null;
        }
     }
    public double getGia(String masp) {
        try {
            Connection conn = LoginRun.con;
            CallableStatement cstmt = conn.prepareCall("SELECT GIA from SANPHAM WHERE MASP='" + masp + "'");
            ResultSet rs = cstmt.executeQuery();
            rs.next();
            double gia = rs.getDouble(1);
            return gia;
        } catch (SQLException ex) {
            System.err.println("Cannot connect database, " + ex);
            return 0.0;
        }
    }
}
    


