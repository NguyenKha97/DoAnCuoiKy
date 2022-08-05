/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package D02_ThucThi;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author admin
 */
public abstract class QuanLy {
    public abstract DefaultTableModel taiTT();
    public abstract DefaultTableModel xoaDong(int i, DefaultTableModel dfTable, int countButton);
    public abstract void xoaDongTrenSQL(int i);
    
}
