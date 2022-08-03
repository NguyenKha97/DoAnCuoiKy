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
public class QuanLyKH {
    public DefaultTableModel taiTTKH(){
        DefaultTableModel table = new DefaultTableModel();
        try {
            Connection conn = LoginRun.con;
            CallableStatement cstmt = conn.prepareCall("SELECT * from KHACHHANG");
            ResultSet rs = cstmt.executeQuery();
    
            String []colsName = {"STT", "Mã KH", "Họ và tên", "Địa chỉ", "Số ĐT", "Sinh nhật", "Ngày ĐK", "Doanh số", "Loại KH" };
            table.setColumnIdentifiers(colsName);
            int index = 1;
            try {
            while(rs.next()){ // nếu còn đọc tiếp được một dòng dữ liệu
                String rows[] = new String[9];
                rows[0] = Integer.toString(index);
                rows[1] = rs.getString(1); // lấy dữ liệu tại cột số 1 (ứng với mã hàng) 
                rows[2] = rs.getString(2); // lấy dữ liệu tai cột số 2 ứng với tên hàng
                rows[3] = rs.getString(3);
                rows[4] = rs.getString(4);
                rows[5] = rs.getString(5);
                rows[6] = rs.getString(6);
                rows[7] = rs.getString(7);
                rows[8] = rs.getString(8);
                
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
}
