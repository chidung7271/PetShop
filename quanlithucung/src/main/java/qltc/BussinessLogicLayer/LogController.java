package qltc.BussinessLogicLayer;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.Random;

import org.mindrot.jbcrypt.BCrypt;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import qltc.DataAccessLayer.khachhangDAL;

public class LogController {

    @FXML
    private Hyperlink admin_forgotpass;

    @FXML
    private AnchorPane admin_form;

    @FXML
    private Button admin_logbtn;

    @FXML
    private Label admin_noti;

    @FXML
    private PasswordField admin_password;

    @FXML
    private TextField admin_username;

    @FXML
    private Button adminbtn;

    @FXML
    private Button button_login;

    @FXML
    private Button button_singup;

    @FXML
    private Button changepass;

    @FXML
    private AnchorPane changepass_form;

    @FXML
    private Button confirmotp;

    @FXML
    private Button customerbtn;

    @FXML
    private AnchorPane email_form;

    @FXML
    private Hyperlink email_log;

    @FXML
    private AnchorPane first_form;

    @FXML
    private PasswordField forgot_confirm;

    @FXML
    private Button forgot_confirmotp;

    @FXML
    private TextField forgot_email;

    @FXML
    private AnchorPane forgot_email_form;

    @FXML
    private Button forgot_log;

    @FXML
    private Button forgot_log1;

    @FXML
    private TextField forgot_otp;

    @FXML
    private PasswordField forgot_pass;

    @FXML
    private Button forgot_sendEmail;

    @FXML
    private Hyperlink forgotpass;

    @FXML
    private AnchorPane login_form;

    @FXML
    private Label noti_forgot_confirm;

    @FXML
    private Label noti_log;
    @FXML
    private Label noti_email;

    @FXML
    private Label noti_log11;

    @FXML
    private Label noti_otp1;

    @FXML
    private Label noti_sign;

    @FXML
    private TextField otpp;

    @FXML
    private PasswordField password;

    @FXML
    private Button relog;

    @FXML
    private AnchorPane relog_form;

    @FXML
    private Label relog_head;

    @FXML
    private Label relog_p;

    @FXML
    private Button sendEmail;

    @FXML
    private Hyperlink si_log;

    @FXML
    private AnchorPane signup_form;

    @FXML
    private PasswordField su_confirm;

    @FXML
    private TextField su_email;

    @FXML
    private PasswordField su_pass;

    @FXML
    private Hyperlink su_signup;

    @FXML
    private TextField su_username;

    @FXML
    private TextField username;

    private String otp;

    @FXML
    public int data = 0;
    static String username1 = "";

    public void login() throws IOException {
        // System.out.println(accDAL.acc().accExist(username.getText(),password.getText()));
        if (!(username.getText().isEmpty() || password.getText().isEmpty())) {
            // khachhangDAL.khachhang().accExist(username.getText(),password.getText());
            if (FunctionClient.FC().LoginUser(username.getText(), password.getText())) {
                username1 = username.getText();
                button_login.getScene().getWindow().hide();
                Parent root = FXMLLoader
                        .load(getClass().getClassLoader().getResource("qltc\\Resource\\FXML_CSS\\kh2.fxml"));
                Scene scene = new Scene(root);
                scene.getStylesheets().add(getClass().getClassLoader()
                        .getResource("qltc/Resource/FXML_CSS/MakeSthMoreBeautiful.css").toExternalForm());
                Stage stage = new Stage();
                stage.setTitle("Pet Shop Management Application");
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
            } else {
                noti_log.setText("Sai tên đăng nhập/mật khẩu");
            }
        } else
            noti_log.setText("Hãy nhập tên đăng nhập và mật khẩu");
    }

    public void signup_btn() throws IOException {
        if (su_username.getText().isEmpty() || su_pass.getText().isEmpty() || su_email.getText().isEmpty()) {
            noti_log.setText("Hãy nhập đầy đủ thông tin");
        } else if (!su_pass.getText().equals(su_confirm.getText())) {
            noti_sign.setText("xác nhận không khớp với mật khẩu");
        } else if (FunctionClient.FC().UsernameExist(su_username.getText())) {
            noti_sign.setText("Tên đăng nhập đã tồn tại");
        } else {
            // String password = su_pass.getText();
            // String hash = BCrypt.hashpw(password, BCrypt.gensalt(12));
            // System.out.println("BCrypt hash: " + hash);
            FunctionClient.FC().SignUpUser(su_username.getText(), su_pass.getText(), su_email.getText());
            // accDAL.acc().insertAcc(su_username.getText(),hash,su_email.getText());
            // khachhangDAL.khachhang().themkh(su_username.getText(),hash,su_email.getText());
            relog_head.setText("Chào Mừng Gia Nhập PetShop");
            relog_p.setText("Đã đăng kí thành công");
            signup_form.setVisible(false);
            relog_form.setVisible(true);
        }
    }

    public void si_log_btn() {
        su_confirm.setText("");
        su_pass.setText("");
        su_username.setText("");
        signup_form.setVisible(false);
        forgot_email_form.setVisible(false);
        login_form.setVisible(true);
        email_form.setVisible(false);
        changepass_form.setVisible(false);
    }

    public void email_log_btn() {
        su_email.setText("");
        otpp.setText("");
        email_form.setVisible(false);
        login_form.setVisible(true);
    }

    public void su_signup_btn() {
        username.setText("");
        password.setText("");
        email_form.setVisible(true);
        login_form.setVisible(false);
        ;
    }

    // public void sendEmail(String to,String otp)throws
    // UnsupportedEncodingException{
    // final String from="lamn37140@gmail.com";
    // final String password="nijdtedywgakfcps";
    // // final String from="vemailchatatc@gmail.com";
    // // final String password="iencsgrjdsokjtiq";
    // Properties props = new Properties();
    // props.put("mail.smtp.host", "smtp.gmail.com");
    // props.put("mail.smtp.port","587");
    // props.put("mail.smtp.auth","true");
    // props.put("mail.smtp.starttls.enable","true");
    // Authenticator auth = new Authenticator() {
    // @Override
    // protected PasswordAuthentication getPasswordAuthentication(){
    // return new PasswordAuthentication(from, password);
    // }
    // };
    // //tạo tin nhắn
    // Session session= Session.getInstance(props,auth);
    // MimeMessage msg=new MimeMessage(session);
    // try {
    // Address senderAddress = new InternetAddress(from);
    // msg.setFrom(senderAddress);
    // msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
    // msg.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to,false));
    // msg.setSubject("YOUR OTP!");
    // //quy định ngày gửi
    // msg.setSentDate(new Date());
    // //nội dung
    // msg.setText(otp,"UTF-8");
    // //gửi
    // Transport.send(msg);
    // // noti_email.setText("đã gửi thành công");
    // // noti_email1.setText("đã gửi thành công");
    // }
    // catch (MessagingException e) {
    // // noti_email.setText("Email không tồn tại");
    // // noti_email1.setText("Email không tồn tại");
    // }
    // }
    public static String generateOTP(int length) {
        String numbers = "0123456789";
        Random random = new Random();
        StringBuilder otp = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(numbers.length());
            otp.append(numbers.charAt(index));
        }
        return otp.toString();
    }

    public void sendEmail_btn() throws SQLException, IOException {
        noti_email.setText("");
        if (FunctionClient.FC().CheckEmailExist(su_email.getText())) {
            noti_email.setText("email đã được sử dụng");
        } else if (su_email.getText().isEmpty()) {
            noti_email.setText("Vui lòng nhập email");
        } else {
            otp = generateOTP(6);
            FunctionServer.FS().sendEmail(su_email.getText(), otp);
            noti_email.setText("Đã gửi thành công");
        }
    }

    public void confirmotp_btn() {
        if (otpp.getText().equals(otp)) {
            email_form.setVisible(false);
            signup_form.setVisible(true);
        } else {
            noti_email.setText("Mã xác nhận không chính xác");
        }
    }

    public void forgot_sendEmail_btn() throws UnsupportedEncodingException, SQLException {
        if (forgot_email.getText().isEmpty()) {
            noti_otp1.setText("vui lòng nhập email");
        } else if (!khachhangDAL.khachhang().emailExist(forgot_email.getText())) {
            noti_otp1.setText("Email không chính xác");

        } else {
            noti_otp1.setText("");
            otp = generateOTP(6);
            FunctionServer.FS().sendEmail(forgot_email.getText(), otp);
        }
    }

    public void forgot_confirmotp_btn() {
        if (forgot_otp.getText().equals(otp)) {
            forgot_email.setVisible(false);
            changepass_form.setVisible(true);
        } else {
            noti_otp1.setText("Mã xác nhận không đúng");
        }
    }

    public void relog_btn() throws IOException {
        button_login.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("qltc\\Resource\\FXML_CSS\\Scence.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getClassLoader()
                .getResource("qltc/Resource/FXML_CSS/MakeSthMoreBeautiful.css").toExternalForm());
        Stage stage = new Stage();
        stage.setTitle("Pet Shop Management Application");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void forgotpass_btn() {
        login_form.setVisible(false);
        forgot_email_form.setVisible(true);
    }

    public void forgot_log_btn() {
        forgot_email_form.setVisible(false);
        login_form.setVisible(true);
    }

    public void changepass_btn() throws SQLException, IOException {
        if (!(forgot_pass.getText().equals(forgot_confirm.getText()))) {
            noti_forgot_confirm.setText("Xác nhận phải trùng với mật khẩu");
        } else {
            String password = forgot_pass.getText();
            String hash = BCrypt.hashpw(password, BCrypt.gensalt(12));
            System.out.println("BCrypt hash: " + hash);
            // khachhangDAL.khachhang().changepass(forgot_email.getText(), hash);
            FunctionClient.FC().ChangePass(forgot_email.getText(), hash);
            relog_form.setVisible(true);
            changepass_form.setVisible(false);
            relog_head.setText("Đổi mật khẩu thành công");
            relog_p.setText("Nhấn để đăng nhập");
        }
    }

    public void admin_logbtn() throws SQLException, IOException {
        if (!(admin_username.getText().isEmpty() || admin_password.getText().isEmpty())) {
            if (FunctionClient.FC().LogAdmin(admin_username.getText(), admin_password.getText())) {
                admin_logbtn.getScene().getWindow().hide();
                Parent root = FXMLLoader
                        .load(getClass().getClassLoader().getResource("qltc\\Resource\\FXML_CSS\\main.fxml"));
                Scene scene = new Scene(root);
                scene.getStylesheets().add(getClass().getClassLoader()
                        .getResource("qltc/Resource/FXML_CSS/MakeSthMoreBeautiful.css").toExternalForm());
                Stage stage = new Stage();
                stage.setTitle("Pet Shop Management Application");
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
            } else {
                admin_noti.setText("Sai tên đăng nhập/mật khẩu");
            }
        } else
            admin_noti.setText("Hãy nhập tên đăng nhập và mật khẩu");
    }

    public void customerbtn() {
        first_form.setVisible(false);
        login_form.setVisible(true);
    }

    public void adminbtn() {
        first_form.setVisible(false);
        admin_form.setVisible(true);
    }
}