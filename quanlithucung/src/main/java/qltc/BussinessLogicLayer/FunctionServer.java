package qltc.BussinessLogicLayer;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import javafx.collections.ObservableList;
import qltc.DataAccessLayer.SanPhamDaMuaDAL;
import qltc.DataAccessLayer.accDAL;
import qltc.DataAccessLayer.hoadonDAL;
import qltc.DataAccessLayer.khachhangDAL;
import qltc.DataAccessLayer.petDAL;
import qltc.DataAccessLayer.sanphamDAL;
import qltc.Entity.PetView;
import qltc.Entity.SPDaMua;
import qltc.Entity.buySP;
import qltc.Entity.customer;
import qltc.Entity.pet;
import qltc.Entity.product;

public class FunctionServer {
    public static FunctionServer FS() {
        return new FunctionServer();
    }

    public Boolean RequestCheckExist(String username, String password) throws SQLException {
        if (khachhangDAL.khachhang().accExist(username, password)) {
            return true;
        }
        return false;
    }

    public Boolean UpdateInformationKH(String username, String hoten, String sex, String sdt) {
        try {
            khachhangDAL.khachhang().UpdateKhachHang(username, hoten, sex, sdt);
            return true;
        } catch (SQLException e) {
            return false;
        }

    }

    public customer GetInformationKH(String username) throws SQLException {
        customer kh = khachhangDAL.khachhang().selectKH(username);
        return kh;
    }

    public int GetIdKH(String username) throws SQLException {
        int id = khachhangDAL.khachhang().getId(username);
        return id;
    }

    public ObservableList<product> GetListSp() throws SQLException {
        ObservableList<product> list = sanphamDAL.sanpham().GetSanPham();
        return list;
    }

    public ObservableList<SPDaMua> GioHang(int id) throws SQLException {
        ObservableList<SPDaMua> list = SanPhamDaMuaDAL.sanphamdamua().giohang(id);
        return list;
    }

    public Boolean XoaSp(int id_select) {
        try {
            SanPhamDaMuaDAL.sanphamdamua().xoaGioHang(id_select);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean MuaSp(int id) throws Exception {
        try {
            hoadonDAL.hd().insertEmpty();
            int tt = SanPhamDaMuaDAL.sanphamdamua().sumGioHang(id);
            SanPhamDaMuaDAL.sanphamdamua().updateMahd(id);
            Date date = new Date();
            SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy");
            hoadonDAL.hd().updateKhMua(id, tt, ft.format(date));
            List<buySP> data = hoadonDAL.hd().GetInformationHoaDon();
            Bill bill = new Bill();
            bill.createBill(data);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    
    public List<Integer> GetChuongRong() throws SQLException{
        List<Integer> lstData = petDAL.pet().GetTT();
        return lstData;
    }

    public void SetUpChuongRong() throws SQLException {
        ObservableList<pet> PetList;
        PetList = petDAL.pet().GetPet();
        List<Integer> numbersList = new ArrayList<>();
        List<Integer> DeleteNum = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            numbersList.add(i);
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        for (pet pet : PetList) {
            LocalDate date = LocalDate.parse(pet.getNgaytra(), formatter);
            if (date.isAfter(LocalDate.now())) {
                DeleteNum.add(Integer.parseInt(pet.getId_phong().substring(7)));
            }
        }
        numbersList.removeAll(DeleteNum);
        String numbersString = numbersList.stream().map(Object::toString).collect(Collectors.joining(", "));
        petDAL.pet().ChuongRong(numbersString);
    }
    public int InsertChuong(int chuong) throws SQLException {
        int data = petDAL.pet().InsertChuong(chuong);
        return data;
    }
    public void InsertPet(int id_kh,String loai, String ten, String gioitinh, String note, String nhan, String tra, int chuong, String image , int giatien) throws SQLException, FileNotFoundException {
        petDAL.pet().InsertPet(id_kh,loai, ten, gioitinh, note, nhan, tra, chuong, image, giatien);
    }
    public Boolean UpdateHD(int id , int total , String date) {
        try {
            hoadonDAL.hd().insertEmpty();
            hoadonDAL.hd().updateKhMua(id, total, date);
            return true;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
        

    }
    public int TkKhDamua(int id) throws SQLException {
        int data = SanPhamDaMuaDAL.sanphamdamua().TkKh(id);
        return data;
    }
    public ObservableList<SPDaMua> GetListKhDaMua(int id) throws SQLException {
        ObservableList<SPDaMua> list = SanPhamDaMuaDAL.sanphamdamua().getKhDamua(id);
        return list;
    }
    public int SlUserDichVu(int id) throws SQLException {
        int data = petDAL.pet().SlUserDichVu(id);
        return data;
    }
    public ObservableList<PetView> ListKHSuDungDichVu(int id) throws SQLException {
        ObservableList<PetView> list = petDAL.pet().ListKHSuDungDichVu(id);
        return list;
    }
    public ObservableList<SPDaMua> DanhSachTatCaDaMua() throws SQLException {
        ObservableList<SPDaMua> list = SanPhamDaMuaDAL.sanphamdamua().GetSanPhamDaMua();
        return list;
    }
    public Boolean InsertSP(int id_sp, int id_kh, String date, int gia , int sl) {
        try {
            SanPhamDaMuaDAL.sanphamdamua().InsertSanPhamDaMua(id_sp, id_kh, date, gia, sl);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        
    }
    public ObservableList<product> GetListSpAdmin() throws SQLException {
        ObservableList<product> list = sanphamDAL.sanpham().GetSanPham();
        return list;
    }
    public Boolean UsernameExist(String username)throws SQLException{
        if (khachhangDAL.khachhang().userExist(username)){
            return true;
        }
        return false;
    }
    public void SignUpUser(String username,String password,String email)throws SQLException{
        khachhangDAL.khachhang().themkh(username,password,email);
    }





    public Boolean CheckEmailExist(String email) throws SQLException{
    if (khachhangDAL.khachhang().emailExist(email)){
        return true;
    }
    return false;

    }

    public void ChangePass(String email,String pass) throws SQLException{
        khachhangDAL.khachhang().changepass(email, pass);

    }

    public Boolean LogAdmin(String username,String password) throws SQLException{
        if (accDAL.acc().accAdminExist(username,password)){
            return true;
        }
        return false;
    }


        public void sendEmail(String to,String otp)throws UnsupportedEncodingException{
        final String from="lamn37140@gmail.com";
		final String password="nijdtedywgakfcps";
		// final String from="vemailchatatc@gmail.com";
		// final String password="iencsgrjdsokjtiq";
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port","587");
		props.put("mail.smtp.auth","true");
		props.put("mail.smtp.starttls.enable","true");
		Authenticator auth = new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication(){
				return new PasswordAuthentication(from, password);
			}
		};
		//tạo tin nhắn
		Session session= Session.getInstance(props,auth);
		MimeMessage msg=new MimeMessage(session);
		try {
			Address senderAddress = new InternetAddress(from);
			msg.setFrom(senderAddress);
			msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
			msg.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to,false));
			msg.setSubject("YOUR OTP!");
			//quy định ngày gửi
			msg.setSentDate(new Date());
			//nội dung
			msg.setText(otp,"UTF-8");
			//gửi
			Transport.send(msg);
            // noti_email.setText("đã gửi thành công");
            // noti_email1.setText("đã gửi thành công");
		} 
		catch (MessagingException e) {
			// noti_email.setText("Email không tồn tại");
            // noti_email1.setText("Email không tồn tại");
		}
    }
    public Boolean CheckCustomerExist(String name, String sex, String phone) throws SQLException{
        if (khachhangDAL.khachhang().CheckCustomerExist(name.trim(),sex.trim(),phone.trim())!=0){
            return true;
        }
        return false;
    }
    public void UpdateKh(int id, String name, String sex, String phone) throws SQLException{
        khachhangDAL.khachhang().UpdateKhachHang(id, name, sex, phone);
    }
    public void InsertKh(String name, String sex, String phone) throws SQLException{
        khachhangDAL.khachhang().InsertKhachHang( name, sex, phone);

    }
    public void DeleteKh(int id) throws SQLException {
        khachhangDAL.khachhang().DeleteKhachHang(id);
    }
    public Boolean CheckProductExist(String loai,String name,int sl,int gia,String org, String mota) throws SQLException{
        if(sanphamDAL.sanpham().CheckProductExist(loai, name, sl, gia, org, mota)!=0){
            return true;
        }
        return false;
    }
    public void InsertSanpham(String loai,String name,int sl,int gia,String org,String mota,String image) throws FileNotFoundException, SQLException{
        sanphamDAL.sanpham().InsertSanPham(loai, name, sl, gia, org, mota, image);
    }
    public void UpdateSanPham(int id,String loai,String name,int sl,int gia,String org, String mota,String image) throws FileNotFoundException, SQLException{
        sanphamDAL.sanpham().UpdateSanPham(id, loai, name, sl, gia, org, mota, image);
    }
    // public ObservableList<customer> TableUser(){
    //     ObservableList list = FXCollections.observableArrayList();
    //     lis
    //     return list;
    // }


    

    
}

