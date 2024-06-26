package qltc.BussinessLogicLayer;
import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import qltc.DataAccessLayer.connection;
import qltc.DataAccessLayer.khachhangDAL;
import qltc.Entity.product;
public class CardController {
    
    @FXML
    private Button card_add;

    @FXML
    private AnchorPane card_form;

    @FXML
    private ImageView card_image;

    @FXML
    private Spinner<Integer> card_spinner;

    @FXML
    private Label pro_name;

    @FXML
    private Label pro_price;

    private Image image;
    private int id;
    private product prodData;
    private Connection c;
    private PreparedStatement prepare;
    private ResultSet result;
    private Alert alert;
    private String type;
    private String pro_image;

    public void initialize() {
        setquantity();
    }

    public void setData(product prodData) {
        this.prodData = prodData;
        String getid = prodData.getId().substring(2);
        id = Integer.parseInt(getid);
        pro_name.setText(prodData.getName());
        pro_price.setText("$ " + String.valueOf(prodData.getPrice()));
        Image image = new Image(new ByteArrayInputStream(prodData.getImage()),170,170,true,true);
                card_image.setFitWidth(104);
                card_image.setFitHeight(78);
                card_image.setImage(image);
        price = prodData.getPrice();
        System.out.println(prodData.getImage());
    }
    private SpinnerValueFactory<Integer> spin;
    private int qty;
    public void setquantity() {
        spin = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
        card_spinner.setValueFactory(spin);
    }

    private double total;
    private String price;
    public void addBtn() throws SQLException {
        c = connection.connectionDB();
                int idkh = khachhangDAL.khachhang().getId(LogController.username1);
                CustomerController mForm = new CustomerController();
                qty = card_spinner.getValue();
                int stock = 0;
                String checkStock = "SELECT sl FROM sanpham WHERE masp = '"
                        + id + "'";
                prepare = c.prepareStatement(checkStock);
                result = prepare.executeQuery();
                if (result.next()) {
                    stock = result.getInt("sl");
                }
                if (stock < 0) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Invalid. This product is out of stock");
                    alert.showAndWait();
                } else {
                    String insertData = "INSERT INTO mua_sanpham "
                            + "(masp , makh , soluong , dongia) "
                            + "VALUES (?,?,?,?)";
                    prepare = c.prepareStatement(insertData);
                    prepare.setInt(1, id);
                    prepare.setInt(2, idkh);
                    prepare.setInt(3, qty);
                    prepare.setString(4, price);
                    prepare.executeUpdate();
                    int upStock = stock - qty;
                    String updateStock = "UPDATE sanpham SET sl=" + upStock + " WHERE masp='" + id + "'";
                    prepare = c.prepareStatement(updateStock);
                    prepare.executeUpdate();
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added!");
                    alert.showAndWait();
                }


    }


}
