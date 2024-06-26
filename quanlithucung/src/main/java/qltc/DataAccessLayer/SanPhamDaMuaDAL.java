package qltc.DataAccessLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import qltc.Entity.SPDaMua;

public class SanPhamDaMuaDAL {
    public static SanPhamDaMuaDAL sanphamdamua() {
        return new SanPhamDaMuaDAL();
    }
    // public void InsertSanPhamDaMua(int masp , int makh , String date, int gia)
    // throws SQLException {
    // String querry = "insert into mua_sanpham (MASP,MAKH,DONGIA,NGAY) values
    // (?,?,?,?) " ;
    // Connection con = connection.connectionDB();
    // PreparedStatement ps = con.prepareStatement(querry);
    // ps.setInt(1, masp);
    // ps.setInt(2, makh);
    // ps.setInt(3, gia);
    // ps.setString(4, date);
    // ps.executeUpdate();
    // connection.CloseConnect(con);
    // }

    public void InsertSanPhamDaMua(int masp, int makh, String date, int gia, int sl) throws SQLException {
        String querry = "insert into mua_sanpham (MASP,MAKH,DONGIA,SOLUONG,MAHD) values (?,?,?,?,(select max(mahd) from hoadon)) ";
        Connection con = connection.connectionDB();
        PreparedStatement ps = con.prepareStatement(querry);
        ps.setInt(1, masp);
        ps.setInt(2, makh);
        ps.setInt(3, gia);
        ps.setInt(4, sl);
        ps.executeUpdate();
        connection.CloseConnect(con);
    }

    // public ObservableList<SPDaMua> GetSanPhamDaMua() throws SQLException{
    // ObservableList<SPDaMua> lstData = FXCollections.observableArrayList();
    // String querry ="SELECT kh.MAKH,TEN,TENSP,DONGIA,NGAY FROM khachhang kh INNER
    // JOIN mua_sanpham msp ON kh.MAKH = msp.MAKH Inner join sanpham sp on sp.MASP =
    // msp.MASP";
    // Connection con = connection.connectionDB();
    // Statement st = con.createStatement();
    // ResultSet rs = st.executeQuery(querry);
    // while (rs.next()) {
    // String id = rs.getString("MAKH");
    // if (id.length()==1){id = "0" + id;}
    // String id_input = "KH"+id;
    // String ten = rs.getNString("TEN");
    // String tensp = rs.getNString("TENSP");
    // String chiphi = rs.getString("CHIPHI");
    // String ngay = rs.getString("NGAY");
    // SPDaMua sp = new SPDaMua(id_input, ten, tensp, chiphi, ngay);
    // lstData.add(sp);
    // }
    // connection.CloseConnect(con);
    // return lstData;
    // }

    public ObservableList<SPDaMua> GetSanPhamDaMua() throws SQLException {
        ObservableList<SPDaMua> lstData = FXCollections.observableArrayList();
        String querry = "SELECT kh.MAKH, TEN, TENSP, msp.DONGIA FROM khachhang kh INNER JOIN mua_sanpham msp ON kh.MAKH = msp.MAKH INNER JOIN sanpham sp ON sp.MASP = msp.MASP";
        Connection con = connection.connectionDB();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(querry);
        while (rs.next()) {
            String id = rs.getString("MAKH");
            if (id.length() == 1) {
                id = "0" + id;
            }
            String id_input = "KH" + id;
            String ten = rs.getNString("TEN");
            String tensp = rs.getNString("TENSP");
            String dongia = rs.getString("dongia");
            SPDaMua sp = new SPDaMua(id_input, ten, tensp, dongia);
            lstData.add(sp);
        }
        connection.CloseConnect(con);
        return lstData;
    }

    // public void DoanhThuTheoNgay(BarChart<String, Integer> chart,
    // XYChart.Series<String, Integer> data) throws SQLException {
    // String sql = """
    // SELECT CONVERT(DATE, NGAY, 103) AS NGAY, SUM(TotalCost) AS total_quantity
    // FROM (
    // SELECT
    // NGAY,
    // DONGIA AS TotalCost
    // FROM mua_sanpham
    // UNION ALL
    // SELECT NGAY_NHAN, GiATIEN
    // FROM thucung
    // ) AS combined_data
    // GROUP BY CONVERT(DATE, NGAY, 103)
    // ORDER BY CONVERT(DATE, NGAY, 103)
    // """;
    // try (Connection con = connection.connectionDB();
    // Statement st = con.createStatement();
    // ResultSet rs = st.executeQuery(sql)) {
    // while (rs.next()) {
    // data.getData().add(new XYChart.Data<>(rs.getString(1), rs.getInt(2)));
    // }
    // } catch (SQLException e) {
    // e.printStackTrace();
    // }
    // chart.getData().add(data);
    // }
    // public int countSp_perId(int id){

    // return 0;
    // }
    public void DoanhThuTheoNgay(BarChart<String, Integer> chart, XYChart.Series<String, Integer> data)
            throws SQLException {
        String sql = """
                SELECT  SUM(TotalCost) AS total_quantity
                FROM (
                    SELECT

                    DONGIA AS TotalCost
                    FROM mua_sanpham
                    UNION ALL
                    SELECT NGAY_NHAN, GiATIEN AS TotalCost
                    FROM thucung
                ) AS combined_data
                        """;
        try (Connection con = connection.connectionDB();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                data.getData().add(new XYChart.Data<>(rs.getString(1), rs.getInt(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        chart.getData().add(data);
    }

    public int countSp_perId(int id) {

        return 0;
    }

    public ObservableList<SPDaMua> giohang(int idkh) throws SQLException {
        ObservableList<SPDaMua> lstData = FXCollections.observableArrayList();
        String sql = "SELECT mua_sanpham.madat, sanpham.tensp, mua_sanpham.soluong, mua_sanpham.dongia " +
                "FROM mua_sanpham " +
                "JOIN sanpham ON sanpham.masp = mua_sanpham.masp " +
                "WHERE mua_sanpham.mahd IS NULL AND mua_sanpham.makh = " + idkh;
        Connection con = connection.connectionDB();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            int madat = rs.getInt("madat");
            String ten = rs.getNString("TENSP");
            int soluong = rs.getInt("SOLUONG");
            String dongia = rs.getString("DONGIA");
            int dongiaa = Integer.parseInt(dongia);
            int chiphi = dongiaa * soluong;
            String chiphii = Integer.toString(chiphi);
            SPDaMua sp = new SPDaMua(madat, ten, soluong, dongia, chiphii);
            lstData.add(sp);
        }
        connection.CloseConnect(con);
        return lstData;
    }

    public void xoaGioHang(int md) throws SQLException {
        String querry = "delete from mua_sanpham where MADAT = ?";
        Connection con = connection.connectionDB();
        PreparedStatement ps = con.prepareStatement(querry);
        ps.setInt(1, md);
        ps.executeUpdate();
        connection.CloseConnect(con);
    }

    public int sumGioHang(int makh) throws SQLException {
        String sql = "SELECT SUM(dongia * soluong) AS total FROM mua_sanpham WHERE makh = ? AND mahd IS NULL";
        Connection con = connection.connectionDB();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, makh);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt("total");
        }
        connection.CloseConnect(con);
        return 0;
    }

    public void updateMahd(int makh) {
        String sql = "UPDATE mua_sanpham SET mahd = (select max(mahd) from hoadon) WHERE makh=? and mahd is null";
        Connection con = connection.connectionDB();
        try {
            PreparedStatement prepare = con.prepareStatement(sql);
            prepare.setInt(1, makh);
            prepare.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.CloseConnect(con);
        }
    }

    public int TkKh(int id) throws SQLException {
        String sql = "SELECT SUM(SOLUONG) as total FROM mua_sanpham WHERE MAKH = ?";
        Connection con = connection.connectionDB();
        PreparedStatement prepare = con.prepareStatement(sql);
        prepare.setInt(1, id);
        ResultSet rs = prepare.executeQuery();
        if (rs.next()) {
            return rs.getInt("total");
        }
        connection.CloseConnect(con);
        return 4;

    }

    public ObservableList<SPDaMua> getKhDamua(int id) throws SQLException {
        ObservableList<SPDaMua> lstData = FXCollections.observableArrayList();
        String querry = "SELECT hd.NGAYTT ,sp.TENSP, msp.DONGIA, msp.SOLUONG FROM MUA_SANPHAM msp INNER JOIN sanpham sp ON msp.MASP = sp.MASP INNER JOIN hoadon hd ON msp.MAHD = hd.MAHD"
                + " WHERE msp.makh = " + id ;
        Connection con = connection.connectionDB();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(querry);
        while (rs.next()) {
            String ngay = rs.getString("NGAYTT");
            String tensp = rs.getNString("TENSP");
            String dongia = rs.getString("dongia");
            int soluong = rs.getInt("soluong");
            SPDaMua sp = new SPDaMua(ngay ,tensp, dongia, soluong);
            lstData.add(sp);
        }
        connection.CloseConnect(con);
        return lstData;

    }
}
