package qltc.DataAccessLayer;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import qltc.Entity.PetView;
import qltc.Entity.pet;

public class petDAL {
    public static petDAL pet(){
        return new petDAL();
    }
    public void 
    InsertPet(int MAKH, String loai, String name , String gioitinh , String note , String nhan , String tra , int chuong , String image , int giatien) throws SQLException, FileNotFoundException {
        String querry = "insert into thucung (MAKH,LOAI,TEN,GIOI_TINH,NOTE,NGAY_NHAN,NGAY_TRA,MACHUONG,AVA,GIATIEN,MAHD) values (?,?,?,?,?,?,?,?,?,?,(select max(mahd) from hoadon)) " ;
        Connection con = connection.connectionDB();
        PreparedStatement ps = con.prepareStatement(querry);
        ps.setInt(1, MAKH);
        ps.setNString(2, loai);
        ps.setNString(3, name);
        ps.setNString(4,gioitinh );
        ps.setNString(5, note);
        ps.setString(6, nhan);
        ps.setString(7,tra );
        ps.setInt(8, chuong);
        File imageFile = new File(image);
        FileInputStream fis = new FileInputStream(imageFile);
        ps.setBinaryStream(9, fis, (int) imageFile.length());
        ps.setInt(10, giatien);
        ps.executeUpdate();
        connection.CloseConnect(con);
    }
    public ObservableList<pet> GetPet() throws SQLException{
        ObservableList<pet> lstData = FXCollections.observableArrayList();
        String querry = "select * from thucung " ;
        Connection con = connection.connectionDB();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(querry);
        while (rs.next()) {
            String id_kh = rs.getString("MAKH");
            if (id_kh.length()==1){id_kh = "0" + id_kh;}
            id_kh = "KH"+id_kh;
            String id_tc = rs.getString("MATC");
            if (id_tc.length()==1){id_tc = "0" + id_tc;}
            id_tc = "TC"+id_tc;
            String loai = rs.getNString("LOAI");
            String ten = rs.getNString("TEN");
            String gioitinh = rs.getString("GIOI_TINH");
            String note = rs.getString("NOTE");
            String nhan = rs.getString("NGAY_NHAN");
            String tra = rs.getString("NGAY_TRA");
            String chuong = rs.getString("MACHUONG");
            chuong = "Chuồng " + chuong;
            byte[] imagedata = rs.getBytes("AVA");
            if (imagedata != null){Image image = new Image(new ByteArrayInputStream(imagedata),170,170,true,true);
                ImageView imageView = new ImageView(image);
                pet kh = new pet(id_kh, id_tc, loai, ten, gioitinh, note, nhan, tra, chuong,imageView);
                lstData.add(kh);
            }
            else{
            
            pet kh = new pet(id_kh, id_tc, loai, ten, gioitinh, note, nhan, tra, chuong,null);
            lstData.add(kh);}
        }
        connection.CloseConnect(con);
        return lstData;
    }
    public int InsertChuong(int chuong) throws SQLException {
        int data = 0;
        String querry = "select * from cacchuong where MACHUONG =" + chuong;
        Connection con = connection.connectionDB();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(querry);
        while (rs.next()) {
            boolean bitValue = rs.getBoolean("TT");
            if (!bitValue){
                String querry2 = "UPDATE cacchuong SET TT = ? WHERE MACHUONG =" + chuong;
                Connection con2 = connection.connectionDB();
                PreparedStatement ps = con2.prepareStatement(querry2);
                boolean newValue = true;
                ps.setBoolean(1, newValue);
                ps.executeUpdate();
                connection.CloseConnect(con2);
                data = 1;
            }
            else {
                System.out.println("Đã đầy");
            }
        }
        connection.CloseConnect(con);
        return data;
    }

    public int SlUserDichVu(int id) throws SQLException {
        String sql = "SELECT COUNT(*) as total FROM thucung WHERE MAKH = ?";
        Connection con = connection.connectionDB();
        PreparedStatement prepare = con.prepareStatement(sql);
        prepare.setInt(1, id);
        ResultSet rs = prepare.executeQuery();
        if (rs.next()) {
            return rs.getInt("total");
        }
        connection.CloseConnect(con);
        return 0;

    }

    public ObservableList<PetView> ListKHSuDungDichVu(int id) throws SQLException {
        ObservableList<PetView> lstData = FXCollections.observableArrayList();
        String sql = "SELECT LOAI,TEN,GIOI_TINH,NGAY_NHAN,NGAY_TRA,GIATIEN FROM thucung WHERE MAKH =" + id;
        Connection con = connection.connectionDB();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) { // Changed from if to while
            String loai = rs.getNString("LOAI");
            String ten = rs.getNString("TEN");
            String gioitinh = rs.getString("GIOI_TINH");
            String nhan = rs.getString("NGAY_NHAN");
            String tra = rs.getString("NGAY_TRA");
            int giatien = rs.getInt("GIATIEN");
            PetView data = new PetView(loai, ten, gioitinh, nhan, tra, giatien);
    
            lstData.add(data);
            // Assuming pet class has a toString method for printing, otherwise this line might cause an error
        }
        connection.CloseConnect(con);
        return lstData;
    }

    public static void main(String[] args) throws SQLException {
        petDAL.pet().ListKHSuDungDichVu(22);
    }




    public void ChuongRong(String chuong) throws SQLException {

            String querry2 = "UPDATE cacchuong SET TT = ? WHERE MACHUONG IN (" + chuong + ")";
            Connection con2 = connection.connectionDB();
            PreparedStatement ps = con2.prepareStatement(querry2);
            boolean newValue = false;
            ps.setBoolean(1, newValue);
            ps.executeUpdate();
            connection.CloseConnect(con2);
    }


    public List<Integer> GetTT() throws SQLException{
        List<Integer> lstData= new ArrayList<Integer>();
        int i  = 0;
        String querry = "select TT from cacchuong " ;
        Connection con = connection.connectionDB();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(querry);
        while (rs.next()) {
            i += 1;
            boolean bitValue = rs.getBoolean("TT");
            if(bitValue) {lstData.add(i);}
        }
        connection.CloseConnect(con);
        return lstData;


    }

}