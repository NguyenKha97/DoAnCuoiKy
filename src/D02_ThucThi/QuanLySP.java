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

/**
 *
 * @author admin
 */
public class QuanLySP extends QuanLy {
    static int index;
    @Override
    public DefaultTableModel taiTT (){
        DefaultTableModel table = new DefaultTableModel();
        try {
            Connection conn = LoginRun.con;
            CallableStatement cstmt = conn.prepareCall("SELECT * from SANPHAM WHERE XOA = 0");
            ResultSet rs = cstmt.executeQuery();
    
            String []colsName = {"STT", "Mã SP", "Tên SP", "Đơn vị tính", "Nước sản xuất", "Giá" };
            table.setColumnIdentifiers(colsName);
            index = 1;
            try {
            while(rs.next()){ // nếu còn đọc tiếp được một dòng dữ liệu
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
                System.out.println(index);
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
                System.out.println("row = " + i);
        table.removeRow(i);
        int j = i;
        System.out.println(count);
        for(  ; j <(index-count-1); j++ ){
            table.setValueAt(j+1, j, 0);
        }
        return table;
    }

    @Override
    public void xoaDongTrenSQL(int i) {
        try {
            Connection conn = LoginRun.con;
            CallableStatement cstmt = conn.prepareCall("SELECT MASP from SANPHAM WHERE XOA = 0");
            ResultSet rs = cstmt.executeQuery();
            int temp = 0; String masp="";
            try {
                while(rs.next()&&temp<=i){
                    if(temp == i){
                        masp = rs.getString("MASP");
                        System.out.println(masp);
                    }
                    temp++;
                }
            } catch (SQLException e) {
            }
//            cstmt = conn.prepareCall("DELETE from KHACHHANG where MAKH = '" + makh + "'"); 
            cstmt = conn.prepareCall("UPDATE SANPHAM SET XOA =" + 1 + " WHERE MASP='" + masp + "'");
            cstmt.execute();
            
        } catch (SQLException ex) {
            System.err.println("Cannot connect database, " + ex);
        }
    }
    
    public boolean them(String MaSP, String TenSP, String Dvt, String NuocSX, String Gia ) {
        try {
            Connection conn = LoginRun.con;
            CallableStatement cstmt = conn.prepareCall("INSERT INTO SANPHAM VALUES ('" + MaSP + "', '" + TenSP + "', '"
                    + Dvt + "', '" + NuocSX +"', '" +  Gia + "', '" + 0 + "')");
            cstmt.execute();
            return true;
            
        } catch (SQLException ex) {
            System.err.println("Cannot connect database, " + ex);
            return false;
        }
        
    }
    
}
