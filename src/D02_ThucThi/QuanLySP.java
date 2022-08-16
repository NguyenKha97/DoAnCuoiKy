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
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author admin
 */
public class QuanLySP extends QuanLy {

    static int index;
//    static Connection conQLSP = KetNoi.getNewConnection();
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
            Connection conQLSP = KetNoi.getConnection();
            CallableStatement cstmt = conQLSP.prepareCall("SELECT * from SANPHAM WHERE XOA = 0");
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
                    rows[5] = String.format("%,d", rs.getLong(5)) + " VNĐ";
                    table.addRow(rows); // đưa dòng dữ liệu vào tableModel để hiện thị lên jtable
                    //mỗi lần có sự thay đổi dữ liệu ở tableModel thì Jtable sẽ tự động update lại trên frame
                    index++;
                }
            } catch (SQLException e) {
            }
        } catch (SQLException ex) {
            System.err.println("Cannot connect database, " + ex);
        }
        System.out.println("thanh cong");
        return table;
    }



    @Override
    public void xoaDongTrenSQL(String ma) {
        try {
            Connection conQLSP = KetNoi.getConnection();
            CallableStatement cstmt = conQLSP.prepareCall("UPDATE SANPHAM SET XOA =" + 1 + " WHERE MASP='" + ma + "'");
            cstmt.execute();

        } catch (SQLException ex) {
            System.err.println("Cannot connect database, " + ex);
        }
    }

    public boolean them(String MaSP, String TenSP, String Dvt, String NuocSX, String Gia) {
        try {
            Connection conQLSP = KetNoi.getConnection();
            CallableStatement cstmt = conQLSP.prepareCall("INSERT INTO SANPHAM VALUES ('" + MaSP + "', '" + TenSP + "', '"
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
           
            Connection conQLSP = KetNoi.getConnection();
            CallableStatement cstmt = conQLSP.prepareCall("UPDATE SANPHAM SET TENSP = '" + tenSP 
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
            Connection conQLSP = KetNoi.getConnection();
            DefaultComboBoxModel model = new DefaultComboBoxModel();
            CallableStatement cstmt = conQLSP.prepareCall("SELECT MASP from SANPHAM WHERE XOA = 0");
            ResultSet rs = cstmt.executeQuery();
            int temp1=0;
            while(rs.next()){
                model.insertElementAt(rs.getString("MASP"), temp1);
                temp1++;
            }
            return model;
            } catch (SQLException ex) {
            System.err.println("Cannot connect database, " + ex); 
            return null;
        }
     }
    public long getGia(String masp) {
        try {
            Connection conQLSP = KetNoi.getConnection();
            CallableStatement cstmt = conQLSP.prepareCall("SELECT GIA from SANPHAM WHERE MASP='" + masp + "'");
            ResultSet rs = cstmt.executeQuery();
            rs.next();
            return rs.getLong(1);
        } catch (SQLException ex) {
            System.err.println("Cannot connect database, " + ex);
            return 0;
        }
    }
    
    /**
     *
     * @param masp
     * @return
     */
    @Override
    public int timMa(String masp){
        try {
            Connection conQLSP = KetNoi.getConnection();
            CallableStatement cstmt = conQLSP.prepareCall("SELECT MASP from SANPHAM WHERE XOA = 0");
            ResultSet rs = cstmt.executeQuery();
            int temp = 0;
            try {
                while(rs.next()){
                    if(rs.getString("MASP").equalsIgnoreCase(masp)){
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
    
    public static void getSP(String ma, int sl, DefaultTableModel table) {

        boolean check = false;
        int i = 0;
        for (; i < table.getRowCount(); i++) {
            if (table.getValueAt(i, 0).equals(ma)) {
                check = true;
                break;
            }
        } 
        try {
            Connection conQLSP = KetNoi.getConnection();
            CallableStatement cstmt = conQLSP.prepareCall("SELECT * from SANPHAM WHERE XOA = 0 AND MASP = '" + ma + "'");
            ResultSet rs = cstmt.executeQuery();

            String[] colsName = {"Mã SP", "Tên SP", "ĐVT", "Xuất xứ", "Giá", "SL", "Thành tiền"};
            table.setColumnIdentifiers(colsName);
            try {
                rs.next();
                if (check) {
                    int slMoi = sl + Integer.parseInt((String) table.getValueAt(i, 5));
                    table.setValueAt(Integer.toString(slMoi), i, 5);
                    String thanhTien = String.format("%,d", (slMoi * rs.getLong(5))) + " VNĐ";
                    table.setValueAt(thanhTien, i, 6);
                } else {
                    String rows[] = new String[7];
                    rows[0] = rs.getString(1);
                    rows[1] = rs.getString(2); // lấy dữ liệu tại cột số 1 (ứng với mã hàng) 
                    rows[2] = rs.getString(3); // lấy dữ liệu tai cột số 2 ứng với tên hàng
                    rows[3] = rs.getString(4);
                    rows[4] = String.format("%,d", rs.getLong(5)) + " VNĐ";
                    rows[5] = Integer.toString(sl);
                    rows[6] = String.format("%,d", (sl * rs.getLong(5))) + " VNĐ";
                    table.addRow(rows); // đưa dòng dữ liệu vào tableModel để hiện thị lên jtable
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException ex) {
            System.err.println("Cannot connect database, " + ex);
        }

    }
    
    public String getTTSP(String masp) {
        String thongTinSP = "";
        try {
            Connection conQLSP = KetNoi.getConnection();
            CallableStatement cstmt = conQLSP.prepareCall("SELECT * from SANPHAM WHERE XOA = 0 AND MASP = '" + masp + "'");
            ResultSet rs = cstmt.executeQuery();
            try {
                rs.next();
                thongTinSP = rs.getString(1) + " - " + rs.getString(2) + " - " + rs.getString(3) + " - " + rs.getString(4) + " - " + String.format("%,d", rs.getLong(5)) + " VNĐ";
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException ex) {
            System.err.println("Cannot connect database, " + ex);
        }
        return thongTinSP;
    }
    
}
    


