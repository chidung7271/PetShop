package qltc.DataAccessLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.mindrot.jbcrypt.BCrypt;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import qltc.Entity.customer;

public class khachhangDAL {


    public static khachhangDAL khachhang(){
        return new khachhangDAL();
    }


    public void InsertKhachHang(String name , String gioitinh , String sdt) throws SQLException {
        String querry = "insert into khachhang (TEN,GIOI_TINH,SDT,trangthai) values (?,?,?,?) " ;
        Connection con = connection.connectionDB();
        PreparedStatement ps = con.prepareStatement(querry);
        ps.setNString(1,name );
        ps.setNString(2, gioitinh);
        ps.setNString(3, sdt);
        ps.setNString(4, "ton tai");
        ps.executeUpdate();
        connection.CloseConnect(con);
    }
    public ObservableList<customer> GetKhachHang() throws SQLException{
        ObservableList<customer> lstData = FXCollections.observableArrayList();
        String querry = "select * from khachhang " ;
        Connection con = connection.connectionDB();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(querry);
        while (rs.next()) {
            String tt = rs.getString("trangthai");
            if(tt.equals("khong ton tai")){
                continue;
            }
            String id = rs.getString("MAKH");
            if (id.length()==1){id = "0" + id;}
            String id_input = "KH"+id;
            String name = rs.getNString("TEN");
            String gioitinh = rs.getNString("GIOI_TINH");
            String sdt = rs.getString("SDT");
            customer kh = new customer(id_input, name, gioitinh, sdt);
            lstData.add(kh);
        }
        connection.CloseConnect(con);
        return lstData;
    }
    public int CheckCustomerExist(String name , String gioitinh , String sdt) throws SQLException{
        String querry = "SELECT * FROM khachhang WHERE TEN = ? AND GIOI_TINH = ? AND SDT = ?";
        Connection con = connection.connectionDB();
        PreparedStatement ps = con.prepareStatement(querry);
        ps.setNString(1,name );
        ps.setNString(2, gioitinh);
        ps.setNString(3, sdt);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt("MAKH");
        }
        connection.CloseConnect(con);
        return 0;
    }

    public void UpdateKhachHang(int id , String name , String gioitinh , String sdt) throws SQLException {
        String querry = "update khachhang set TEN = ? , GIOI_TINH = ? , SDT = ? where MAKH = ?"  ;
        Connection con = connection.connectionDB();
        PreparedStatement ps = con.prepareStatement(querry);
        ps.setNString(1,name );
        ps.setNString(2, gioitinh);
        ps.setNString(3, sdt);
        ps.setInt(4, id);
        ps.executeUpdate();
        connection.CloseConnect(con);
    }

    public void DeleteKhachHang(int id) throws SQLException {
        String querry = "update khachhang set trangthai ='khong ton tai' where MAKH = ?"  ;
        Connection con = connection.connectionDB();
        PreparedStatement ps = con.prepareStatement(querry);
        ps.setInt(1, id);
        ps.executeUpdate();
        connection.CloseConnect(con);
    }

    public void themkh(String username, String pass,String email) throws SQLException{
        String querry = "insert into khachhang (username,password,Email,trangthai) values (?,?,?,'ton tai') " ;
        Connection con = connection.connectionDB();
        PreparedStatement ps = con.prepareStatement(querry);
        ps.setNString(1,username);
        ps.setNString(2, pass);
        ps.setNString(3, email);
        ps.executeUpdate();
        connection.CloseConnect(con);
    }
        public Boolean userExist(String username)throws SQLException{
        String CheckUsername ="SELECT username FROM khachhang where username= '"+username+"'";
        Connection con = connection.connectionDB();
        PreparedStatement ps = con.prepareStatement(CheckUsername);
        ResultSet rs = ps.executeQuery();
        if(rs.next()) {
            connection.CloseConnect(con);
            return true;
    }else {
        connection.CloseConnect(con);
        return false;
    }
}
    public Boolean accExist(String username,String pass)throws SQLException{
        String CheckPass ="SELECT password FROM khachhang where username= '"+username+"'";
        Connection con = connection.connectionDB();
        PreparedStatement ps = con.prepareStatement(CheckPass);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            String password = rs.getString("password");
            System.out.println(password+"@@@");
            Boolean valuate = BCrypt.checkpw(pass,password);
            connection.CloseConnect(con);
            if(valuate){
                return true;
            }else {
                System.out.println(BCrypt.checkpw(pass,password));
                System.out.println(pass);
                return false;
            }
        }return false;   
    }
    public Boolean emailExist(String email) throws SQLException{
        String CheckPass ="SELECT email FROM khachhang where email= '"+email+"'";
        Connection con = connection.connectionDB();
        PreparedStatement ps = con.prepareStatement(CheckPass);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            connection.CloseConnect(con);
            return true;
        }
        else{
            connection.CloseConnect(con);
            return false;  
        }
    }

    public void changepass(String email, String newpass ) throws SQLException{
        String query ="UPDATE khachhang SET password =(?) WHERE email= '"+email+"'";
        Connection con = connection.connectionDB();
        PreparedStatement ps = con.prepareStatement(query);
        ps.setNString(1,newpass);
        ps.executeUpdate();
        connection.CloseConnect(con);
    }
    public Boolean accAdminExist(String username,String pass) throws SQLException{
        String CheckPass ="SELECT password FROM accadmin where username= '"+username+"'";
        Connection con = connection.connectionDB();
        PreparedStatement ps = con.prepareStatement(CheckPass);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            String password = rs.getString("password");
            Boolean valuate = BCrypt.checkpw(pass,password);
            connection.CloseConnect(con);
            if(valuate){
                return true;
            }else {

                return false;
            }
        }return false;   
    }
    public customer selectKH(String username) throws SQLException{
        String sql="select*from khachhang where username='"+username+"'";
        customer kh=null;
        Connection con = connection.connectionDB();
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            String ten = rs.getNString("TEN");
            String sdt = rs.getString("SDT");
            String email = rs.getString("EMAIL");
            String gt = rs.getString("GIOI_TINH");
            kh = new customer(ten, sdt, gt, username, email);
            return kh;
        }
        
        return kh;
    }
    public void UpdateKhachHang(String username, String name, String gioitinh, String sdt) throws SQLException {
        String query = "UPDATE khachhang SET TEN = ?, GIOI_TINH = ?, SDT = ? WHERE username = ?";
        Connection con = connection.connectionDB();
        PreparedStatement ps = con.prepareStatement(query);
        ps.setNString(1, name);
        ps.setNString(2, gioitinh);
        ps.setNString(3, sdt);
        ps.setString(4, username);
        ps.executeUpdate();
        connection.CloseConnect(con);
    }
    public int getId(String username) throws SQLException{
        String sql="select makh from khachhang where username='"+username+"'";
        Connection con = connection.connectionDB();
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int id = rs.getInt("makh");
            return id;
        }
        return 0;
    }
    
}
