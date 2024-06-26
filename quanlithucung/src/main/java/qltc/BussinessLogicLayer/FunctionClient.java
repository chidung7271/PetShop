package qltc.BussinessLogicLayer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import qltc.Entity.PetView;
import qltc.Entity.SPDaMua;
import qltc.Entity.customer;
import qltc.Entity.product;

public class FunctionClient {
    public static FunctionClient FC(){
        return new FunctionClient();
    }
    public Boolean LoginUser (String username , String password) throws IOException {
        Client.out_to_server.println("Login" + "//" + username.trim() + "//" + password.trim());
        String response = Client.in_from_server.readLine();
        System.out.println("Client get response: " + "Login");
        return Boolean.parseBoolean(response.trim());
        
    }
    public Boolean UpdateInformationKH(String username, String hoten , String sex , String sdt ) throws IOException {
        Client.out_to_server.println("UpdateKH" + "//" + username.trim() + "//" + hoten.trim() + "//" + sex.trim() + "//" + sdt.trim());
        String response = Client.in_from_server.readLine();
        System.out.println("Client get response: " + "UpdateKH");
        return Boolean.parseBoolean(response.trim());
    }
    public customer GetInformationKH(String username) throws IOException {
        Client.out_to_server.println("GetKH" + "//" + username.trim());
        String response = Client.in_from_server.readLine();
        String data[] = response.split("//");
        customer kh = new customer(data[0], data[1], data[2], data[3], data[4]);
        System.out.println("Client get response: " + "GetKH");
        return kh;
    }
    public int GetIdKH(String username) throws IOException {
        Client.out_to_server.println("GetIdKH" + "//" + username.trim());
        String response = Client.in_from_server.readLine();
        System.out.println("Client get response: " + "GetIdKH");
        return Integer.parseInt(response.trim());
    }
    public ArrayList<product> GetListSp()throws IOException{
        Client.out_to_server.println("GetListSP");
        String response = Client.in_from_server.readLine();
        Gson gson = new Gson();
        ArrayList<product> list = gson.fromJson(response, new TypeToken<ArrayList<product>>(){}.getType());
        System.out.println("Client get response: " + "GetListSP");
        return list;
    }
    public ArrayList<SPDaMua> GioHang(int id)throws IOException{
        Client.out_to_server.println("GioHang" + "//" + id);
        String response = Client.in_from_server.readLine();
        Gson gson = new Gson();
        ArrayList<SPDaMua> list = gson.fromJson(response, new TypeToken<ArrayList<SPDaMua>>(){}.getType());
        System.out.println("Client get response: " + "GioHang");
        return list;
    }
    public Boolean XoaSp(int id_select) throws IOException {
        Client.out_to_server.println("XoaSp" + "//" + id_select);
        String response = Client.in_from_server.readLine();
        System.out.println("Client get response: " + "XoaSp");
        return Boolean.parseBoolean(response.trim());
    }
    public Boolean MuaSp(int id) throws IOException {
        Client.out_to_server.println("MuaSp" + "//" + id);
        String response = Client.in_from_server.readLine();
        System.out.println("Client get response: " + "MuaSp");
        return Boolean.parseBoolean(response.trim());

    }
    public List<Integer> GetChuongRong() throws IOException {
        Client.out_to_server.println("GetChuong");
        String response = Client.in_from_server.readLine();
        Gson gson = new Gson();
        List<Integer> list = gson.fromJson(response, new TypeToken<List<Integer>>(){}.getType());
        System.out.println("Client get response: " + "GetChuong");
        return list;
    
    }
    public void SetChuong() throws IOException {
        Client.out_to_server.println("SetChuong");
        System.out.println("Client get response: " + "SetChuong");
    }
    public int InsertChuong(int chuong) throws IOException {
        Client.out_to_server.println("InsertChuong" + "//" + chuong);
        String response = Client.in_from_server.readLine();
        System.out.println("Client get response: " + "InsertChuong");
        return Integer.parseInt(response.trim());
    }
    public void InsertPet(int id_kh,String loai, String ten, String gioitinh, String note, String nhan, String tra, int chuong, String image , int giatien) throws IOException {
        Client.out_to_server.println("InsertPet" + "//" + id_kh + "//" + loai + "//" + ten + "//" + gioitinh + "//" + note + "//" + nhan + "//" + tra + "//" + chuong + "//" + image + "//" + giatien);
        System.out.println("Client get response: " + "InsertPet");
    }
    public Boolean UpdateHD(int id , int tt , String date) throws IOException {
        Client.out_to_server.println("UpdateHD" + "//" + id + "//" + tt + "//" + date);
        String response = Client.in_from_server.readLine();
        System.out.println("Client get response: " + "UpdateHD");
        return Boolean.parseBoolean(response.trim());
    }

    public int TkKhDamua(int id) throws IOException {
        Client.out_to_server.println("TkKhDamua" + "//" + id);
        String response = Client.in_from_server.readLine();
        System.out.println("Client get response: " + "TkKhDamua");
        return Integer.parseInt(response.trim());
    }
    public ArrayList<SPDaMua> GetListKhDaMua(int id) throws IOException {
        Client.out_to_server.println("GetListKhDaMua" + "//" + id);
        String response = Client.in_from_server.readLine();
        Gson gson = new Gson();
        ArrayList<SPDaMua> list = gson.fromJson(response, new TypeToken<ArrayList<SPDaMua>>(){}.getType());
        System.out.println("Client get response: " + "GetListKhDaMua");
        System.out.println(list);
        return list;
    }
    public ArrayList<PetView> GetListKhDichVu(int id) throws IOException {
        Client.out_to_server.println("ListKHSuDungDichVu"  + "//" + id);
        String response = Client.in_from_server.readLine();
        Gson gson = new Gson();
        ArrayList<PetView> list = gson.fromJson(response, new TypeToken<ArrayList<PetView>>(){}.getType());
        System.out.println("Client get response: " + "ListKHSuDungDichVu");
        return list;
    }
    
    public int SlUserDichVu(int id) throws IOException {
        Client.out_to_server.println("SlUserDichVu" + "//" + id);
        String response = Client.in_from_server.readLine();
        System.out.println("Client get response: " + "SlUserDichVu");
        return Integer.parseInt(response.trim());
    }

    public ArrayList<SPDaMua> DanhSachTatCaDaMua() throws IOException {
        Client.out_to_server.println("TableDaMuaAdmin");
        String response = Client.in_from_server.readLine();
        Gson gson = new Gson();
        ArrayList<SPDaMua> list = gson.fromJson(response, new TypeToken<ArrayList<SPDaMua>>(){}.getType());
        System.out.println("Client get response: " + "TableDaMuaAdmin");
        return list;
    }
    public ArrayList<product> GetListSpAdmin() throws IOException {
        Client.out_to_server.println("GetListSpAdmin");
        String response = Client.in_from_server.readLine();
        Gson gson = new Gson();
        ArrayList<product> list = gson.fromJson(response, new TypeToken<ArrayList<product>>(){}.getType());
        System.out.println("Client get response: " + "GetListSpAdmin");
        return list;
    }

    public Boolean UsernameExist(String username)throws IOException{
        Client.out_to_server.println("CheckUsername" + "//" + username.trim());
        String response = Client.in_from_server.readLine();
        System.out.println("Server response: " + response);
        return Boolean.parseBoolean(response.trim());
    }

    public void SignUpUser(String username,String password,String email)throws IOException{
        Client.out_to_server.println("SignUp" + "//" + username + "//" + password + "//" + email);
        String response = Client.in_from_server.readLine();
        System.out.println("Server response: " + response);
    }

    public Boolean CheckEmailExist(String email)throws IOException{
        Client.out_to_server.println("CheckEmail" + "//" + email.trim());
        String response = Client.in_from_server.readLine();
        System.out.println("Server response: " + response);
        return Boolean.parseBoolean(response.trim());
    }

    public void ChangePass(String email,String pass) throws IOException{
        Client.out_to_server.println("ChangePass" + "//" + email.trim() + "//" + pass.trim());
        String response = Client.in_from_server.readLine();
        System.out.println("Server response: " + response);
    }

    public Boolean LogAdmin(String username,String password) throws IOException{
        Client.out_to_server.println("LoginAdmin" + "//" + username.trim() + "//" + password.trim());
        String response = Client.in_from_server.readLine();
        System.out.println("Server response: " + response);
        return Boolean.parseBoolean(response.trim());
    }
    
    public void SendEmail(String to,String otp) throws IOException{
        Client.out_to_server.println("SendEmail" + "//" + to.trim() + "//" + otp.trim());
        String response = Client.in_from_server.readLine();
        System.out.println("Server response: " + response);
    }
    public Boolean CheckCustomerExist(String name, String sex, String phone) throws IOException{
        Client.out_to_server.println("CheckCustomerExist" + "//" + name.trim() + "//"+ sex.trim() + "//" + phone.trim());
        String response = Client.in_from_server.readLine();
        System.out.println("Server response: " + response);
        return Boolean.parseBoolean(response.trim());
    }

    public void UpdateKh(int id, String name,String sex, String phone) throws IOException{
        Client.out_to_server.println("UpdateKh" + "//" + id + "//" + name.trim() + "//" + sex.trim() + "//" + phone.trim());
        String response = Client.in_from_server.readLine();
        System.out.println("Server response: " + response);
    }
    public void InsertKh(String name, String sex, String phone) throws IOException{
        Client.out_to_server.println("InsertKh" + "//" + name.trim() + "//" + sex.trim() + "//" + phone.trim());
        String response = Client.in_from_server.readLine();
        System.out.println("Server response: " + response);
    }
    public void DeleteKh(int id) throws IOException{
        Client.out_to_server.println("DeleteKh" + "//" + id );
        String response = Client.in_from_server.readLine();
        System.out.println("Server response: " + response);
    }

    //// public ObservableList<customer> TableUser() throws IOException{
    //// ObservableList listData = FXCollections.observableArrayList();
    //// Client.out_to_server.println("TableUser");
    //// String response = Client.in_from_server.readLine();
    //// System.out.println("Server response: " + response);

    //// return listData;
    //// }
    public Boolean CheckProductExist(String loai,String name,int sl,int gia,String org, String mota) throws IOException{
        Client.out_to_server.println("CheckProductExist" + "//" + loai.trim() + "//"+ name.trim() + "//" + sl+ "//" + gia+ "//" + org.trim()+ "//" + mota.trim());
        String response = Client.in_from_server.readLine();
        System.out.println("Server response: " + response);
        return Boolean.parseBoolean(response.trim());
    }
    public void InsertSanpham(String loai,String name,int sl,int gia,String org,String mota,String image) throws IOException{
        Client.out_to_server.println("InsertSanpham" + "//" + loai.trim()+ "//" + name.trim() + "//" + sl + "//" + gia + "//" + org.trim()+ "//" + mota.trim()+ "//" + image.trim());
        String response = Client.in_from_server.readLine();
        System.out.println("Server response: " + response);
    }
    public void UpdateSanPham(int id,String loai,String name,int sl,int gia,String org, String mota,String image) throws IOException{
        Client.out_to_server.println("UpdateSanPham" + "//" + id + "//" + loai.trim()+ "//" + name.trim() + "//" + sl + "//" + gia + "//" + org.trim()+ "//" + mota.trim()+ "//" + image.trim());
        String response = Client.in_from_server.readLine();
        System.out.println("Server response: " + response);
    }
    
}
