package qltc.DataAccessLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import qltc.Entity.buySP;

public class hoadonDAL {

    public static hoadonDAL hd() {
        return new hoadonDAL();
    }

    public void insertEmpty() throws SQLException {
        String querry = "insert into hoadon (makh) values (null) ";
        Connection con = connection.connectionDB();
        PreparedStatement ps = con.prepareStatement(querry);
        ps.executeUpdate();
        connection.CloseConnect(con);
    }

    public void updateKhMua(int makh, int total, String date) {
        String sql = "UPDATE hoadon SET makh = ?, tongtien = ?, ngaytt = ? WHERE mahd = (SELECT MAX(mahd) FROM hoadon)";
        Connection con = connection.connectionDB();
        try {
            PreparedStatement prepare = con.prepareStatement(sql);
            prepare.setInt(1, makh);
            prepare.setDouble(2, total);
            prepare.setString(3, date);
            prepare.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.CloseConnect(con);
        }
    }

    public void DoanhThuTheoNgay(BarChart<String, Integer> chart, XYChart.Series<String, Integer> data)
            throws SQLException {
        String sql = " SELECT ngaytt, SUM(tongtien) AS doanhthu FROM hoadon GROUP BY ngaytt";
        try (Connection con = connection.connectionDB();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                String ngay = rs.getString(1);
                Integer doanhthu = rs.getInt(2);
                if (ngay != null && doanhthu != null) {
                    data.getData().add(new XYChart.Data<>(ngay, doanhthu));
                }
            }
            connection.CloseConnect(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        chart.getData().add(data);

    }

    public List<buySP> GetInformationHoaDon() throws SQLException {
        List<buySP> lstData = new ArrayList<buySP>();
        String sql = "  SELECT sp.TENSP , msp.SOLUONG , sp.GIABAN , msp.MAHD FROM sanpham sp INNER JOIN mua_sanpham msp ON sp.MASP = msp.MASP where msp.MAHD = (SELECT MAX(mahd) FROM mua_sanpham)";
        Connection con = connection.connectionDB();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            int mahd = rs.getInt("MAHD");
            String tensp = rs.getNString("TENSP");
            int soluong = rs.getInt("SOLUONG");
            int dongia = rs.getInt("GIABAN");
            int total = soluong * dongia;
            buySP sp = new buySP(mahd, tensp, soluong, dongia, total);
            lstData.add(sp);
        }
        connection.CloseConnect(con);
        return lstData;

    }
}
