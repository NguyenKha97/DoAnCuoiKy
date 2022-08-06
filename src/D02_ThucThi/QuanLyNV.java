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
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author admin
 */
public class QuanLyNV extends QuanLy {
    static int index;
    
    public String getMaCuoi(){
        String result ="";
        try {
            Connection conn = LoginRun.con;
//            WHERE XOA = 0
            CallableStatement cstmt = conn.prepareCall("SELECT TOP 1 MANV FROM NHANVIEN ORDER BY MANV DESC");
            ResultSet rs = cstmt.executeQuery();
            rs.next();
            result = rs.getString(1);  
        } catch (SQLException ex) {
            System.err.println("Cannot connect database, " + ex);
        }
        return result;
    }
    
    @Override
    public DefaultTableModel taiTT(){
        DefaultTableModel table = new DefaultTableModel();
        try {
            Connection conn = LoginRun.con;
            CallableStatement cstmt = conn.prepareCall("SELECT * from NHANVIEN WHERE XOA = 0");
            ResultSet rs = cstmt.executeQuery();
    
            String []colsName = {"STT", "Mã NV", "Số ĐT", "Họ và tên", "Ngày vào làm" };
            table.setColumnIdentifiers(colsName);
            index = 1; 
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            try {
            while(rs.next()){ // nếu còn đọc tiếp được một dòng dữ liệu
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
    public DefaultTableModel xoaDong(int i, DefaultTableModel table, int count){
        table.removeRow(i);
        System.out.println("row = " + i);
        int j = i;
        for(  ; j <(index-count-1); j++ ){
            table.setValueAt(j+1, j, 0);
        }
        return table;
    }
    
    @Override
    public void xoaDongTrenSQL(int i){
        try {
            Connection conn = LoginRun.con;
            CallableStatement cstmt = conn.prepareCall("SELECT MANV from NHANVIEN WHERE XOA = 0");
            ResultSet rs = cstmt.executeQuery();
            int temp = 0; String manv="";
            try {
                while(rs.next()&&temp<=i){
                    if(temp == i){
                        manv = rs.getString("MANV");
                        System.out.println(manv);
                    }
                    temp++;
                }
            } catch (SQLException e) {
            }
//            cstmt = conn.prepareCall("DELETE from KHACHHANG where MAKH = '" + makh + "'"); 
            cstmt = conn.prepareCall("UPDATE NHANVIEN SET XOA =" + 1 + " WHERE MANV='" + manv + "'");
            cstmt.execute();
            
        } catch (SQLException ex) {
            System.err.println("Cannot connect database, " + ex);
        }
    }
    
    public boolean them(String MaNV, String HoTenNV, String SoDTNV, Date NgVaoLam) {
        try {
            Connection conn = LoginRun.con;
            CallableStatement cstmt = conn.prepareCall("INSERT INTO NHANVIEN VALUES ('" + MaNV + "', '" + HoTenNV + "', '"
                    + SoDTNV + "', '" + new java.sql.Date(NgVaoLam.getTime()) +"', '" +  0 + "')");
            cstmt.execute();
            return true;
            
        } catch (SQLException ex) {
            System.err.println("Cannot connect database, " + ex);
            return false;
        }
        
    }
    
}
