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
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author NEMO
 */
public class QuanLyCTHD extends QuanLy {
    static int index;
//    static Connection conQLCTHD = KetNoi.getNewConnection();
    @Override
    public DefaultTableModel taiTT() {
        DefaultTableModel table = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        try {
            Connection conQLCTHD = KetNoi.getConnection();
            CallableStatement cstmt = conQLCTHD.prepareCall("SELECT * from CTHD WHERE XOA = 0");
            ResultSet rs = cstmt.executeQuery();

            String[] colsName = {"Số HD", "Mã Sản Phẩm", "Số Lượng"};
            table.setColumnIdentifiers(colsName);
            try {
                while (rs.next()) { // nếu còn đọc tiếp được một dòng dữ liệu
                    String rows[] = new String[3];                 
                    rows[0] = rs.getString(1); // lấy dữ liệu tại cột số 1 (ứng với mã hàng) 
                    rows[1] = rs.getString(2); // lấy dữ liệu tai cột số 2 ứng với tên hàng
                    rows[2] = rs.getString(3);

                    table.addRow(rows); // đưa dòng dữ liệu vào tableModel để hiện thị lên jtable
                    //mỗi lần có sự thay đổi dữ liệu ở tableModel thì Jtable sẽ tự động update lại trên frame
                   
                }
            } catch (SQLException e) {
            }

        } catch (SQLException ex) {
            System.err.println("Cannot connect database, " + ex);
        }
        return table;
    }


    public void xoaDongTrenSQL(ArrayList sohd) {
         try {
             Connection conQLCTHD = KetNoi.getConnection();
            for(int i=0; i<sohd.size();i++){
            System.out.println(sohd.get(i));    
            CallableStatement cstmt = conQLCTHD.prepareCall("UPDATE CTHD SET XOA = '" + 1 + "' WHERE SOHD='" + sohd.get(i) + "'");
            cstmt.execute();
             }

        } catch (SQLException ex) {
            System.err.println("Cannot connect database, " + ex);
        }
    }

    public boolean themCTTD(String mahd, String masp, String soluong) {
        try {
            Connection conQLCTHD = KetNoi.getConnection();
            CallableStatement cstmt = conQLCTHD.prepareCall("INSERT INTO CTHD VALUES ('" + mahd + "', '" + masp + "', '" + soluong + "', '" + 0 + "')");
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
            Connection conQLCTHD = KetNoi.getConnection();
            CallableStatement cstmt = conQLCTHD.prepareCall("SELECT CTHD.SOHD, CTHD.MASP, SP.TENSP, SP.NUOCSX, SP.DVT, SP.GIA, CTHD.SL FROM (CTHD JOIN SANPHAM SP ON CTHD.MASP = SP.MASP) JOIN HOADON HD ON HD.SOHD=CTHD.SOHD WHERE CTHD.SOHD ='" + sohd + "'");
            ResultSet rs = cstmt.executeQuery();
            
            String[] colsName = {"STT", "Mã SP", "Tên SP", "Xuất xứ", "ĐVT", "Giá", "SL", "Thành tiền"};
            table.setColumnIdentifiers(colsName);
            index = 1;
            try {
                while (rs.next()) { // nếu còn đọc tiếp được một dòng dữ liệu
                    String rows[] = new String[8];
                    rows[0] = Integer.toString(index);
                    rows[1] = rs.getString(2); // lấy dữ liệu tại cột số 1 (ứng với mã hàng) 
                    rows[2] = rs.getString(3); // lấy dữ liệu tai cột số 2 ứng với tên hàng
                    rows[3] = rs.getString(4);
                    rows[4] = rs.getString(5);
                    rows[5] = String.format("%,d", rs.getLong(6)) + " VNĐ";
                    rows[6] = rs.getString(7);
                    long thanhTien = rs.getLong(6) * rs.getLong(7);
                    rows[7] = String.format("%,d", thanhTien) + " VNĐ";

                    table.addRow(rows); // đưa dòng dữ liệu vào tableModel để hiện thị lên jtable
                    index++;
                }
            } catch (SQLException e) {
            }

        } catch (SQLException ex) {
            System.err.println("Cannot connect database, " + ex);
        }
        return table;
    }

    public boolean capnhatCTHD(String sohd, String sl, String masp) {
        try {
            Connection conQLCTHD = KetNoi.getConnection();
            CallableStatement cstmt = conQLCTHD.prepareCall("UPDATE CTHD SET SL = '" + sl + "' WHERE MASP ='" + masp + "' AND SOHD ='" + sohd + "'");
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
    public int timMa(String sohd){
        try {
            Connection conQLCTHD = KetNoi.getConnection();
            CallableStatement cstmt = conQLCTHD.prepareCall("SELECT SOHD from CTHD WHERE XOA = 0");
            ResultSet rs = cstmt.executeQuery();
            int temp = 0;
            try {
                while(rs.next()){
                    if(rs.getString("MAKH").equalsIgnoreCase(sohd)){
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

    @Override
    public void xoaDongTrenSQL(String sohd) {
        try {
            Connection conQLHD = KetNoi.getConnection();
            CallableStatement cstmt = conQLHD.prepareCall("UPDATE CTHD SET XOA = '" + 1 + "' WHERE SOHD='" + sohd + "'");
            cstmt.execute();
        } catch (SQLException ex) {
            System.err.println("Cannot connect database, " + ex);
        }
    }
    
    public DefaultTableModel taiSoHD(String ma){
        DefaultTableModel table = new DefaultTableModel();
        try {
            Connection conQLCTHD = KetNoi.getConnection();
            CallableStatement cstmt = conQLCTHD.prepareCall("SELECT * from CTHD WHERE XOA = 0 AND SOHD = '" + ma + "'");
            ResultSet rs = cstmt.executeQuery();
            String[] colsName = {"Số HD", "Mã Sản Phẩm", "Số Lượng"};
            table.setColumnIdentifiers(colsName);
            try {
                while (rs.next()) { // nếu còn đọc tiếp được một dòng dữ liệu
                    String rows[] = new String[3];                 
                    rows[0] = rs.getString(1); // lấy dữ liệu tại cột số 1 (ứng với mã hàng) 
                    rows[1] = rs.getString(2); // lấy dữ liệu tai cột số 2 ứng với tên hàng
                    rows[2] = rs.getString(3);
                    table.addRow(rows); // đưa dòng dữ liệu vào tableModel để hiện thị lên jtable
                    //mỗi lần có sự thay đổi dữ liệu ở tableModel thì Jtable sẽ tự động update lại trên frame
                   
                }
                return table;
            } catch (SQLException e) {
            }

        } catch (SQLException ex) {
            System.err.println("Cannot connect database, " + ex);
        }
        return null;     
    }
    
    public DefaultTableModel taiMaSP(String ma){
        DefaultTableModel table = new DefaultTableModel();
        try {
            Connection conQLCTHD = KetNoi.getConnection();
            CallableStatement cstmt = conQLCTHD.prepareCall("SELECT * from CTHD WHERE XOA = 0 AND MASP = '" + ma + "'");
            ResultSet rs = cstmt.executeQuery();
            String[] colsName = {"Số HD", "Mã Sản Phẩm", "Số Lượng"};
            table.setColumnIdentifiers(colsName);
            try {
                while (rs.next()) { // nếu còn đọc tiếp được một dòng dữ liệu
                    String rows[] = new String[3];                 
                    rows[0] = rs.getString(1); // lấy dữ liệu tại cột số 1 (ứng với mã hàng) 
                    rows[1] = rs.getString(2); // lấy dữ liệu tai cột số 2 ứng với tên hàng
                    rows[2] = rs.getString(3);
                    table.addRow(rows); // đưa dòng dữ liệu vào tableModel để hiện thị lên jtable
                    //mỗi lần có sự thay đổi dữ liệu ở tableModel thì Jtable sẽ tự động update lại trên frame
                   
                }
                return table;
            } catch (SQLException e) {
            }

        } catch (SQLException ex) {
            System.err.println("Cannot connect database, " + ex);
        }
        return null;     
    }
    
    public DefaultTableModel taiSL(String ma){
        DefaultTableModel table = new DefaultTableModel();
        try {
            Connection conQLCTHD = KetNoi.getConnection();
            CallableStatement cstmt = conQLCTHD.prepareCall("SELECT * from CTHD WHERE XOA = 0 AND SL = '" + ma + "'");
            ResultSet rs = cstmt.executeQuery();
            String[] colsName = {"Số HD", "Mã Sản Phẩm", "Số Lượng"};
            table.setColumnIdentifiers(colsName);
            try {
                while (rs.next()) { // nếu còn đọc tiếp được một dòng dữ liệu
                    String rows[] = new String[3];                 
                    rows[0] = rs.getString(1); // lấy dữ liệu tại cột số 1 (ứng với mã hàng) 
                    rows[1] = rs.getString(2); // lấy dữ liệu tai cột số 2 ứng với tên hàng
                    rows[2] = rs.getString(3);
                    table.addRow(rows); // đưa dòng dữ liệu vào tableModel để hiện thị lên jtable
                    //mỗi lần có sự thay đổi dữ liệu ở tableModel thì Jtable sẽ tự động update lại trên frame
                   
                }
                return table;
            } catch (SQLException e) {
            }

        } catch (SQLException ex) {
            System.err.println("Cannot connect database, " + ex);
        }
        return null;     
    }
    
}
