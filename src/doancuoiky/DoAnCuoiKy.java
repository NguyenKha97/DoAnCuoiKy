/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package doancuoiky;

import Screens.Login;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;


/**
 *
 * @author admin
 */
public class DoAnCuoiKy {

    /**
     * @param temp
     * @return 
     */
    
    
    
        public String tangMa(String temp){
        String haiKiTuDau = temp.substring(0, 2);
        String haiKiTuSau = temp.substring(2);
        int x = Integer.parseInt(haiKiTuSau);
        String so = "0";
        if(++x <= 9)
            so = so.concat(Integer.toString(x));
        else
            so = Integer.toString(x);
        temp = haiKiTuDau;
        temp = temp.concat(so);
        return temp;
    }

    public static void resizeColumnWidth(JTable table) {
        final TableColumnModel columnModel = table.getColumnModel();
        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 15; // Min width
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                Component comp = table.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width + 1, width);
            }
            if (width > 300) {
                width = 300;
            }
            columnModel.getColumn(column).setPreferredWidth(width);
        }

    }
    
    /**
     *
     * @param table
     */
    public static void setRightRendererAndResizeWitdh(JTable table) {
        resizeColumnWidth(table);
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(rightRenderer);
        }

    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        Login frmlogin1 = new Login();
        frmlogin1.setVisible(true);
        
    }
            
    
}
