/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package D02_ThucThi;

import D01_KetNoi.KetNoi;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author admin
 */
public class QuanLyNV extends QuanLy {

    static int index;
//    static Connection conQLNV = KetNoi.getNewConnection();

    public String getMaCuoi() {
        String result = "";
        try {
//            WHERE XOA = 0
            Connection conQLNV = KetNoi.getConnection();
            CallableStatement cstmt = conQLNV.prepareCall("SELECT TOP 1 MANV FROM NHANVIEN ORDER BY MANV DESC");
            ResultSet rs = cstmt.executeQuery();
            rs.next();
            result = rs.getString(1);
        } catch (SQLException ex) {
            System.err.println("Cannot connect database, " + ex);
        }
        return result;
    }

    @Override
    public DefaultTableModel taiTT() {
        DefaultTableModel table = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        try {
            Connection conQLNV = KetNoi.getConnection();
            CallableStatement cstmt = conQLNV.prepareCall("SELECT * from NHANVIEN WHERE XOA = 0");
            ResultSet rs = cstmt.executeQuery();

            String[] colsName = {"STT", "Mã NV", "Họ và tên", "Số ĐT", "Ngày vào làm"};
            table.setColumnIdentifiers(colsName);
            index = 1;
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            try {
                while (rs.next()) { // nếu còn đọc tiếp được một dòng dữ liệu
                    String rows[] = new String[5];
                    rows[0] = Integer.toString(index);
                    rows[1] = rs.getString(1); // lấy dữ liệu tại cột số 1 (ứng với mã hàng) 
                    rows[2] = rs.getString(2); // lấy dữ liệu tai cột số 2 ứng với tên hàng
                    rows[3] = rs.getString(3);
                    rows[4] = df.format(rs.getDate(4));
                    table.addRow(rows); // đưa dòng dữ liệu vào tableModel để hiện thị lên jtable
                    //mỗi lần có sự thay đổi dữ liệu ở tableModel thì Jtable sẽ tự động update lại trên frame
                    index++;
                }
            } catch (SQLException e) {
            }

        } catch (SQLException ex) {
            System.err.println("Cannot connect database, " + ex);
        }
        return table;
    }

    @Override
    public void xoaDongTrenSQL(String ma) {
        try {
            Connection conQLNV = KetNoi.getConnection();
            CallableStatement cstmt = conQLNV.prepareCall("UPDATE NHANVIEN SET XOA =" + 1 + " WHERE MANV='" + ma + "'");
            cstmt.execute();
            cstmt = conQLNV.prepareCall("UPDATE DANGNHAP SET XOA =" + 1 + " WHERE MANV='" + ma + "'");
            cstmt.execute();
        } catch (SQLException ex) {
            System.err.println("Cannot connect database, " + ex);
        }
    }

    public boolean them(String MaNV, String HoTenNV, String SoDTNV, Date NgVaoLam) {
        try {
            Connection conQLNV = KetNoi.getConnection();
            CallableStatement cstmt = conQLNV.prepareCall("INSERT INTO NHANVIEN VALUES ('" + MaNV + "', '" + HoTenNV + "', '"
                    + SoDTNV + "', '" + new java.sql.Date(NgVaoLam.getTime()) + "', '" + 0 + "')");
            cstmt.execute();
            cstmt = conQLNV.prepareCall("INSERT INTO DANGNHAP VALUES ('" + MaNV + "', '" + MaNV.toLowerCase() + "', '" + MaNV.toLowerCase() + "', '0')");
            cstmt.execute();
            return true;

        } catch (SQLException ex) {
            System.err.println("Cannot connect database, " + ex);
            return false;
        }

    }

    public boolean capNhat(String ma, String hoTen, String soDT, Date NgVaoLam) {
        try {
            Connection conQLNV = KetNoi.getConnection();
            CallableStatement cstmt = conQLNV.prepareCall("UPDATE NHANVIEN SET HOTEN = '" + hoTen
                    + "', SODT = '" + soDT + "', NGVL = '" + new java.sql.Date(NgVaoLam.getTime()) + "' WHERE MANV = '" + ma + "'");
            cstmt.execute();
            return true;

        } catch (SQLException ex) {
            System.err.println("Cannot connect database, " + ex);
            return false;
        }
    }

    public Date getDate(int i) {
        try {
            Connection conQLNV = KetNoi.getConnection();
            CallableStatement cstmt = conQLNV.prepareCall("SELECT NGVL from NHANVIEN WHERE XOA = 0");
            ResultSet rs = cstmt.executeQuery();
            int temp = 0;
            try {
                while (rs.next() && temp <= i) {
                    if (temp == i) {
                        return rs.getDate("NGVL");
                    }
                    temp++;
                }
            } catch (SQLException e) {
                System.out.println("loi oy");
            }

        } catch (SQLException ex) {
            System.err.println("Cannot connect database, " + ex);
        }
        return null;
    }

    @Override
    public int timMa(String manv) {
        try {
            Connection conQLNV = KetNoi.getConnection();
            CallableStatement cstmt = conQLNV.prepareCall("SELECT MANV from NHANVIEN WHERE XOA = 0");
            ResultSet rs = cstmt.executeQuery();
            int temp = 0;
            try {
                while (rs.next()) {
                    if (rs.getString("MANV").equalsIgnoreCase(manv)) {
                        return temp;
                    }
                    temp++;
                }
            } catch (SQLException e) {
                System.out.println("loi oy");
            }

        } catch (SQLException ex) {
            System.err.println("Cannot connect database, " + ex);
        }
        return -1;
    }

    public DefaultComboBoxModel<String> getListMaNV() {
        try {
            Connection conQLSP = KetNoi.getConnection();
            DefaultComboBoxModel model = new DefaultComboBoxModel();
            CallableStatement cstmt = conQLSP.prepareCall("SELECT MANV from NHANVIEN WHERE XOA = 0");
            ResultSet rs = cstmt.executeQuery();
            int Temp = 0;
            while (rs.next()) {
                model.insertElementAt(rs.getString("MANV"), Temp);
                Temp++;
            }
            return model;
        } catch (SQLException ex) {
            System.err.println("Cannot connect database, " + ex);
            return null;
        }
    }

    public String getTenNV(String manv) {
        try {
            Connection conQLSP = KetNoi.getConnection();
            CallableStatement cstmt = conQLSP.prepareCall("SELECT HOTEN from NHANVIEN WHERE MANV = '" + manv + "'");
            ResultSet rs = cstmt.executeQuery();
            rs.next();
            return rs.getString(1);
        } catch (SQLException ex) {
            System.err.println("Cannot connect database, " + ex);
        }
        return null;
    }

    public DefaultTableModel timNV(String ma, String dieuKienTim) {
        DefaultTableModel table = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        try {
            Connection conQLNV = KetNoi.getConnection();
            CallableStatement cstmt = conQLNV.prepareCall("SELECT * from NHANVIEN WHERE XOA = 0 AND " + dieuKienTim + " = '" + ma + "'");
            ResultSet rs = cstmt.executeQuery();

            String[] colsName = {"STT", "Mã NV", "Họ và tên", "Số ĐT", "Ngày vào làm"};
            table.setColumnIdentifiers(colsName);
            index = 1;
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            try {
                while (rs.next()) { // nếu còn đọc tiếp được một dòng dữ liệu
                    String rows[] = new String[5];
                    rows[0] = Integer.toString(index);
                    rows[1] = rs.getString(1); // lấy dữ liệu tại cột số 1 (ứng với mã hàng) 
                    rows[2] = rs.getString(2); // lấy dữ liệu tai cột số 2 ứng với tên hàng
                    rows[3] = rs.getString(3);
                    rows[4] = df.format(rs.getDate(4));
                    table.addRow(rows); // đưa dòng dữ liệu vào tableModel để hiện thị lên jtable
                    //mỗi lần có sự thay đổi dữ liệu ở tableModel thì Jtable sẽ tự động update lại trên frame
                    index++;
                }
            } catch (SQLException e) {
            }

        } catch (SQLException ex) {
            System.err.println("Cannot connect database, " + ex);
        }
        return table;
    }

}
