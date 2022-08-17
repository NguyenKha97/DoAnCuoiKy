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
import java.util.ArrayList;
import java.util.Date;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author NEMO
 */
public class QuanLyHD extends QuanLy {

    static int index;
//    static Connection conQLHD = KetNoi.getNewConnection();

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
            Connection conQLHD = KetNoi.getConnection();
            CallableStatement cstmt = conQLHD.prepareCall("SELECT * from HOADON WHERE XOA = 0");
            ResultSet rs = cstmt.executeQuery();

            String[] colsName = {"STT", "Số HD", "Ngày Lập", "Mã KH", "Mã NV", "Trị Giá"};
            table.setColumnIdentifiers(colsName);
            index = 1;
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            try {
                while (rs.next()) { // nếu còn đọc tiếp được một dòng dữ liệu
                    String rows[] = new String[6];
                    rows[0] = Integer.toString(index);
                    rows[1] = rs.getString(1); // lấy dữ liệu tại cột số 1 (ứng với mã hàng) 
                    rows[2] = df.format(rs.getDate(2)); // lấy dữ liệu tai cột số 2 ứng với tên hàng
                    rows[3] = rs.getString(3);
                    rows[4] = rs.getString(4);
//                    rows[5] = String.format("%,d", rs.getLong(5));
                    rows[5] = String.format("%,d", rs.getLong(5)) + " VNĐ";
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

    public DefaultTableModel taiTTTheoMaNV(String manv) {
        DefaultTableModel table = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        try {
            Connection conQLHD = KetNoi.getConnection();
            CallableStatement cstmt = conQLHD.prepareCall("SELECT * from HOADON WHERE XOA = 0 AND MANV ='" + manv + "'");
            ResultSet rs = cstmt.executeQuery();

            String[] colsName = {"STT", "Số HD", "Ngày Lập", "Mã KH", "Mã NV", "Trị Giá"};
            table.setColumnIdentifiers(colsName);
            index = 1;
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            try {
                while (rs.next()) { // nếu còn đọc tiếp được một dòng dữ liệu
                    String rows[] = new String[6];
                    rows[0] = Integer.toString(index);
                    rows[1] = rs.getString(1); // lấy dữ liệu tại cột số 1 (ứng với mã hàng) 
                    rows[2] = df.format(rs.getDate(2)); // lấy dữ liệu tai cột số 2 ứng với tên hàng
                    rows[3] = rs.getString(3);
                    rows[4] = rs.getString(4);
//                    rows[5] = String.format("%,d", rs.getLong(5));
                    rows[5] = String.format("%,d", rs.getLong(5)) + " VNĐ";
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

    public void xoaDongTrenSQL(ArrayList makh) {
        try {
            Connection conQLHD = KetNoi.getConnection();
            for (int i = 0; i < makh.size(); i++) {
                CallableStatement cstmt = conQLHD.prepareCall("UPDATE HOADON SET XOA = '" + 1 + "' WHERE SOHD='" + makh.get(i) + "'");
                cstmt.execute();
            }

        } catch (SQLException ex) {
            System.err.println("Cannot connect database, " + ex);
        }
    }

    public long getTriGia(String sohd) {
        try {
            Connection conQLHD = KetNoi.getConnection();
            CallableStatement cstmt = conQLHD.prepareCall("SELECT TRIGIA FROM HOADON WHERE SOHD ='" + sohd + "'");
            ResultSet rs = cstmt.executeQuery();
            rs.next();
            return rs.getLong("TRIGIA");
        } catch (SQLException ex) {
            System.err.println("Cannot connect database, " + ex);
        }
        return 0;
    }

    public boolean capNhatNgayHD(String mahd, Date ngayhd) {
        try {
            Connection conQLHD = KetNoi.getConnection();
            CallableStatement cstmt = conQLHD.prepareCall("UPDATE HOADON SET NGHD = '" + new java.sql.Date(ngayhd.getTime()) + "' WHERE SOHD ='" + mahd + "'");
            cstmt.execute();
            return true;
        } catch (SQLException ex) {
            System.err.println("Cannot connect database, " + ex);
            return false;
        }
    }

    public boolean themHD(String mahd, Date ngayhd, String makh, String manv, long trigia) {
        try {
            Connection conQLHD = KetNoi.getConnection();
            CallableStatement cstmt = conQLHD.prepareCall("INSERT INTO HOADON VALUES ('" + mahd + "', '" + new java.sql.Date(ngayhd.getTime()) + "', '" + makh + "', '" + manv + "', '" + trigia + "', '" + 0 + "')");
            cstmt.execute();
            return true;
        } catch (SQLException ex) {
            System.err.println("Cannot connect database, " + ex);
            return false;
        }
    }

    public String getNewSoHD() {
        try {
            Connection conQLHD = KetNoi.getConnection();
            CallableStatement cstmt = conQLHD.prepareCall("SELECT TOP 1 SOHD FROM HOADON ORDER BY SOHD DESC");
            ResultSet rs = cstmt.executeQuery();
            rs.next();
            int newSOHD = Integer.parseInt(rs.getString(1));
            return Integer.toString(++newSOHD);
        } catch (SQLException ex) {
            System.err.println("Cannot connect database, " + ex);
            return null;
        }
    }

    public boolean capnhatTriGia(String sohd, double trigiaNew) {
        try {
            Connection conQLHD = KetNoi.getConnection();
            CallableStatement cstmt = conQLHD.prepareCall("UPDATE HOADON SET TRIGIA = '" + trigiaNew + "' WHERE XOA = 0 AND SOHD ='" + sohd + "'");
            cstmt.execute();
            return true;
        } catch (SQLException ex) {
            System.err.println("Cannot connect database, " + ex);
            return false;
        }
    }

    /**
     *
     * @param sohd
     * @return
     */
    @Override
    public int timMa(String sohd) {
        try {
            Connection conQLHD = KetNoi.getConnection();
            CallableStatement cstmt = conQLHD.prepareCall("SELECT SOHD from HOADON WHERE XOA = 0");
            ResultSet rs = cstmt.executeQuery();
            int temp = 0;
            try {
                while (rs.next()) {
                    if (rs.getString("SOHD").equalsIgnoreCase(sohd)) {
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

    public ArrayList laySoHDTheoMaKH(String maKH) {
        ArrayList sohd = new ArrayList<String>();
        try {
            Connection conQLHD = KetNoi.getConnection();
            CallableStatement cstmt = conQLHD.prepareCall("SELECT SOHD from HOADON WHERE MAKH = '" + maKH + "'");
            ResultSet rs = cstmt.executeQuery();
            try {
                while (rs.next()) {
                    sohd.add(rs.getString("SOHD"));
                }
            } catch (SQLException e) {
                System.out.println("loi oy");
            }
        } catch (SQLException ex) {
            System.err.println("Cannot connect database, " + ex);
        }
        return sohd;
    }

    @Override
    public void xoaDongTrenSQL(String makh) {
        try {
            Connection conQLHD = KetNoi.getConnection();
            CallableStatement cstmt = conQLHD.prepareCall("UPDATE HOADON SET XOA = '" + 1 + "' WHERE SOHD='" + makh + "'");
            cstmt.execute();
        } catch (SQLException ex) {
            System.err.println("Cannot connect database, " + ex);
        }
    }

    public String layMaKH(String sohd) {
        String result = "";
        try {
            Connection conQLHD = KetNoi.getConnection();
            CallableStatement cstmt = conQLHD.prepareCall("SELECT MAKH FROM HOADON WHERE SOHD ='" + sohd + "'");
            ResultSet rs = cstmt.executeQuery();
            rs.next();
            result = rs.getString("MAKH");
        } catch (SQLException ex) {
            System.err.println("Không thể lấy mã KH kiểm tra lai QLHD " + ex);
        }
        return result;
    }

    public boolean check(String makh) {
        try {
            Connection conQLKH = KetNoi.getConnection();
            CallableStatement cstmt = conQLKH.prepareCall("SELECT MAKH from HOADON WHERE XOA = 0 AND MAKH = '" + makh + "'");
            ResultSet rs = cstmt.executeQuery();
            rs.next();
            if (rs.getString("MAKH").equalsIgnoreCase(makh)) {
                return true;
            }
        } catch (SQLException ex) {
            System.err.println("Cannot connect database, " + ex);
        }
        return false;
    }

    public DefaultTableModel timHD(String ma, String dieuKienTim) {
        DefaultTableModel table = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        try {
            Connection conQLHD = KetNoi.getConnection();
            CallableStatement cstmt = conQLHD.prepareCall("SELECT * from HOADON WHERE XOA = 0 AND " + dieuKienTim + " = '" + ma + "'");
            ResultSet rs = cstmt.executeQuery();

            String[] colsName = {"STT", "Số HD", "Ngày Lập", "Mã KH", "Mã NV", "Trị Giá"};
            table.setColumnIdentifiers(colsName);
            index = 1;
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            try {
                while (rs.next()) { // nếu còn đọc tiếp được một dòng dữ liệu
                    String rows[] = new String[6];
                    rows[0] = Integer.toString(index);
                    rows[1] = rs.getString(1); // lấy dữ liệu tại cột số 1 (ứng với mã hàng) 
                    rows[2] = df.format(rs.getDate(2)); // lấy dữ liệu tai cột số 2 ứng với tên hàng
                    rows[3] = rs.getString(3);
                    rows[4] = rs.getString(4);
//                    rows[5] = String.format("%,d", rs.getLong(5));
                    rows[5] = String.format("%,d", rs.getLong(5)) + " VNĐ";
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

}
