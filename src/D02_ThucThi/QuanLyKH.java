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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author admin
 */
public class QuanLyKH extends QuanLy {

    static int indexRow;
//    static Connection conQLKH = KetNoi.getNewConnection();
    DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel();

    public String getMaCuoi() {
        String result = "";
        try {
            Connection conQLKH = KetNoi.getConnection();
            CallableStatement cstmt = conQLKH.prepareCall("SELECT TOP 1 MAKH FROM KHACHHANG ORDER BY MAKH DESC");
            ResultSet rs = cstmt.executeQuery();
            rs.next();
            result = rs.getString(1);
        } catch (SQLException ex) {
            System.err.println("Không thể lấy mã KH cuối cùng, " + ex);
        }
        return result;
    }

    @Override
    public DefaultTableModel taiTT() {
        DefaultTableModel table = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        try {
            Connection conQLKH = KetNoi.getConnection();
            CallableStatement cstmt = conQLKH.prepareCall("SELECT * from KHACHHANG WHERE XOA = 0");
            ResultSet rs = cstmt.executeQuery();

            String[] colsName = {"STT", "Mã KH", "Họ và tên", "Địa chỉ", "Số ĐT", "Sinh nhật", "Ngày ĐK", "Doanh số", "Loại KH"};
            table.setColumnIdentifiers(colsName);
            indexRow = 1;
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            try {
                while (rs.next()) { // nếu còn đọc tiếp được một dòng dữ liệu
                    String rows[] = new String[9];
                    rows[0] = Integer.toString(indexRow);
                    rows[1] = rs.getString(1); // lấy dữ liệu tại cột số 1 (ứng với mã ) 
                    rows[2] = rs.getString(2); // lấy dữ liệu tai cột số 2 ứng với tên
                    rows[3] = rs.getString(3);
                    rows[4] = rs.getString(4);
                    rows[5] = df.format(rs.getDate(5));
                    rows[6] = df.format(rs.getDate(6));
                    rows[7] = String.format("%,d", rs.getLong(7)) + " VNĐ";
                    rows[8] = rs.getString(8);

                    table.addRow(rows); // đưa dòng dữ liệu vào tableModel để hiện thị lên jtable
                    indexRow++;
                }
            } catch (SQLException e) {
                System.out.println("Lỗi lấy dữ liệu resultSet trong QLKH");
            }

        } catch (SQLException ex) {
            System.err.println("Lỗi kết nỗi hoặc querry trong QLKH " + ex);
        }
        return table;
    }

    @Override
    public void xoaDongTrenSQL(String ma) {
        try {
            Connection conQLKH = KetNoi.getConnection();
            CallableStatement cstmt = conQLKH.prepareCall("UPDATE KHACHHANG SET XOA =" + 1 + " WHERE MAKH='" + ma + "'");
            cstmt.execute();

        } catch (SQLException ex) {
            System.err.println("Cannot connect database, " + ex);
        }
    }

    public boolean them(String MaKH, String HoTenKH, String DiaChiKH, String SoDTKH, Date SinhNhatKH, Date NgDKKH) {
        try {
            String loaiKH = "Vang lai";
            Connection conQLKH = KetNoi.getConnection();
            CallableStatement cstmt = conQLKH.prepareCall("INSERT INTO KHACHHANG VALUES ('" + MaKH + "', '" + HoTenKH + "', '"
                    + DiaChiKH + "', '" + SoDTKH + "', '" + new java.sql.Date(SinhNhatKH.getTime()) + "', '"
                    + new java.sql.Date(NgDKKH.getTime()) + "', '" + 1 + "', '" + loaiKH
                    + "', '" + 0 + "')");
            cstmt.execute();
            return true;

        } catch (SQLException ex) {
            System.err.println("Cannot connect database, " + ex);
            return false;
        }

    }

    public boolean capNhat(String ma, String hoTen, String diaChi, String soDT, Date sinhNhat) {
        try {
            Connection conQLKH = KetNoi.getConnection();
            CallableStatement cstmt = conQLKH.prepareCall("UPDATE KHACHHANG SET HOTEN = '" + hoTen + "', DCHI = '" + diaChi + "', SODT = '"
                    + soDT + "', NGSINH = '" + new java.sql.Date(sinhNhat.getTime()) + "' WHERE MAKH = '"
                    + ma + "'");
            cstmt.execute();
            return true;

        } catch (SQLException ex) {
            System.err.println("Cannot connect database, " + ex);
            return false;
        }

    }

    public Date getDate(int i) {
        try {
            Connection conQLKH = KetNoi.getConnection();
            CallableStatement cstmt = conQLKH.prepareCall("SELECT NGSINH from KHACHHANG WHERE XOA = 0");
            ResultSet rs = cstmt.executeQuery();
            int temp = 0;
            try {
                while (rs.next() && temp <= i) {
                    if (temp == i) {
                        return rs.getDate("NGSINH");
                    }
                    temp++;
                }
            } catch (SQLException e) {
                System.out.println("loi oy");
            }

        } catch (SQLException ex) {
            System.err.println("Cannot connect database, " + ex);
        }
        return null;
    }

    @Override
    public int timMa(String makh) {
        try {
            Connection conQLKH = KetNoi.getConnection();
            CallableStatement cstmt = conQLKH.prepareCall("SELECT MAKH from KHACHHANG WHERE XOA = 0");
            ResultSet rs = cstmt.executeQuery();
            int temp = 0;
            try {
                while (rs.next()) {
                    if (rs.getString("MAKH").equalsIgnoreCase(makh)) {
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

    public void capNhatDS(String ma, long ds) {
        try {
            Connection conQLKH = KetNoi.getConnection();
            CallableStatement cstmt = conQLKH.prepareCall("SELECT DOANHSO from KHACHHANG WHERE XOA = 0 AND MAKH = '" + ma + "'");
            ResultSet rs = cstmt.executeQuery();
            rs.next();
            long dsMoi = ds + rs.getLong("DOANHSO");
            if (dsMoi == 0) {
                dsMoi = 1;
            } else if (dsMoi < 0) {
                System.out.println("Loi, doanh so khong the am. kiem tra lại QLKH");
                return;
            }
            String loaiKH;
            if (dsMoi < 100000 && dsMoi > 0) {
                loaiKH = "Vang lai";
            } else if (dsMoi >= 100000 && dsMoi < 3000000) {
                loaiKH = "Thuong xuyen";
            } else {
                loaiKH = "Vip";
            }
            cstmt = conQLKH.prepareCall("UPDATE KHACHHANG SET DOANHSO = '" + Long.toString(dsMoi) + "', LOAIKH = '" + loaiKH + "' WHERE MAKH = '" + ma + "'");
            cstmt.execute();
        } catch (SQLException ex) {
            System.err.println("Cannot connect database, " + ex);
        }
    }

    public boolean checkMaKH(String makh) {
        try {
            Connection conQLKH = KetNoi.getConnection();
            CallableStatement cstmt = conQLKH.prepareCall("SELECT MAKH from KHACHHANG WHERE XOA = 0 AND MAKH = '" + makh + "'");
            ResultSet rs = cstmt.executeQuery();
            rs.next();
            if (!rs.getString("MAKH").isEmpty()) {
                return true;
            }
        } catch (SQLException ex) {
            System.err.println("Cannot connect database, " + ex);
        }
        return false;
    }

    public String getTenKH(String makh) {
        try {
            Connection conQLSP = KetNoi.getConnection();
            CallableStatement cstmt = conQLSP.prepareCall("SELECT HOTEN from KHACHHANG WHERE MAKH = '" + makh + "'");
            ResultSet rs = cstmt.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException ex) {
            System.err.println("Cannot connect database, " + ex);
        }
        return null;
    }

    public String checkSDTKH(String sdt) {
        try {
            Connection conQLKH = KetNoi.getConnection();
            CallableStatement cstmt = conQLKH.prepareCall("SELECT MAKH from KHACHHANG WHERE XOA = 0 AND SODT = '" + sdt + "'");
            ResultSet rs = cstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("MAKH");
            }
        } catch (SQLException ex) {
            System.err.println("Cannot connect database, " + ex);
        }
        return null;
    }

    public String getSinhNhat(String makh) {
        try {
            Connection conQLSP = KetNoi.getConnection();
            CallableStatement cstmt = conQLSP.prepareCall("SELECT NGSINH from KHACHHANG WHERE XOA = '0' AND MAKH = '" + makh + "'");
            ResultSet rs = cstmt.executeQuery();
            if (rs.next()) {
                return new SimpleDateFormat("dd/MM/yyyy").format(rs.getDate(1));
            }
        } catch (SQLException ex) {
            System.err.println("Cannot connect database, " + ex);
        }
        return null;
    }

    public String getLoaiKH(String makh) {
        try {
            Connection conQLSP = KetNoi.getConnection();
            CallableStatement cstmt = conQLSP.prepareCall("SELECT LOAIKH from KHACHHANG WHERE XOA = '0' AND MAKH = '" + makh + "'");
            ResultSet rs = cstmt.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException ex) {
            System.err.println("Cannot connect database, " + ex);
        }
        return null;
    }

    public DefaultTableModel timKH(String ma, String dieuKienTim) {
        DefaultTableModel table = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        try {
            Connection conQLKH = KetNoi.getConnection();
            CallableStatement cstmt = conQLKH.prepareCall("SELECT * from KHACHHANG WHERE XOA = 0 AND " + dieuKienTim + " = '" + ma + "'");
            ResultSet rs = cstmt.executeQuery();

            String[] colsName = {"STT", "Mã KH", "Họ và tên", "Địa chỉ", "Số ĐT", "Sinh nhật", "Ngày ĐK", "Doanh số", "Loại KH"};
            table.setColumnIdentifiers(colsName);
            indexRow = 1;
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            try {
                while (rs.next()) { // nếu còn đọc tiếp được một dòng dữ liệu
                    String rows[] = new String[9];
                    rows[0] = Integer.toString(indexRow);
                    rows[1] = rs.getString(1); // lấy dữ liệu tại cột số 1 (ứng với mã ) 
                    rows[2] = rs.getString(2); // lấy dữ liệu tai cột số 2 ứng với tên
                    rows[3] = rs.getString(3);
                    rows[4] = rs.getString(4);
                    rows[5] = df.format(rs.getDate(5));
                    rows[6] = df.format(rs.getDate(6));
                    rows[7] = String.format("%,d", rs.getLong(7)) + " VNĐ";
                    rows[8] = rs.getString(8);

                    table.addRow(rows); // đưa dòng dữ liệu vào tableModel để hiện thị lên jtable
                    indexRow++;
                }
            } catch (SQLException e) {
                System.out.println("Lỗi lấy dữ liệu resultSet trong QLKH");
            }

        } catch (SQLException ex) {
            System.err.println("Lỗi kết nỗi hoặc querry trong QLKH " + ex);
        }
        return table;
    }
}
