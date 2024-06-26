package qltc.DataAccessLayer;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import qltc.Entity.product;
public class sanphamDAL{
    



    public static sanphamDAL sanpham(){
        return new sanphamDAL();
    }
    
    public void InsertSanPham(String loaihang , String name , int sl, int gia, String xuatxu, String mota,String image ) throws SQLException, FileNotFoundException {
        String querry = "insert into SanPham (LOAISP,TENSP,SL,GIABAN,XUATXU,MOTA,image,trangthai) values (?,?,?,?,?,?,?,?) " ;
        Connection con = connection.connectionDB();
        PreparedStatement ps = con.prepareStatement(querry);
        ps.setNString(1,loaihang );
        ps.setNString(2, name);
        ps.setInt(3, sl);
        ps.setInt(4, gia);
        ps.setNString(5, xuatxu);
        ps.setNString(6, mota);
        File imageFile = new File(image);
        FileInputStream fis = new FileInputStream(imageFile);
        ps.setBinaryStream(7, fis, (int) imageFile.length());
        ps.setNString(8,"ton tai");
        ps.executeUpdate();
        connection.CloseConnect(con);
    }


    public ObservableList<product> GetSanPham() throws SQLException{
        ObservableList<product> lstData = FXCollections.observableArrayList();
        String querry = "select * from sanpham " ;
        Connection con = connection.connectionDB();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(querry);
        while (rs.next()) {
            String tt = rs.getString("trangthai");
            if(tt.equals("khong ton tai")){
                continue;
            }
                            
            String id = rs.getString("MASP");
            if (id.length()==1){id = "0" + id;}
            String id_input = "SP"+id;
            String loaihang = rs.getNString("LOAISP");
            String name = rs.getNString("TENSP");
            String sl = rs.getString("SL");
            String gia = rs.getString("GIABAN");
            String xuatxu = rs.getNString("XUATXU");
            String mota = rs.getNString("MOTA");
String image = rs.getString("image");
byte[] imageData = null;
if (image != null) {
    InputStream inputStream = rs.getBinaryStream("image");
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    byte[] buffer = new byte[4096];
    int bytesRead;
    try {
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
        imageData = outputStream.toByteArray();
    } catch (IOException e) {
        e.printStackTrace();
    }
}            product pd = new product(id_input, loaihang, name, sl, gia, xuatxu, mota,imageData);
            lstData.add(pd);
        }
        connection.CloseConnect(con);
        return lstData;
    }

    public int CheckProductExist(String loaihang , String name , int sl, int gia, String xuatxu, String mota ) throws SQLException{
        String querry = "SELECT * FROM SanPham WHERE LOAISP = ? AND TENSP = ? AND SL = ? AND GIABAN = ? AND XUATXU = ? AND MOTA = ? ";
        Connection con = connection.connectionDB();
        PreparedStatement ps = con.prepareStatement(querry);
        ps.setNString(1,loaihang );
        ps.setNString(2, name);
        ps.setInt(3, sl);
        ps.setInt(4,gia );
        ps.setNString(5, xuatxu);
        ps.setNString(6, mota);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt("MASP");
        }
        connection.CloseConnect(con);
        return 0;
    }

    public void UpdateSanPham(int id , String loaihang , String name , int sl, int gia, String xuatxu, String mota,String image) throws SQLException, FileNotFoundException {
        String querry = "update SanPham set LOAISP = ? , TENSP = ? , SL = ? , GIABAN = ? , XUATXU = ? , MOTA = ?, image=? where MASP = ?"  ;
        Connection con = connection.connectionDB();
        PreparedStatement ps = con.prepareStatement(querry);
        ps.setNString(1,loaihang );
        ps.setNString(2, name);
        ps.setInt(3, sl);
        ps.setInt(4,gia );
        ps.setNString(5, xuatxu);
        ps.setNString(6, mota);
        File imageFile = new File(image);
        FileInputStream fis = new FileInputStream(imageFile);
        ps.setBinaryStream(7, fis, (int) imageFile.length());
        ps.setInt(8, id);
        ps.executeUpdate();
        connection.CloseConnect(con);
    }

    public void DeleteSanPham(int id) throws SQLException {
        String querry = "update sanpham set trangthai = 'khong ton tai' where MASP = ?"  ;
        Connection con = connection.connectionDB();
        PreparedStatement ps = con.prepareStatement(querry);
        ps.setInt(1, id);
        ps.executeUpdate();
        connection.CloseConnect(con);
    }

    // public ObservableList<product> GetDataUsingKindSP(String kind_sp) throws SQLException{
    //     ObservableList<product> data = FXCollections.observableArrayList();
    //     String querry = "SELECT TENSP,SL,GIABAN FROM SanPham WHERE LOAISP = ?";
    //     Connection con = connection.connectionDB();
    //     PreparedStatement ps = con.prepareStatement(querry);
    //     ps.setNString(1,kind_sp );
    //     ResultSet rs = ps.executeQuery();
    //     while (rs.next()) {
    //         String name = rs.getNString("TENSP");
    //         String sl = rs.getString("SL");
    //         String gia = rs.getString("GIABAN");
    //         product pd = new product(null, null, name, sl, gia, null, null);
    //         data.add(pd);
    //     }
    //     connection.CloseConnect(con);
    //     return data;
    // }


    

}

