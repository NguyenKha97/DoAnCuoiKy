/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package D02_ThucThi;

import D01_KetNoi.KetNoi;
import Screens.BanHangChoNV;
import Screens.Login;
import Screens.Main;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import static Screens.Main.qlcthd;
import static Screens.Main.qlkh;
import static Screens.Main.qlnv;
import static Screens.Main.tableKhachHang;
import static Screens.Main.tableNhanVien;
import static Screens.Main.taittkh;
import static Screens.Main.taittnv;
import static Screens.Main.taittsp;
import static Screens.Main.taitthd;
import static Screens.Main.taittcthd;
import static Screens.Main.tableSanPham;
import static Screens.Main.dfTableKH;
import static Screens.Main.dfTableNV;
import static Screens.Main.dfTableSP;
import static Screens.Main.dfTableHD;
import static Screens.Main.dfTableCTHD;
import static Screens.Main.qlhd;
import static Screens.Main.qlsp;
import static Screens.Main.tableHoaDon;
import static Screens.Main.tableCTHD;
import static doancuoiky.DoAnCuoiKy.setRightRendererAndResizeWitdh;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author admin
 */
public class LoginRun {
    
    
    public static void execute() throws SQLException {
        if (KetNoi.getConnection()!= null) {
            Login.statusLogin = true;
        } else {
            JOptionPane.showMessageDialog(null, "ĐĂNG NHẬP KHÔNG THÀNH CÔNG:\n" + "Quá trình kết nối gặp vấn đề. Vui lòng kiểm tra lại");
        }
    }
    
    public static void getAdminScreens() {
        Main main = new Main();
        main.setVisible(true);
        main.setLocationRelativeTo(null);
        dfTableKH = qlkh.taiTT();
        dfTableSP = qlsp.taiTT();
        dfTableNV = qlnv.taiTT();
        dfTableHD = qlhd.taiTT();
        dfTableCTHD = qlcthd.taiTT();
        tableNhanVien.setModel(dfTableNV);
        tableSanPham.setModel(dfTableSP);
        tableKhachHang.setModel(dfTableKH);
        tableHoaDon.setModel(dfTableHD);
        tableCTHD.setModel(dfTableCTHD);
        setRightRendererAndResizeWitdh(tableKhachHang);
        setRightRendererAndResizeWitdh(tableNhanVien);
        setRightRendererAndResizeWitdh(tableSanPham);
        setRightRendererAndResizeWitdh(tableHoaDon);
        setRightRendererAndResizeWitdh(tableCTHD);
        taittkh = taittnv = taittsp = taitthd = taittcthd = true;
    }
    
    public static void getStaffScreens() {
        BanHangChoNV banHang = new BanHangChoNV();
        banHang.setVisible(true);
        banHang.setLocationRelativeTo(null);
        Main main = new Main();
        main.setVisible(false);
        dfTableKH = qlkh.taiTT();
        dfTableSP = qlsp.taiTT();
        dfTableHD = qlhd.taiTT();
        dfTableCTHD = qlcthd.taiTT();
        BanHangChoNV.tableSanPham.setModel(dfTableSP);
        BanHangChoNV.tableKhachHang.setModel(dfTableKH);
        BanHangChoNV.tableHoaDon.setModel(dfTableHD);
        setRightRendererAndResizeWitdh(BanHangChoNV.tableKhachHang);
        setRightRendererAndResizeWitdh(BanHangChoNV.tableSanPham);
        setRightRendererAndResizeWitdh(BanHangChoNV.tableHoaDon);
        taittkh = taittsp = taitthd = taittcthd = true;
        DefaultComboBoxModel comboModels = qlsp.getListMaSP();
        BanHangChoNV.txtSoHD.setText(qlhd.getNewSoHD());
        BanHangChoNV.txtMaNV.setText("Mã nhân viên: " + Login.txtUser.getText().toUpperCase());
        BanHangChoNV.txtHoTenNV.setText("Họ tên NV: " + Main.qlnv.getTenNV(Login.txtUser.getText().toUpperCase()));
        BanHangChoNV.txtNgayTaoHD.setText("Ngày tạo: " + new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime()));
        BanHangChoNV.comboBoxMaSP.setModel(comboModels);

    }
    
}
    

