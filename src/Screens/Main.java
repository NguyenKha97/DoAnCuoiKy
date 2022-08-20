/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Screens;

import D02_ThucThi.QuanLy;
import D02_ThucThi.QuanLyKH;
import D02_ThucThi.QuanLyNV;
import D02_ThucThi.QuanLySP;
import D02_ThucThi.QuanLyHD;
import doancuoiky.DoAnCuoiKy;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import D02_ThucThi.QuanLyCTHD;
import D02_ThucThi.QuanLyDN;
import static Screens.QuanLyDangNhap.tableQuanLyDangNhap;
import static doancuoiky.DoAnCuoiKy.setRightRendererAndResizeWitdh;
import java.awt.Frame;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author HP
 */
public class Main extends javax.swing.JFrame {

    static public boolean screenIsOn = false;
    static public boolean taittkh, taittnv, taittsp, taitthd, taittcthd, taittdn;
    static public QuanLyKH qlkh = new QuanLyKH();
    static public QuanLyNV qlnv = new QuanLyNV();
    static public QuanLySP qlsp = new QuanLySP();
    static public QuanLyHD qlhd = new QuanLyHD();
    static public QuanLyCTHD qlcthd = new QuanLyCTHD();
    static public QuanLyDN qldn = new QuanLyDN();
    static public DefaultTableModel dfTableKH;
    static public DefaultTableModel dfTableNV;
    static public DefaultTableModel dfTableSP;
    static public DefaultTableModel dfTableHD;
    static public DefaultTableModel dfTableCTHD;
    static public DefaultTableModel dfTableQLDN;
    static int countButtonXoaKH = 1;
    static int countButtonXoaNV = 1;
    static int countButtonXoaSP = 1;
    static int countButtonXoaHD = 1;
    static int countButtonXoaCTHD = 1;
    static int choice;
    static String maSR;

    public static boolean xoaAction(javax.swing.JTable table, boolean taitt, int countButton, javax.swing.JButton button, DefaultTableModel dfTable, QuanLy ql) {
        int i = table.getSelectedRow();
        if (i >= 0 && taitt) {
            choice = JOptionPane.showConfirmDialog(button, "Ban co chac chan muon xoa du lieu nay", "Thong bao", 0);
            maSR = (String) table.getValueAt(i, 1);
            if (choice == 0) {
                ql.xoaDongTrenSQL(maSR);
                dfTable = ql.taiTT();
                table.setModel(dfTable);
                setRightRendererAndResizeWitdh(table);
                return true;
            }
        }
        return false;
    }

    private void tim(javax.swing.JTable table, String maTimKiem, int loaiTK) {
        switch (jTabbedPaneAdmin.getSelectedIndex()) {
            case 0:
                switch (loaiTK) {
                    case 0, 1, 2, 3, 4:
                        for (int i = 0; i < table.getRowCount(); i++) {
                            if (table.getValueAt(i, loaiTK).toString().equalsIgnoreCase(maTimKiem)) {
                                JOptionPane.showMessageDialog(null, "Đã tìm thấy !!!");
                                table.setRowSelectionInterval(i, i);
                                table.scrollRectToVisible(table.getCellRect(i, 2, true));
                                return;
                            }
                        }
                        JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu có mã " + txtMa.getText() + "\nVui lòng kiểm tra lại thông tin tìm kiếm!!!");
                        break;
                    case 5:
                        if (qlkh.timKH(maTimKiem, "NGSINH") == null) {
                            JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu có mã " + txtMa.getText() + "\nVui lòng kiểm tra lại thông tin tìm kiếm!!!");
                        } else {
                            tableKhachHang.setModel(qlkh.timKH(maTimKiem, "NGSINH"));
                            setRightRendererAndResizeWitdh(tableKhachHang);
                        }
                        break;
                    case 6:
                        if (qlkh.timKH(maTimKiem, "NGDK") == null) {
                            JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu có mã " + txtMa.getText() + "\nVui lòng kiểm tra lại thông tin tìm kiếm!!!");
                        } else {
                            tableKhachHang.setModel(qlkh.timKH(maTimKiem, "NGDK"));
                            setRightRendererAndResizeWitdh(tableKhachHang);
                        }
                        break;
                    case 7:
                        if (qlkh.timKH(maTimKiem, "DOANHSO") == null) {
                            JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu có mã " + txtMa.getText() + "\nVui lòng kiểm tra lại thông tin tìm kiếm!!!");
                        } else {
                            tableKhachHang.setModel(qlkh.timKH(maTimKiem, "DOANHSO"));
                            setRightRendererAndResizeWitdh(tableKhachHang);
                        }
                        break;
                    case 8:
                        if (qlkh.timKH(maTimKiem, "LOAIKH") == null) {
                            JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu có mã " + txtMa.getText() + "\nVui lòng kiểm tra lại thông tin tìm kiếm!!!");
                        } else {
                            tableKhachHang.setModel(qlkh.timKH(maTimKiem, "LOAIKH"));
                            setRightRendererAndResizeWitdh(tableKhachHang);
                        }
                        break;
                }
                break;
            case 1:
                switch (loaiTK) {
                    case 0, 1, 2, 3, 4:
                        for (int i = 0; i < table.getRowCount(); i++) {
                            if (table.getValueAt(i, loaiTK).toString().equalsIgnoreCase(maTimKiem)) {
                                JOptionPane.showMessageDialog(null, "Đã tìm thấy !!!");
                                table.setRowSelectionInterval(i, i);
                                table.scrollRectToVisible(table.getCellRect(i, 2, true));
                                return;
                            }
                        }
                        JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu có mã " + txtMa.getText() + "\nVui lòng kiểm tra lại thông tin tìm kiếm!!!");
                        break;
                    case 5:
                        if (qlnv.timNV(maTimKiem, "NGVL") == null) {
                            JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu có mã " + txtMa.getText() + "\nVui lòng kiểm tra lại thông tin tìm kiếm!!!");
                        } else {
                            tableNhanVien.setModel(qlnv.timNV(maTimKiem, "NGVL"));
                            setRightRendererAndResizeWitdh(tableNhanVien);
                        }
                        break;
                }
                break;
            case 2:
                switch (loaiTK) {
                    case 0, 1:
                        for (int i = 0; i < table.getRowCount(); i++) {
                            if (table.getValueAt(i, loaiTK).toString().equalsIgnoreCase(maTimKiem)) {
                                JOptionPane.showMessageDialog(null, "Đã tìm thấy !!!");
                                table.setRowSelectionInterval(i, i);
                                table.scrollRectToVisible(table.getCellRect(i, 2, true));
                                return;
                            }
                        }
                        JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu có mã " + txtMa.getText() + "\nVui lòng kiểm tra lại thông tin tìm kiếm!!!");
                        break;
                    case 2:
                        if (qlsp.timSP(maTimKiem, "TENSP") == null) {
                            JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu có mã " + txtMa.getText() + "\nVui lòng kiểm tra lại thông tin tìm kiếm!!!");
                        } else {
                            tableSanPham.setModel(qlsp.timSP(maTimKiem, "TENSP"));
                            setRightRendererAndResizeWitdh(tableSanPham);
                        }
                        break;
                    case 3:
                        if (qlsp.timSP(maTimKiem, "DVT") == null) {
                            JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu có mã " + txtMa.getText() + "\nVui lòng kiểm tra lại thông tin tìm kiếm!!!");
                        } else {
                            tableSanPham.setModel(qlsp.timSP(maTimKiem, "DVT"));
                            setRightRendererAndResizeWitdh(tableSanPham);
                        }
                        break;
                    case 4:
                        if (qlsp.timSP(maTimKiem, "NUOCSX") == null) {
                            JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu có mã " + txtMa.getText() + "\nVui lòng kiểm tra lại thông tin tìm kiếm!!!");
                        } else {
                            tableSanPham.setModel(qlsp.timSP(maTimKiem, "DVT"));
                            setRightRendererAndResizeWitdh(tableSanPham);
                        }
                        break;
                    case 5:
                        if (qlsp.timSP(maTimKiem, "GIA") == null) {
                            JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu có mã " + txtMa.getText() + "\nVui lòng kiểm tra lại thông tin tìm kiếm!!!");
                        } else {
                            tableSanPham.setModel(qlsp.timSP(maTimKiem, "GIA"));
                            setRightRendererAndResizeWitdh(tableSanPham);
                        }
                        break;
                }
                break;
            case 3:
                switch (loaiTK) {
                    case 0, 1:
                        for (int i = 0; i < table.getRowCount(); i++) {
                            if (table.getValueAt(i, loaiTK).toString().equalsIgnoreCase(maTimKiem)) {
                                JOptionPane.showMessageDialog(null, "Đã tìm thấy !!!");
                                table.setRowSelectionInterval(i, i);
                                table.scrollRectToVisible(table.getCellRect(i, 2, true));
                                return;
                            }
                        }
                        JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu có mã " + txtMa.getText() + "\nVui lòng kiểm tra lại thông tin tìm kiếm!!!");
                        break;
                    case 2:
                        if (qlhd.timHD(maTimKiem, "NGHD") == null) {
                            JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu có mã " + txtMa.getText() + "\nVui lòng kiểm tra lại thông tin tìm kiếm!!!");
                        } else {
                            tableHoaDon.setModel(qlhd.timHD(maTimKiem, "NGHD"));
                            setRightRendererAndResizeWitdh(tableHoaDon);
                        }
                        break;
                    case 3:
                        if (qlhd.timHD(maTimKiem, "MAKH") == null) {
                            JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu có mã " + txtMa.getText() + "\nVui lòng kiểm tra lại thông tin tìm kiếm!!!");
                        } else {
                            tableHoaDon.setModel(qlhd.timHD(maTimKiem, "MAKH"));
                            setRightRendererAndResizeWitdh(tableHoaDon);
                        }
                        break;
                    case 4:
                        if (qlhd.timHD(maTimKiem, "MANV") == null) {
                            JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu có mã " + txtMa.getText() + "\nVui lòng kiểm tra lại thông tin tìm kiếm!!!");
                        } else {
                            tableHoaDon.setModel(qlhd.timHD(maTimKiem, "MANV"));
                            setRightRendererAndResizeWitdh(tableHoaDon);
                        }
                        break;
                    case 5:
                        if (qlhd.timHD(maTimKiem, "TRIGIA") == null) {
                            JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu có mã " + txtMa.getText() + "\nVui lòng kiểm tra lại thông tin tìm kiếm!!!");
                        } else {
                            tableHoaDon.setModel(qlhd.timHD(maTimKiem, "TRIGIA"));
                            setRightRendererAndResizeWitdh(tableHoaDon);
                        }
                        break;
                }
                break;
            case 4:
                switch (loaiTK) {
                    case 0:
                        if (qlcthd.taiSoHD(maTimKiem) == null) {
                            JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu có mã " + txtMa.getText() + "\nVui lòng kiểm tra lại thông tin tìm kiếm!!!");
                        } else {
                            tableCTHD.setModel(qlcthd.taiSoHD(maTimKiem));
                            setRightRendererAndResizeWitdh(tableCTHD);
                        }
                        break;
                    case 1:
                        if (qlcthd.taiMaSP(maTimKiem) == null) {
                            JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu có mã " + txtMa.getText() + "\nVui lòng kiểm tra lại thông tin tìm kiếm!!!");
                        } else {
                            tableCTHD.setModel(qlcthd.taiMaSP(maTimKiem));
                            setRightRendererAndResizeWitdh(tableCTHD);
                        }
                        break;
                    case 2:
                        if (qlcthd.taiSL(maTimKiem) == null) {
                            JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu có mã " + txtMa.getText() + "\nVui lòng kiểm tra lại thông tin tìm kiếm!!!");
                        } else {
                            tableCTHD.setModel(qlcthd.taiSL(maTimKiem));
                            setRightRendererAndResizeWitdh(tableCTHD);
                        }
                        break;
                }
                break;
        }

        if (jTabbedPaneAdmin.getSelectedIndex() == 4) {

        } else {
            for (int i = 0; i < table.getRowCount(); i++) {
                if (table.getValueAt(i, loaiTK).toString().equalsIgnoreCase(maTimKiem)) {
                    JOptionPane.showMessageDialog(null, "Đã tìm thấy !!!");
                    table.setRowSelectionInterval(i, i);
                    table.scrollRectToVisible(table.getCellRect(i, 2, true));
                    return;
                }
            }
            JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu có mã " + txtMa.getText() + "\nVui lòng kiểm tra lại thông tin tìm kiếm!!!");
        }
    }

    public static void setComboBoxInfo(javax.swing.JTable table, DefaultComboBoxModel comboModels) {
        for (int i = 0; i < table.getColumnCount(); i++) {
            comboModels.insertElementAt(table.getColumnName(i), i);
        }
    }

    public static void disposeAll() {
        for (Frame i : Main.getFrames()) {
            i.dispose();
//            i.setVisible(false);
        }
    }

    /**
     * Creates new form Main
     */
    public Main() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSpinner1 = new javax.swing.JSpinner();
        jToggleButton1 = new javax.swing.JToggleButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        jPasswordField1 = new javax.swing.JPasswordField();
        jToggleButton2 = new javax.swing.JToggleButton();
        jPasswordField3 = new javax.swing.JPasswordField();
        jSeparator1 = new javax.swing.JSeparator();
        jTabbedPaneAdmin = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableKhachHang = new javax.swing.JTable();
        buttonTaiTTKH = new javax.swing.JButton();
        buttonThemKH = new javax.swing.JButton();
        butonCapNhatKH = new javax.swing.JButton();
        buttonXoaKH = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        buttonTaiTTNV = new javax.swing.JButton();
        buttonThemNV = new javax.swing.JButton();
        buttonXoaNV = new javax.swing.JButton();
        buttonCapNhatNV = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        tableNhanVien = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableSanPham = new javax.swing.JTable();
        buttonTaiTTSP = new javax.swing.JButton();
        buttonThemSP = new javax.swing.JButton();
        buttonXoaSP = new javax.swing.JButton();
        buttonCapNhatSP = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableHoaDon = new javax.swing.JTable();
        buttonTaiTTHD = new javax.swing.JButton();
        buttonThemHD = new javax.swing.JButton();
        buttonXemCTHD = new javax.swing.JButton();
        buttonXoaHD = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        btnTaiTTCTHD = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tableCTHD = new javax.swing.JTable();
        buttonDangXuat = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtMa = new javax.swing.JTextField();
        buttonTim = new javax.swing.JButton();
        timKiemComboBox = new javax.swing.JComboBox<>();
        buttonQLDN = new javax.swing.JButton();

        jToggleButton1.setText("jToggleButton1");

        jScrollPane2.setViewportView(jEditorPane1);

        jPasswordField1.setText("jPasswordField1");

        jToggleButton2.setText("jToggleButton2");

        jPasswordField3.setText("jPasswordField3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Màn hình chính");

        jTabbedPaneAdmin.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPaneAdminStateChanged(evt);
            }
        });

        jPanel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        tableKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã KH", "Họ và tên", "Địa chỉ", "Số ĐT", "Sinh nhật", "Ngày ĐK", "Doanh số", "Loại KH"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Short.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableKhachHang.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tableKhachHang.setDropMode(javax.swing.DropMode.ON);
        tableKhachHang.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableKhachHang.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tableKhachHang);

        buttonTaiTTKH.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        buttonTaiTTKH.setText("TẢI THÔNG TIN KHÁCH HÀNG");
        buttonTaiTTKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonTaiTTKHActionPerformed(evt);
            }
        });

        buttonThemKH.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        buttonThemKH.setText("THÊM KHÁCH HÀNG");
        buttonThemKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonThemKHActionPerformed(evt);
            }
        });

        butonCapNhatKH.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        butonCapNhatKH.setText("CẬP NHẬT TT KH");
        butonCapNhatKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butonCapNhatKHActionPerformed(evt);
            }
        });

        buttonXoaKH.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        buttonXoaKH.setText("XÓA KHÁCH HÀNG");
        buttonXoaKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonXoaKHActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonTaiTTKH)
                .addGap(40, 40, 40)
                .addComponent(buttonThemKH)
                .addGap(18, 18, 18)
                .addComponent(butonCapNhatKH)
                .addGap(18, 18, 18)
                .addComponent(buttonXoaKH)
                .addGap(0, 28, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonTaiTTKH)
                    .addComponent(buttonThemKH)
                    .addComponent(butonCapNhatKH)
                    .addComponent(buttonXoaKH))
                .addGap(34, 34, 34))
        );

        jTabbedPaneAdmin.addTab("Quản lý khách hàng", jPanel1);

        buttonTaiTTNV.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        buttonTaiTTNV.setText("TẢI THÔNG TIN NHÂN VIÊN");
        buttonTaiTTNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonTaiTTNVActionPerformed(evt);
            }
        });

        buttonThemNV.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        buttonThemNV.setText("THÊM NHÂN VIÊN");
        buttonThemNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonThemNVActionPerformed(evt);
            }
        });

        buttonXoaNV.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        buttonXoaNV.setText("XÓA NHÂN VIÊN");
        buttonXoaNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonXoaNVActionPerformed(evt);
            }
        });

        buttonCapNhatNV.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        buttonCapNhatNV.setText("CẬP NHẬT TT NV");
        buttonCapNhatNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCapNhatNVActionPerformed(evt);
            }
        });

        tableNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã NV", "Họ và tên", "Số ĐT", "Ngày vào làm"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Short.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableNhanVien.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tableNhanVien.setDropMode(javax.swing.DropMode.ON);
        tableNhanVien.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableNhanVien.getTableHeader().setReorderingAllowed(false);
        jScrollPane6.setViewportView(tableNhanVien);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonTaiTTNV)
                .addGap(46, 46, 46)
                .addComponent(buttonThemNV)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addComponent(buttonCapNhatNV)
                .addGap(46, 46, 46)
                .addComponent(buttonXoaNV)
                .addContainerGap())
            .addComponent(jScrollPane6)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonTaiTTNV)
                    .addComponent(buttonThemNV)
                    .addComponent(buttonCapNhatNV)
                    .addComponent(buttonXoaNV))
                .addGap(34, 34, 34))
        );

        jTabbedPaneAdmin.addTab("Quản lý nhân viên", jPanel2);

        tableSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã SP", "Tên SP", "Đơn vị tính", "Nước sản xuất", "Giá"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableSanPham.setDropMode(javax.swing.DropMode.ON);
        tableSanPham.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableSanPham.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(tableSanPham);

        buttonTaiTTSP.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        buttonTaiTTSP.setText("TẢI THÔNG TIN SẢN PHẨM");
        buttonTaiTTSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonTaiTTSPActionPerformed(evt);
            }
        });

        buttonThemSP.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        buttonThemSP.setText("THÊM SẢN PHẨM");
        buttonThemSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonThemSPActionPerformed(evt);
            }
        });

        buttonXoaSP.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        buttonXoaSP.setText("XÓA SẢN PHẨM");
        buttonXoaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonXoaSPActionPerformed(evt);
            }
        });

        buttonCapNhatSP.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        buttonCapNhatSP.setText("CẬP NHẬT TT SP");
        buttonCapNhatSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCapNhatSPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 948, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonTaiTTSP)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonThemSP)
                .addGap(50, 50, 50)
                .addComponent(buttonCapNhatSP)
                .addGap(43, 43, 43)
                .addComponent(buttonXoaSP)
                .addGap(35, 35, 35))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonTaiTTSP)
                    .addComponent(buttonThemSP)
                    .addComponent(buttonCapNhatSP)
                    .addComponent(buttonXoaSP))
                .addGap(36, 36, 36))
        );

        jTabbedPaneAdmin.addTab("Quản lý sản phẩm", jPanel3);

        tableHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Số HD", "Ngày Lập", "Mã Khách Hàng", "Mã Nhân Viên", "Trị Giá"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableHoaDon.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane4.setViewportView(tableHoaDon);

        buttonTaiTTHD.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        buttonTaiTTHD.setText("TẢI THÔNG TIN HÓA ĐƠN");
        buttonTaiTTHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonTaiTTHDActionPerformed(evt);
            }
        });

        buttonThemHD.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        buttonThemHD.setText("THÊM HÓA ĐƠN");
        buttonThemHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonThemHDActionPerformed(evt);
            }
        });

        buttonXemCTHD.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        buttonXemCTHD.setText("XEM CHI TIẾT HÓA ĐƠN");
        buttonXemCTHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonXemCTHDActionPerformed(evt);
            }
        });

        buttonXoaHD.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        buttonXoaHD.setText("XÓA HÓA ĐƠN");
        buttonXoaHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonXoaHDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 948, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonTaiTTHD)
                .addGap(30, 30, 30)
                .addComponent(buttonThemHD)
                .addGap(40, 40, 40)
                .addComponent(buttonXemCTHD)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonXoaHD)
                .addGap(26, 26, 26))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonTaiTTHD)
                    .addComponent(buttonThemHD)
                    .addComponent(buttonXemCTHD)
                    .addComponent(buttonXoaHD))
                .addGap(33, 33, 33))
        );

        jTabbedPaneAdmin.addTab("Quản lý hóa đơn", jPanel4);

        btnTaiTTCTHD.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnTaiTTCTHD.setText("TẢI CHI TIẾT HD");
        btnTaiTTCTHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaiTTCTHDActionPerformed(evt);
            }
        });
        jPanel5.add(btnTaiTTCTHD);

        tableCTHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Số HD", "Mã Sản phẩm", "Số Lượng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(tableCTHD);

        jPanel5.add(jScrollPane5);

        jTabbedPaneAdmin.addTab("Quản lý chi tiết hóa đơn", jPanel5);

        buttonDangXuat.setText("Đăng xuất");
        buttonDangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDangXuatActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("ỨNG DỤNG QUẢN LÝ BÁN HÀNG");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Tìm kiếm nhanh theo ");

        buttonTim.setText("Tìm");
        buttonTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonTimActionPerformed(evt);
            }
        });

        timKiemComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        buttonQLDN.setText("Quản lý đăng nhập");
        buttonQLDN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonQLDNActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPaneAdmin)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(286, 286, 286)
                        .addComponent(jLabel2)
                        .addGap(1, 1, 1)
                        .addComponent(timKiemComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonTim)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(182, 182, 182)
                        .addComponent(buttonDangXuat))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonQLDN)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonTim)
                            .addComponent(timKiemComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(buttonDangXuat)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonQLDN)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPaneAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonDangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDangXuatActionPerformed
        Login.statusLogin = false;
        disposeAll();
        dispose();
        screenIsOn = false;
        Login frmLogin = new Login();
        frmLogin.setLocationRelativeTo(null);
        frmLogin.setVisible(true);
    }//GEN-LAST:event_buttonDangXuatActionPerformed

    private void buttonTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTimActionPerformed
        if (txtMa.getText().isBlank() || timKiemComboBox.getSelectedIndex() == -1)
            JOptionPane.showMessageDialog(rootPane, "Hãy nhập đầy đủ thông tin để tìm kiếm");
        else {
            String maTimKiem = txtMa.getText();
            int loaiTK = timKiemComboBox.getSelectedIndex();
            switch (jTabbedPaneAdmin.getSelectedIndex()) {
                case 0 ->
                    tim(tableKhachHang, maTimKiem, loaiTK);
                case 1 ->
                    tim(tableNhanVien, maTimKiem, loaiTK);
                case 2 ->
                    tim(tableSanPham, maTimKiem, loaiTK);
                case 3 ->
                    tim(tableHoaDon, maTimKiem, loaiTK);
                case 4 ->
                    tim(tableCTHD, maTimKiem, loaiTK);
            }
        }
    }//GEN-LAST:event_buttonTimActionPerformed

    private void jTabbedPaneAdminStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPaneAdminStateChanged
        DefaultComboBoxModel comboModels = new DefaultComboBoxModel();
        switch (jTabbedPaneAdmin.getSelectedIndex()) {
            case 0 ->
                setComboBoxInfo(tableKhachHang, comboModels);
            case 1 ->
                setComboBoxInfo(tableNhanVien, comboModels);
            case 2 ->
                setComboBoxInfo(tableSanPham, comboModels);
            case 3 ->
                setComboBoxInfo(tableHoaDon, comboModels);
            case 4 ->
                setComboBoxInfo(tableCTHD, comboModels);
        }
        timKiemComboBox.setModel(comboModels);
        timKiemComboBox.setSelectedIndex(0);
        toBack();
    }//GEN-LAST:event_jTabbedPaneAdminStateChanged

    private void btnTaiTTCTHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaiTTCTHDActionPerformed
        toBack();
        choice = 3;
        dfTableCTHD = qlcthd.taiTT();
        tableCTHD.setModel(dfTableCTHD);
        setRightRendererAndResizeWitdh(tableCTHD);
    }//GEN-LAST:event_btnTaiTTCTHDActionPerformed

    private void buttonXoaHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonXoaHDActionPerformed
        int i = tableHoaDon.getSelectedRow();
        if (i >= 0 && taitthd && !screenIsOn) {
            if (xoaAction(tableHoaDon, taitthd, countButtonXoaHD, buttonXoaHD, dfTableHD, qlhd)) {
                JOptionPane.showMessageDialog(rootPane, "Xóa thành công");
                countButtonXoaHD++;
                qlcthd.xoaDongTrenSQL(maSR);
                dfTableCTHD = qlcthd.taiTT();
                tableCTHD.setModel(dfTableCTHD);
                setRightRendererAndResizeWitdh(tableCTHD);
                String maKh = qlhd.layMaKH(maSR);
                long trigia = -qlhd.getTriGia(maSR);
                qlkh.capNhatDS(maKh, trigia);
                dfTableKH = qlkh.taiTT();
                tableKhachHang.setModel(dfTableKH);
                setRightRendererAndResizeWitdh(tableKhachHang);
            } else {
                if (choice == 0) {
                    JOptionPane.showMessageDialog(rootPane, "Không thành công.\nVui lòng kiểm tra lại");                    
                }
            }
            choice = 1;
        }
        toBack();
        if (!screenIsOn && choice != 1)
            JOptionPane.showMessageDialog(rootPane, "Click chọn dòng để thực hiện");
    }//GEN-LAST:event_buttonXoaHDActionPerformed

    private void buttonXemCTHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonXemCTHDActionPerformed
        int i = tableHoaDon.getSelectedRow();
        if (i >= 0 && taitthd && !screenIsOn) {
            CapNhatTTHD capnhattthd = new CapNhatTTHD();
            String mahd = tableHoaDon.getValueAt(i, 1).toString();
            DefaultTableModel tableCT = qlcthd.getCTTheoHD(mahd);
            CapNhatTTHD.tableCTHD.setModel(tableCT);
            setRightRendererAndResizeWitdh(CapNhatTTHD.tableCTHD);
            taittcthd = true;
            CapNhatTTHD.txtsoHD.setText("CHI TIẾT HÓA ĐƠN : " + tableHoaDon.getValueAt(i, 1).toString());
            String maNV = tableHoaDon.getValueAt(i, 4).toString(), maKH = tableHoaDon.getValueAt(i, 3).toString();
            CapNhatTTHD.txtNV.setText(maNV + " - " + qlnv.getTenNV(maNV));
            CapNhatTTHD.txtKH.setText(maKH + " - " + qlkh.getTenKH(maKH));
            CapNhatTTHD.txtNgHD.setText("Ngày lập: " + tableHoaDon.getValueAt(i, 2).toString());
            capnhattthd.setLocationRelativeTo(null);
            capnhattthd.setVisible(true);
            screenIsOn = true;
        }
        toBack();
        if (!screenIsOn && choice != 1)
            JOptionPane.showMessageDialog(rootPane, "Click chọn dòng để thực hiện");
    }//GEN-LAST:event_buttonXemCTHDActionPerformed

    private void buttonThemHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonThemHDActionPerformed
        if (!screenIsOn) {
            NhapTTHD nhaptthd = new NhapTTHD();
            DefaultComboBoxModel comboModels = qlsp.getListMaSP();
            NhapTTHD.maSPComboBox.setModel(comboModels);
            comboModels = qlnv.getListMaNV();
            NhapTTHD.maNVComboBox.setModel(comboModels);
            NhapTTHD.txtNgayHD.setDate(Calendar.getInstance().getTime());
            NhapTTHD.txtSoHD.setText(qlhd.getNewSoHD());
            nhaptthd.setLocationRelativeTo(null);
            nhaptthd.setVisible(true);
            screenIsOn = true;
        }
        toBack();
    }//GEN-LAST:event_buttonThemHDActionPerformed

    private void buttonTaiTTHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTaiTTHDActionPerformed
        toBack();
        choice = 3;
        dfTableHD = qlhd.taiTT();
        tableHoaDon.setModel(dfTableHD);
        setRightRendererAndResizeWitdh(tableHoaDon);
    }//GEN-LAST:event_buttonTaiTTHDActionPerformed

    private void buttonCapNhatSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCapNhatSPActionPerformed
        int i = tableSanPham.getSelectedRow();
        if (i >= 0 && taittsp && !screenIsOn) {
            CapNhatTTSP capnhatsp = new CapNhatTTSP();
            capnhatsp.setLocationRelativeTo(null);
            capnhatsp.setVisible(true);
            capnhatsp.txtMaSP.setText((String) tableSanPham.getValueAt(tableSanPham.getSelectedRow(), 1));
            capnhatsp.txtTenSP.setText((String) tableSanPham.getValueAt(tableSanPham.getSelectedRow(), 2));
            capnhatsp.donViTinh.setSelectedItem((String) tableSanPham.getValueAt(tableSanPham.getSelectedRow(), 3));
            capnhatsp.txtNuocSX.setText((String) tableSanPham.getValueAt(tableSanPham.getSelectedRow(), 4));
            String gia = (String) tableSanPham.getValueAt(tableSanPham.getSelectedRow(), 5);
            String[] temp4 = gia.split(" VNĐ");
            gia = temp4[0];
            gia = gia.replace(",", "");
            capnhatsp.txtGia.setText(gia);
            screenIsOn = true;
        }
        toBack();
        if (!screenIsOn && choice != 1)
            JOptionPane.showMessageDialog(rootPane, "Click chọn dòng để thực hiện");
    }//GEN-LAST:event_buttonCapNhatSPActionPerformed

    private void buttonXoaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonXoaSPActionPerformed
        int i = tableSanPham.getSelectedRow();
        if (i >= 0 && taittsp && !screenIsOn) {
            if (xoaAction(tableSanPham, taittsp, countButtonXoaSP, buttonXoaSP, dfTableSP, qlsp)) {
                countButtonXoaSP++;
                JOptionPane.showMessageDialog(rootPane, "Xóa thành công");
            } else {
                if (choice == 0) {
                    JOptionPane.showMessageDialog(rootPane, "Không thành công.\nVui lòng kiểm tra lại");
                }
            }
            choice = 1;
        }
        toBack();
        if (!screenIsOn && choice != 1)
            JOptionPane.showMessageDialog(rootPane, "Click chọn dòng để thực hiện");
    }//GEN-LAST:event_buttonXoaSPActionPerformed

    private void buttonThemSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonThemSPActionPerformed
        if (!screenIsOn) {
            NhapTTSP nhapttsp = new NhapTTSP();
            nhapttsp.setLocationRelativeTo(null);
            nhapttsp.setVisible(true);
            NhapTTSP.txtGia.setText("500");
            screenIsOn = true;
        }
        toBack();
    }//GEN-LAST:event_buttonThemSPActionPerformed

    private void buttonTaiTTSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTaiTTSPActionPerformed
        toBack();
        choice = 3;
        dfTableSP = qlsp.taiTT();
        tableSanPham.setModel(dfTableSP);
        setRightRendererAndResizeWitdh(tableSanPham);
    }//GEN-LAST:event_buttonTaiTTSPActionPerformed

    private void buttonCapNhatNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCapNhatNVActionPerformed
        int i = tableNhanVien.getSelectedRow();
        if (i >= 0 && taittnv && !screenIsOn) {
            CapNhatTTNV capnhatnv = new CapNhatTTNV();
            capnhatnv.setLocationRelativeTo(null);
            capnhatnv.setVisible(true);
            capnhatnv.txtmaNV.setText((String) tableNhanVien.getValueAt(tableNhanVien.getSelectedRow(), 1));
            capnhatnv.txtHoTenNV.setText((String) tableNhanVien.getValueAt(tableNhanVien.getSelectedRow(), 2));
            capnhatnv.txtSoDTNV.setText((String) tableNhanVien.getValueAt(tableNhanVien.getSelectedRow(), 3));
            capnhatnv.chonNgVaoLam.setDate(qlnv.getDate(i));
            screenIsOn = true;
        }
        toBack();
        if (!screenIsOn && choice != 1)
            JOptionPane.showMessageDialog(rootPane, "Click chọn dòng để thực hiện");
    }//GEN-LAST:event_buttonCapNhatNVActionPerformed

    private void buttonXoaNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonXoaNVActionPerformed
        int i = tableNhanVien.getSelectedRow();
        if (i >= 0 && taittnv && !screenIsOn) {
            if (xoaAction(tableNhanVien, taittnv, countButtonXoaNV, buttonXoaNV, dfTableNV, qlnv)) {
                countButtonXoaNV++;
                dfTableQLDN = qldn.taiTT();
                tableQuanLyDangNhap.setModel(dfTableQLDN);
                setRightRendererAndResizeWitdh(tableQuanLyDangNhap);
                JOptionPane.showMessageDialog(rootPane, "Xóa thành công");
            } else {
                if (choice == 0) {
                    JOptionPane.showMessageDialog(rootPane, "Không thành công.\nVui lòng kiểm tra lại");
                }
            }
            choice = 1;
        }
        toBack();
        if (!screenIsOn && choice != 1)
            JOptionPane.showMessageDialog(rootPane, "Click chọn dòng để thực hiện");
    }//GEN-LAST:event_buttonXoaNVActionPerformed

    private void buttonThemNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonThemNVActionPerformed
        if (!screenIsOn) {
            NhapTTNV nhapttnv = new NhapTTNV();
            nhapttnv.setLocationRelativeTo(null);
            nhapttnv.setVisible(true);
            NhapTTNV.txtmaNV.setText(new DoAnCuoiKy().tangMa(qlnv.getMaCuoi()));
            NhapTTNV.chonNgVaoLam.setDate(Calendar.getInstance().getTime());
            screenIsOn = true;
        }
        toBack();
    }//GEN-LAST:event_buttonThemNVActionPerformed

    private void buttonTaiTTNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTaiTTNVActionPerformed
        toBack();
        choice = 3;
        dfTableNV = qlnv.taiTT();
        tableNhanVien.setModel(dfTableNV);
        setRightRendererAndResizeWitdh(tableNhanVien);
        dfTableQLDN = qldn.taiTT();
        tableQuanLyDangNhap.setModel(dfTableQLDN);
        setRightRendererAndResizeWitdh(tableQuanLyDangNhap);
    }//GEN-LAST:event_buttonTaiTTNVActionPerformed

    private void buttonXoaKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonXoaKHActionPerformed
        String maKH = "";
        if (tableKhachHang.getSelectedRow() >= 0 && taittkh && !screenIsOn) {
            maKH = tableKhachHang.getValueAt(tableKhachHang.getSelectedRow(), 1).toString();
            if (xoaAction(tableKhachHang, taittkh, countButtonXoaKH, buttonXoaKH, dfTableKH, qlkh)) {
                JOptionPane.showMessageDialog(rootPane, "Xóa thành công");
                countButtonXoaKH++;
                if (qlhd.check(maKH)) {
                    qlhd.xoaDongTrenSQL(qlhd.laySoHDTheoMaKH(maSR));
                    dfTableHD = qlhd.taiTT();
                    tableHoaDon.setModel(dfTableHD);
                    setRightRendererAndResizeWitdh(tableHoaDon);
                    qlcthd.xoaDongTrenSQL(qlhd.laySoHDTheoMaKH(maSR));
                    dfTableCTHD = qlcthd.taiTT();
                    tableCTHD.setModel(dfTableCTHD);
                    setRightRendererAndResizeWitdh(tableCTHD);
                }
            } else {
                if (choice == 0) {
                    JOptionPane.showMessageDialog(rootPane, "Không thành công.\nVui lòng kiểm tra lại");
                }
            }
            choice = 1;
        }
        toBack();
        if (!screenIsOn && choice != 1)
            JOptionPane.showMessageDialog(rootPane, "Click chọn dòng để thực hiện");
    }//GEN-LAST:event_buttonXoaKHActionPerformed

    private void butonCapNhatKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butonCapNhatKHActionPerformed
        int i = tableKhachHang.getSelectedRow();
        if (i >= 0 && taittkh && !screenIsOn) {
            CapNhatTTKH capnhatkh = new CapNhatTTKH();
            capnhatkh.setLocationRelativeTo(null);
            capnhatkh.setVisible(true);
            capnhatkh.txtmaKH.setText((String) tableKhachHang.getValueAt(tableKhachHang.getSelectedRow(), 1));
            capnhatkh.txtHoTenKH.setText((String) tableKhachHang.getValueAt(tableKhachHang.getSelectedRow(), 2));
            capnhatkh.txtDiaChiKH.setText((String) tableKhachHang.getValueAt(tableKhachHang.getSelectedRow(), 3));
            capnhatkh.txtSoDTKH.setText((String) tableKhachHang.getValueAt(tableKhachHang.getSelectedRow(), 4));
            capnhatkh.chonSinhNhat.setDate(qlkh.getDate(i));
            screenIsOn = true;
        }
        toBack();
        if (!screenIsOn && choice != 1)
            JOptionPane.showMessageDialog(rootPane, "Click chọn dòng để thực hiện");
    }//GEN-LAST:event_butonCapNhatKHActionPerformed

    private void buttonThemKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonThemKHActionPerformed
        if (!screenIsOn) {
            NhapTTKH nhapttkh = new NhapTTKH();
            nhapttkh.setLocationRelativeTo(null);
            nhapttkh.setVisible(true);
            nhapttkh.maKh.setText(new DoAnCuoiKy().tangMa(qlkh.getMaCuoi()));
            nhapttkh.ngdk.setText(new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime()));
            screenIsOn = true;
        }
        toBack();
    }//GEN-LAST:event_buttonThemKHActionPerformed

    private void buttonTaiTTKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTaiTTKHActionPerformed
        dfTableKH = qlkh.taiTT();
        tableKhachHang.setModel(dfTableKH);
        setRightRendererAndResizeWitdh(tableKhachHang);
        toBack();
        choice = 3;
    }//GEN-LAST:event_buttonTaiTTKHActionPerformed

    private void buttonQLDNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonQLDNActionPerformed
        if(!screenIsOn) {
        QuanLyDangNhap screenQLDN = new QuanLyDangNhap();
        screenQLDN.setLocationRelativeTo(null);
        screenQLDN.setVisible(true);
        QuanLyDangNhap.txtPassAdmin.setText("********");
        QuanLyDangNhap.txtMaBaoVe.setText("********");
        dfTableQLDN = qldn.taiTT();
        tableQuanLyDangNhap.setModel(dfTableQLDN);
        setRightRendererAndResizeWitdh(tableQuanLyDangNhap);
        taittdn = true;
        screenIsOn = true;
        }
        toBack();
    }//GEN-LAST:event_buttonQLDNActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
//                taittnv = taittsp = taittkh = taitthd = taittcthd = true;
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JButton btnTaiTTCTHD;
    javax.swing.JButton butonCapNhatKH;
    javax.swing.JButton buttonCapNhatNV;
    javax.swing.JButton buttonCapNhatSP;
    private javax.swing.JButton buttonDangXuat;
    private javax.swing.JButton buttonQLDN;
    private javax.swing.JButton buttonTaiTTHD;
    private javax.swing.JButton buttonTaiTTKH;
    private javax.swing.JButton buttonTaiTTNV;
    private javax.swing.JButton buttonTaiTTSP;
    private javax.swing.JButton buttonThemHD;
    private javax.swing.JButton buttonThemKH;
    private javax.swing.JButton buttonThemNV;
    private javax.swing.JButton buttonThemSP;
    javax.swing.JButton buttonTim;
    private javax.swing.JButton buttonXemCTHD;
    private javax.swing.JButton buttonXoaHD;
    private javax.swing.JButton buttonXoaKH;
    private javax.swing.JButton buttonXoaNV;
    private javax.swing.JButton buttonXoaSP;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPasswordField jPasswordField3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSpinner jSpinner1;
    private static javax.swing.JTabbedPane jTabbedPaneAdmin;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToggleButton jToggleButton2;
    public static javax.swing.JTable tableCTHD;
    public static javax.swing.JTable tableHoaDon;
    public static javax.swing.JTable tableKhachHang;
    public static javax.swing.JTable tableNhanVien;
    public static javax.swing.JTable tableSanPham;
    public static javax.swing.JComboBox<String> timKiemComboBox;
    static javax.swing.JTextField txtMa;
    // End of variables declaration//GEN-END:variables
}
