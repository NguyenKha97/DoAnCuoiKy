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
public class QuanLyNV {
    public DefaultTableModel taiTTNV(){
        DefaultTableModel table = new DefaultTableModel();
        try {
            Connection conn = LoginRun.con;
            CallableStatement cstmt = conn.prepareCall("SELECT * from NHANVIEN");
            ResultSet rs = cstmt.executeQuery();
    
            String []colsName = {"STT", "Mã NV", "Số ĐT", "Họ và tên", "Ngày vào làm" };
            table.setColumnIdentifiers(colsName);
            int index = 1;
            try {
            while(rs.next()){ // nếu còn đọc tiếp được một dòng dữ liệu
                String rows[] = new String[5];
                rows[0] = Integer.toString(index);
                rows[1] = rs.getString(1); // lấy dữ liệu tại cột số 1 (ứng với mã hàng) 
                rows[2] = rs.getString(2); // lấy dữ liệu tai cột số 2 ứng với tên hàng
                rows[3] = rs.getString(3);
                rows[4] = rs.getString(4);
                
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
