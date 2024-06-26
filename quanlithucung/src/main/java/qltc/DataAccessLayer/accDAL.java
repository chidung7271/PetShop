package qltc.DataAccessLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;
public class accDAL {    
    public static accDAL acc(){
        return new accDAL();
    }
    public void insertAcc(String username , String pass , String email) throws SQLException {
        String querry = "insert into acc (username,password,Email) values (?,?,?) " ;
        Connection con = connection.connectionDB();
        PreparedStatement ps = con.prepareStatement(querry);
        ps.setNString(1,username );
        ps.setNString(2, pass);
        ps.setNString(3, email);
        ps.executeUpdate();
        connection.CloseConnect(con);
    }   
    public Boolean userExist(String username)throws SQLException{
        String CheckUsername ="SELECT username FROM acc where username= '"+username+"'";
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
    String CheckPass ="SELECT password FROM acc where username= '"+username+"'";
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
    String CheckPass ="SELECT email FROM acc where email= '"+email+"'";
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
public void changepass(String email,String newpass ) throws SQLException{
    String query ="UPDATE acc SET password =(?) WHERE email= '"+email+"'";
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
}
