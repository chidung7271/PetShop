package qltc.BussinessLogicLayer;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import qltc.DataAccessLayer.SanPhamDaMuaDAL;
import qltc.DataAccessLayer.hoadonDAL;
import qltc.DataAccessLayer.khachhangDAL;
import qltc.DataAccessLayer.petDAL;
import qltc.DataAccessLayer.sanphamDAL;
import qltc.Entity.SPDaMua;
import qltc.Entity.buySP;
import qltc.Entity.customer;
import qltc.Entity.pet;
import qltc.Entity.product;

public class MainController {

    @FXML
    private Button b1;

    @FXML
    private Button b10;

    @FXML
    private Button b11;

    @FXML
    private Button b12;

    @FXML
    private Button b2;

    @FXML
    private Button b3;

    @FXML
    private Button b4;

    @FXML
    private Button b5;

    @FXML
    private Button b6;

    @FXML
    private Button b7;

    @FXML
    private Button b8;

    @FXML
    private Button b9;
    
    @FXML
    private GridPane grid;

    @FXML
    private Label NotificationMoney;

    @FXML
    private Label NotificationService;

    @FXML
    private BarChart<String, Integer> chartHT;

    @FXML
    private TableView<SPDaMua> TableSPAlreadyBuy;

    
    @FXML
    private TableView<pet> TablePet1;

    @FXML
    private TableColumn<SPDaMua, String> date_damua;

    @FXML
    private TableColumn<SPDaMua, String> tenkh_damua;

    @FXML
    private TableColumn<SPDaMua, String> tensanpham_damua;
    
    @FXML
    private TableColumn<SPDaMua, String> price_damua;
    
    @FXML
    private TableColumn<SPDaMua, String> makh_damua;

    @FXML
    private TableColumn<pet, ImageView> imageview;
    @FXML
    private Button ServiceButton;

    @FXML
    private Button MoneyButton;

    @FXML
    private Button SPButton;
    @FXML
    private TableColumn<buySP, String> pricetbv_buy;
    @FXML
    private TableColumn<buySP, String> sltbv_buy;

    @FXML
    private TableColumn<buySP, String> sptbv_buy;

    @FXML
    private TableColumn<buySP, String> sumpricetbv_buy;

    @FXML
    private TableView<buySP> tablebuy;

    @FXML
    private ComboBox<String> product_kind;

    @FXML
    private TextField product_mota;

    @FXML
    private TextField product_name;

    @FXML
    private TextField product_org;

    @FXML
    private TextField product_price;

    @FXML
    private TextField product_sl;

    @FXML
    private TextField searchSP;
    
    @FXML
    private TextField TC_NAME;

    @FXML
    private TextField TC_NOTE;

    @FXML
    private DatePicker DateReturn;

    @FXML
    private ComboBox<String> TC_Chuong;

    @FXML
    private TextField priceTH;
    
    @FXML
    private TableView<product> tableSP;

    @FXML
    private Button addbutton_product;

    @FXML
    private Button deletebutton_product;

    @FXML
    private Button editbutton_product;

    @FXML
    private TableColumn<product, String> id_pro;

    @FXML
    private TableColumn<product, String> kind_pro;

    @FXML
    private TableColumn<product, String> mota_pro;
    
    @FXML
    private TableColumn<product, String> name_pro;

    @FXML
    private TableColumn<product, String> org_pro;

    @FXML
    private TableColumn<product, String> sl_pro;

    @FXML
    private TableColumn<product, String> price_pro;
    
    @FXML
    private TextField searchKH;

    @FXML
    private Button pageCustomer;

    @FXML
    private Button pageDashboard;

    @FXML
    private Button pagePay;

    @FXML
    private Button pagePet;

    @FXML
    private Button pageProduct;

    @FXML
    private Button pageService;

    @FXML
    private Label NotificationSpSell;

    @FXML
    private TextField folderField;

    @FXML
    private Button get_ava;
    @FXML
    private TableView<customer> tableKH;
    @FXML
    private TableColumn<customer, String> gender;

    @FXML
    private TableColumn<customer, String> name;

    @FXML
    private TableColumn<customer, String> phone;

    @FXML
    private TableColumn<customer, String> user_id;
    @FXML
    private Button add_button_user;

    @FXML
    private TextField user_name;

    @FXML
    private TextField user_phone;

    @FXML
    private TextField KHname_TH;

    @FXML
    private ChoiceBox<String> user_sex;
    
    @FXML
    private ComboBox<String> KH_buy;

    @FXML
    private ComboBox<String> KH_TH;

    @FXML
    private TextField KHname_buy;

    @FXML
    private ComboBox<String> SP_buy;

    @FXML
    private ComboBox<String> SPkind_buy;

    @FXML
    private ComboBox<String> SPname_buy;

    @FXML
    private TextField SPprice_buy;

    @FXML
    private TextField SPsl_buy;

    @FXML
    private AnchorPane page1;

    @FXML
    private AnchorPane page2;

    @FXML
    private AnchorPane page3;

    @FXML
    private AnchorPane page4;

    @FXML
    private AnchorPane page5;

    @FXML
    private AnchorPane page6;

    @FXML
    private AnchorPane page7;

    @FXML
    private AnchorPane page8;

    @FXML
    private AnchorPane page9;

    @FXML
    private AnchorPane page_muahang;

    @FXML
    private AnchorPane page_trongho;
    
    @FXML
    private ComboBox<String> TC_KIND;

    @FXML
    private ComboBox<String> TC_SEX;

    @FXML
    private TableColumn<pet, String> namepet;

    @FXML
    private TableColumn<pet, String> nhanpet;

    @FXML
    private TableColumn<pet, String> notepet;

    @FXML
    private TableColumn<pet, String> sexpet;

    @FXML
    private TableColumn<pet, String> chuongpet;

    @FXML
    private TableColumn<pet, String> kindpet;

    @FXML
    private TableView<pet> TablePet;
    @FXML
    private TableColumn<pet, String> mkh;
    @FXML
    private TableColumn<pet, String> mkhn;
    @FXML
    private TableColumn<pet, String> mtcn;
    @FXML
    private TableColumn<pet, String> kindpetn;
    @FXML
    private TableColumn<pet, String> sexpetn;
    @FXML
    private TableColumn<pet, String> nhanpetn;
    @FXML
    private TableColumn<pet, String> trapetn;
    @FXML
    private TableColumn<String , String> tiendichvu;
    @FXML
    private TableColumn<pet, String> namepetn;
    @FXML
    private TextField product_image;
    
    @FXML
    private Button get_anh;




    @FXML
    private TableColumn<pet, String> mtc;

    @FXML
    private TableColumn<?, ?> trapet;
    private ObservableList<customer> DataAllCustomer;
    private String[] sex = {"Nam","Nữ"};
    

    private int num = -1;
    public void initialize() throws SQLException, IOException {
        SetUpTableViewPet();
        SetUpTableViewUser();
        SetUpTableViewProduct();
        SetUpTableViewDaMua();
        DoanhThu();
        SetUpTablePetDaChamSoc();
        ChartMoney();

    }
    
    public void SwitchPage(ActionEvent event) throws SQLException, IOException{
    if( event.getSource() == pageDashboard ){
        ChartMoney();
        DoanhThu();
        SetUpTableViewDaMua();
        SetUpTablePetDaChamSoc();
        page1.setVisible(true);
        page2.setVisible(false);
        page3.setVisible(false);
        page4.setVisible(false);
        page5.setVisible(false);
        page7.setVisible(false);
        page8.setVisible(false);
    } else if (event.getSource() == pageProduct){
        SetUpKindProduct(product_kind);
        SetUpTableViewProduct();
        SearchProduct();
        page1.setVisible(false);
        page2.setVisible(true);
        page3.setVisible(false);
        page4.setVisible(false);
        page5.setVisible(false);
        page7.setVisible(false);
        page8.setVisible(false);
    } else if (event.getSource() == pageService){
        SetUpIdUserList(KH_buy);
        SetUpIdUserList(KH_TH);
        SetUpKindProduct(SPkind_buy);
        SetUpKindAnimal();
        SetUpSexAnimal();
        SetUpChuong();
        SetUpChuongRong();
        SetUpChuongTrong(petDAL.pet().GetTT());
        page1.setVisible(false);
        page2.setVisible(false);
        page3.setVisible(true);
        page4.setVisible(false);
        page5.setVisible(false);
        page7.setVisible(false);
        page8.setVisible(false);
    } else if(event.getSource() == pagePet){
        SetUpTableViewPet();
        page1.setVisible(false);
        page2.setVisible(false);
        page3.setVisible(false);
        page4.setVisible(true);
        page5.setVisible(false);
        page7.setVisible(false);
        page8.setVisible(false);
    } else if( event.getSource() == pageCustomer ){
        page1.setVisible(false);
        page2.setVisible(false);
        page3.setVisible(false);
        page4.setVisible(false);
        page5.setVisible(true);
        page7.setVisible(false);
        page8.setVisible(false);
        SetUpSexUser();
        SetUpTableViewUser();
        SearchCustomer();
    } else if( event.getSource() == SPButton){
        SetUpTableViewDaMua();
        page1.setVisible(false);
        page2.setVisible(false);
        page3.setVisible(false);
        page4.setVisible(false);
        page5.setVisible(false);
        page7.setVisible(true);
        
    } else if( event.getSource() == ServiceButton){
        page1.setVisible(false);
        page2.setVisible(false);
        page3.setVisible(false);
        page4.setVisible(false);
        page5.setVisible(false);
        page7.setVisible(false);
        page8.setVisible(true);

    }
}
// PHẦN CỦA KHÁCH HÀNG
    public void getSelectedItemToTheField(){
        if (num >= 0 && tableKH.getSelectionModel().isSelected(num)) {
            tableKH.getSelectionModel().clearSelection();
            ClearFieldUser();
        }
        num = tableKH.getSelectionModel().getSelectedIndex();
        if((num-1)<-1){return;}
        customer customerData =  tableKH.getSelectionModel().getSelectedItem();
        user_name.setText(customerData.getName());
        user_phone.setText(customerData.getSdt());
        user_sex.setValue(customerData.getGioitinh());;
    }

    public void ClearFieldUser(){
        user_name.clear();
        user_sex.getSelectionModel().clearSelection();
        user_phone.clear();
        
    }

    public void ButtonUpdateUser() throws SQLException{
        customer customerData =  tableKH.getSelectionModel().getSelectedItem();
        if (customerData == null){
            System.out.println("Ko có thông tin");
            return;
        }
        String id = customerData.getId().substring(2);
        int id_data = Integer.parseInt(id);
        if(
            user_name.getText().trim().isEmpty() || user_sex.getSelectionModel().getSelectedItem().isEmpty() || user_phone.getText().trim().isEmpty()
        ){
            System.out.println("Điền chưa đủ");
        }
        else if(khachhangDAL.khachhang().CheckCustomerExist(user_name.getText().trim(),user_sex.getSelectionModel().getSelectedItem(),user_phone.getText().trim()) != 0) {
            System.out.println("Thông tin đã tồn tại/trùng thông tin");
        }
        else{
        khachhangDAL.khachhang().UpdateKhachHang(id_data,user_name.getText().trim(),user_sex.getSelectionModel().getSelectedItem(),user_phone.getText().trim());
        SetUpTableViewUser();
        ClearFieldUser();
        SearchCustomer();
    }
    }    
    public void ButtonAddUser() throws SQLException{
        if(
            user_name.getText().trim().isEmpty() || user_sex.getSelectionModel().getSelectedItem().isEmpty() || user_phone.getText().trim().isEmpty()
        ){
            System.out.println("Điền chưa đủ");
        }
        else if(khachhangDAL.khachhang().CheckCustomerExist(user_name.getText().trim(),user_sex.getSelectionModel().getSelectedItem(),user_phone.getText().trim()) != 0) {
            System.out.println("Exist");
        }
        else{
        khachhangDAL.khachhang().InsertKhachHang(user_name.getText().trim(),user_sex.getSelectionModel().getSelectedItem().trim(),user_phone.getText().trim() );
        SetUpTableViewUser();
        ClearFieldUser();
        SearchCustomer();
        }
    }

    public void ButtonDeleteUser() throws SQLException{
        int data = tableKH.getSelectionModel().getSelectedIndex();
        if((data-1)<-1){
            System.out.println("Chưa có thông tin");
            return;}
        customer customerData =  tableKH.getSelectionModel().getSelectedItem();
        String id = customerData.getId().substring(2);
        int id_data = Integer.parseInt(id);
        khachhangDAL.khachhang().DeleteKhachHang(id_data);
        SetUpTableViewUser();
        ClearFieldUser();
        SearchCustomer();
        
    }

    public void SetUpSexUser(){
        List<String> lstSex = Arrays.asList(sex); //mảng thành list

        ObservableList<String> lstsexFX =  FXCollections.observableArrayList(lstSex);
        user_sex.setItems(lstsexFX);
    }

    public void SetUpTableViewUser() throws SQLException{
        DataAllCustomer = khachhangDAL.khachhang().GetKhachHang();
        user_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        gender.setCellValueFactory(new PropertyValueFactory<>("gioitinh"));
        phone.setCellValueFactory(new PropertyValueFactory<>("sdt"));
        tableKH.setItems(DataAllCustomer);
    }
    public void SearchCustomer() throws SQLException{
      
        // tạo một bộ lọc mới và giữ nguyên các phần tử
        FilteredList<customer> BoLoc = new FilteredList<>(DataAllCustomer,e -> true);
        //thêm Listener để check giá trị mới và search
        //setPredicate để xác định phần tử nào được hiển thị
        searchKH.textProperty().addListener((observable,oldValue,newValue) -> {
        BoLoc.setPredicate(PredicateCustomer -> {
            if (newValue == null || newValue.trim().isEmpty()){
                return true;
                }
            String key= newValue.toLowerCase();
            System.out.println(key);
            if(PredicateCustomer.getId().toLowerCase().contains(key)){
                
                return true;
            } else if(PredicateCustomer.getName().toLowerCase().contains(key)){
                return true;
            } else if(PredicateCustomer.getGioitinh().toLowerCase().contains(key)){
                return true;
            } else if(PredicateCustomer.getSdt().toLowerCase().contains(key)){
                return true;
            } else{return false;}
            });
        });
        SortedList<customer> sortlist= new SortedList<>(BoLoc);
        sortlist.comparatorProperty().bind(tableKH.comparatorProperty());
        tableKH.setItems(sortlist);
    }

// PHẦN CỦA SẢN PHẨM
    private int num_pro = -1;
    public void getSelectedItemProductToTheField(){
        if (num_pro >= 0 && tableSP.getSelectionModel().isSelected(num_pro)) {
            tableSP.getSelectionModel().clearSelection();
            ClearFieldProduct();
        }
        num_pro = tableSP.getSelectionModel().getSelectedIndex();
        if((num_pro-1)<-1){return;}
        product productData =  tableSP.getSelectionModel().getSelectedItem();
        product_sl.setText(productData.getSl());
        product_kind.setValue(productData.getKind());
        product_mota.setText(productData.getDescribe());
        product_name.setText(productData.getName());
        product_org.setText(productData.getOrigin());
        product_price.setText(productData.getPrice());
        byte[] imageData = productData.getImage();
        String imageString = Base64.getEncoder().encodeToString(imageData);
        product_image.setText(imageString);
        // product_image.setText(productData.getImage());
    }

    public void ClearFieldProduct(){
        product_sl.clear();
        product_kind.getSelectionModel().clearSelection();
        product_mota.clear();
        product_name.clear();
        product_org.clear();
        product_price.clear();
        product_image.clear();
    }

    private String[] kind_product = {"Thức ăn","Phụ kiện","Dụng cụ","Đồ chơi"};
    public void SetUpKindProduct(ComboBox<String> data){
        List<String> lstPro = Arrays.asList(kind_product);

        ObservableList<String> lstsexFX =  FXCollections.observableArrayList(lstPro);
        data.setItems(lstsexFX);
    }
    private ObservableList<product> DataAllProduct;
    public void SetUpTableViewProduct() throws SQLException, IOException{
        DataAllProduct = FXCollections.observableArrayList(FunctionClient.FC().GetListSpAdmin());
        id_pro.setCellValueFactory(new PropertyValueFactory<>("id"));
        kind_pro.setCellValueFactory(new PropertyValueFactory<>("kind"));
        name_pro.setCellValueFactory(new PropertyValueFactory<>("name"));
        sl_pro.setCellValueFactory(new PropertyValueFactory<>("sl"));
        price_pro.setCellValueFactory(new PropertyValueFactory<>("price"));
        org_pro.setCellValueFactory(new PropertyValueFactory<>("origin"));
        mota_pro.setCellValueFactory(new PropertyValueFactory<>("describe"));
        tableSP.setItems(DataAllProduct);
    }

    public void SearchProduct() throws SQLException{
        FilteredList<product> BoLoc = new FilteredList<>(DataAllProduct,e -> true);
        searchSP.textProperty().addListener((observable,oldValue,newValue) -> {
        BoLoc.setPredicate(PredicateProduct -> {
            if (newValue == null || newValue.trim().isEmpty()){
                return true;
                }
            String key= newValue.toLowerCase();
            System.out.println(key);
            if(PredicateProduct.getId().toLowerCase().contains(key)){
                return true;
            } else if(PredicateProduct.getKind().toLowerCase().contains(key)){
                return true;
            } else if(PredicateProduct.getName().toLowerCase().contains(key)){
                return true;
            } else if(PredicateProduct.getSl().toLowerCase().contains(key)){
                return true;
            } else if(PredicateProduct.getPrice().toLowerCase().contains(key)){
                return true;
            } else if(PredicateProduct.getOrigin().toLowerCase().contains(key)){
                return true;
            } else if(PredicateProduct.getDescribe().toLowerCase().contains(key)){
                return true;
            } else{return false;}
            });
        });
        SortedList<product> sortlist= new SortedList<>(BoLoc);
        sortlist.comparatorProperty().bind(tableSP.comparatorProperty());
        tableSP.setItems(sortlist);
    }

    public void GetAnhSP(){

        Stage newStage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Avatar");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.setInitialFileName("");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("All Files", "*.*"));
        File selectedFolder = fileChooser.showOpenDialog(newStage);
        if (selectedFolder != null) {
            product_image.setText(selectedFolder.getAbsolutePath());
        }
        newStage.close();   
    
    }

    public void ButtonAddProduct() throws SQLException, IOException{
        int sl,gia;
        try {
            sl = Integer.parseInt(product_sl.getText().trim());
            gia = Integer.parseInt(product_price.getText().trim());
        } catch (Exception e) {
            System.out.println("Nhập số khả dụng");
            return;
        }
        
        if(
            product_name.getText().trim().isEmpty() || product_kind.getSelectionModel().getSelectedItem().isEmpty() || product_sl.getText().trim().isEmpty() || product_price.getText().trim().isEmpty() || product_mota.getText().trim().isEmpty() || product_org.getText().trim().isEmpty()
        ){
            System.out.println("Nah");
        }
        else if(sanphamDAL.sanpham().CheckProductExist(product_kind.getSelectionModel().getSelectedItem(),product_name.getText().trim(),sl,gia,product_org.getText().trim(),product_mota.getText().trim()) != 0) {
            System.out.println("Exist");
        }
        else{
        sanphamDAL.sanpham().InsertSanPham(product_kind.getSelectionModel().getSelectedItem(),product_name.getText().trim(),sl,gia,product_org.getText().trim(),product_mota.getText().trim(),product_image.getText().trim());
        SetUpTableViewProduct();
        ClearFieldProduct();;
        SearchProduct();
        }
    }

    public void ButtonUpdateProduct() throws SQLException, IOException{
        product productData =  tableSP.getSelectionModel().getSelectedItem();
        if (productData == null){
            System.out.println("Ko có thông tin");
            return;
        }
        int sl,gia;
        try {
            sl = Integer.parseInt(product_sl.getText().trim());
            gia = Integer.parseInt(product_price.getText().trim());
        } catch (Exception e) {
            System.out.println("Nhập số khả dụng");
            return;}
        String id = productData.getId().substring(2);
        int id_data = Integer.parseInt(id);
        if(
            product_name.getText().trim().isEmpty() || product_kind.getSelectionModel().getSelectedItem().isEmpty() || product_sl.getText().trim().isEmpty() || product_price.getText().trim().isEmpty() || product_mota.getText().trim().isEmpty() || product_org.getText().trim().isEmpty()
        ){
            System.out.println("Nah");
        }
        // else if(sanphamDAL.sanpham().CheckProductExist(product_kind.getSelectionModel().getSelectedItem(),product_name.getText().trim(),sl,gia,product_org.getText().trim(),product_mota.getText().trim()) != 0) {
        //     System.out.println("Thông tin đã tồn tại/trùng thông tin");
        // }
        else{
        sanphamDAL.sanpham().UpdateSanPham(id_data,product_kind.getSelectionModel().getSelectedItem(),product_name.getText().trim(),sl,gia,product_org.getText().trim(),product_mota.getText().trim(),product_image.getText().trim());
        SetUpTableViewProduct();
        ClearFieldProduct();;
        SearchProduct();
        }}

    public void ButtonDeleteProduct() throws SQLException, IOException{
            int data = tableSP.getSelectionModel().getSelectedIndex();
            if((data-1)<-1){
                System.out.println("Chưa có thông tin");
                return;}
            product productData =  tableSP.getSelectionModel().getSelectedItem();
            String id = productData.getId().substring(2);
            int id_data = Integer.parseInt(id);
            sanphamDAL.sanpham().DeleteSanPham(id_data);
            SetUpTableViewProduct();
            ClearFieldProduct();;
            SearchProduct();
            
        }
// PHẦN DỊCH VỤ
// + MUA HÀNG
    public void SetUpIdUserList(ComboBox<String> box){
        ObservableList<customer> data  = DataAllCustomer;
        ObservableList<String> customerIDs = FXCollections.observableArrayList(data.stream().map(customer::getId).collect(Collectors.toList()));
        box.setItems(customerIDs);
    }

    public void GetNameKH(){
        ObservableList<customer> data  = DataAllCustomer;
        String id = KH_buy.getValue();
        for (customer customer : data) {
            if (customer.getId().equals(id)) {
                KHname_buy.setText(customer.getName());
                return;}}
        KHname_buy.clear();
    }

    public void GetNameSp() throws SQLException{
        ObservableList<product> data  = DataAllProduct;
        ObservableList<String> lst = FXCollections.observableArrayList();
        String kind = SPkind_buy.getValue();
        for (product product : data) {
            if (product.getKind().equals(kind)) {
                lst.add(product.getName());
            }
        SPname_buy.setItems(lst);
    }}

    String masp ;
    public void GetPriceSp() throws SQLException{
        ObservableList<product> data  = DataAllProduct;
        String kind = SPkind_buy.getValue();
        String name = SPname_buy.getValue();
        for (product product : data) {
            if (product.getKind().equals(kind) && product.getName().equals(name)){
                SPprice_buy.setText(product.getPrice());
                masp=product.getId();
            }
    }}

    public void ClearFieldBuy(){
        SPname_buy.getSelectionModel().clearSelection();
        SPsl_buy.clear();
        SPprice_buy.clear();
        KH_buy.getSelectionModel().clearSelection();
        KHname_buy.clear();
        
    }
    ObservableList<buySP> table_buy = FXCollections.observableArrayList();

    public void AddButtonBuy(){
        if(
            SPname_buy.getSelectionModel().getSelectedItem().trim().isEmpty() || SPsl_buy.getText().trim().isEmpty() || SPprice_buy.getText().trim().isEmpty() 
        ){
            System.out.println("Điền chưa đủ");}
        else{
        String data = SPprice_buy.getText();
        String data2 = SPsl_buy.getText().trim() ;
        int price = Integer.parseInt(data);
        int sl  = Integer.parseInt(data2);
        int tong = price * sl;
        buySP buySP = new buySP(masp ,SPname_buy.getSelectionModel().getSelectedItem().trim(), SPsl_buy.getText().trim() , SPprice_buy.getText().trim(),String.valueOf(tong));
        for (buySP dataCheck : table_buy) {
            if (dataCheck.getId_sp().equals(buySP.getId_sp())){
                return;
            }}
        table_buy.add(buySP);
        SetUpTableViewBuy();
        ClearFieldBuy();
    }}
    
    public void ButtonDeleteBuy() throws SQLException{
        int data = tablebuy.getSelectionModel().getSelectedIndex();
        if((data-1)<-1){
            System.out.println("Chưa có thông tin");
            return;}
        buySP DataDelete =  tablebuy.getSelectionModel().getSelectedItem();
        table_buy.remove(DataDelete);
        SetUpTableViewBuy();
        
    }

    public void SetUpTableViewBuy(){
        sptbv_buy.setCellValueFactory(new PropertyValueFactory<>("sp"));
        sltbv_buy.setCellValueFactory(new PropertyValueFactory<>("sl"));
        pricetbv_buy.setCellValueFactory(new PropertyValueFactory<>("price"));
        sumpricetbv_buy.setCellValueFactory(new PropertyValueFactory<>("tonggiatri"));
        tablebuy.setItems(table_buy);
    }
    public void BuyButton() throws SQLException{
        int total = 0;
        String kh=KH_buy.getValue();
        int idkh=  Integer.parseInt(kh.substring(2));
        for (buySP data: table_buy){
            Date date = new Date();
            SimpleDateFormat ft = new SimpleDateFormat ("dd/MM/yyyy");
            int id_sp = Integer.parseInt(data.getId_sp().substring(2));
            // int id_kh = Integer.parseInt(data.getId_kh().substring(2));
            // int tgt= Integer.parseInt(data.getTonggiatri());
            int dg = Integer.parseInt(data.getPrice());
            int sl=Integer.parseInt(data.getSl());
            hoadonDAL.hd().insertEmpty();
            int a= Integer.parseInt(data.getPrice());
            int b= Integer.parseInt(data.getSl());
            total = total + a*b;
            SanPhamDaMuaDAL.sanphamdamua().InsertSanPhamDaMua(id_sp,idkh, ft.format(date), dg,sl);
        }
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("dd/MM/yyyy");
        hoadonDAL.hd().updateKhMua(idkh, total, ft.format(date));

        
        table_buy.clear();
        SetUpTableViewBuy();
        
    }

    public void onClickMuaHang(){
        page_muahang.setVisible(true);
        page_trongho.setVisible(false);
    }

//TRÔNG HỘ
    public void onClickTrongHo(){
        page_muahang.setVisible(false);
        page_trongho.setVisible(true);
    }

    public void GetNameKHTH(){
        ObservableList<customer> data  = DataAllCustomer;
        String id = KH_TH.getValue();
        for (customer customer : data) {
            if (customer.getId().equals(id)) {
                KHname_TH.setText(customer.getName());
                return;}}
        KHname_TH.clear();
    }

    private String[] kind_animal = {"Chó","Mèo","Thỏ"};
    public void SetUpKindAnimal(){
        List<String> lstPro = Arrays.asList(kind_animal);

        ObservableList<String> ListKind = FXCollections.observableArrayList(lstPro);
        TC_KIND.setItems(ListKind);
    }

    private String[] sexanimal = {"Đực","Cái"};
    public void SetUpSexAnimal(){
        List<String> lstPro = Arrays.asList(sexanimal);

        ObservableList<String> ListKind = FXCollections.observableArrayList(lstPro);
        TC_SEX.setItems(ListKind);
    }

    private String[] Chuong = {"Chuồng 1","Chuồng 2","Chuồng 3","Chuồng 4","Chuồng 5","Chuồng 6","Chuồng 7","Chuồng 8","Chuồng 9","Chuồng 10","Chuồng 11","Chuồng 12",};
    public void SetUpChuong(){
        List<String> lstPro = Arrays.asList(Chuong);

        ObservableList<String> ListCH = FXCollections.observableArrayList(lstPro);
        TC_Chuong.setItems(ListCH);
    }

    public void ClearFieldCH(){
        KH_TH.getSelectionModel().clearSelection();
        KHname_TH.clear();
        TC_KIND.getSelectionModel().clearSelection();
        TC_SEX.getSelectionModel().clearSelection();
        TC_NAME.clear();
        TC_NOTE.clear();
        DateReturn.setValue(null);
        TC_Chuong.getSelectionModel().clearSelection();
        folderField.clear();
    }

    public void GetLinkAvatar(){
        Stage newStage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Avatar");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.setInitialFileName("");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("All Files", "*.*"));
        File selectedFolder = fileChooser.showOpenDialog(newStage);
        if (selectedFolder != null) {
            folderField.setText(selectedFolder.getAbsolutePath());
        }
        newStage.close();
    }


    public void SetUpChuongTrong(List<Integer> MyList ){
        for(int num : MyList) {
            Button button = (Button) grid.lookup("#b" + num);
            button.setStyle("-fx-background-color: red;");
        }
    }

    public void SetUpChuongRong() throws SQLException{
        PetList = petDAL.pet().GetPet();
        List<Integer> numbersList = new ArrayList<>();
        List<Integer> DeleteNum = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            numbersList.add(i);
        }
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        for (pet pet : PetList){
            LocalDate date = LocalDate.parse(pet.getNgaytra(), formatter);
            if (date.isAfter(LocalDate.now())){
                DeleteNum.add(Integer.parseInt(pet.getId_phong().substring(7)));
            }
        }
        numbersList.removeAll(DeleteNum);
        String numbersString = numbersList.stream().map(Object::toString).collect(Collectors.joining(", "));
        petDAL.pet().ChuongRong(numbersString);
    }
    
    
    public void TrongHoButton() throws SQLException, NumberFormatException, IOException{
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("dd/MM/yyyy");
        LocalDate selectedDate = DateReturn.getValue();
        String kh = KH_TH.getSelectionModel().getSelectedItem().trim().substring(2);
        int kh_data = Integer.parseInt(kh);
        String chuong = TC_Chuong.getSelectionModel().getSelectedItem().trim().substring(7);
        int chuong_data = Integer.parseInt(chuong);
        if(
            KH_TH.getSelectionModel().getSelectedItem().isEmpty() ||  TC_SEX.getSelectionModel().getSelectedItem().isEmpty() || TC_KIND.getSelectionModel().getSelectedItem().isEmpty() || TC_NAME.getText().trim().isEmpty() || TC_NOTE.getText().trim().isEmpty() || TC_Chuong.getSelectionModel().getSelectedItem().isEmpty() || DateReturn.getValue().toString().isEmpty()
        ){
            System.out.println("Điền chưa đủ");
        }
        else{
        if (FunctionClient.FC().InsertChuong(chuong_data) == 1){
            System.out.println("b" + chuong_data);
            Button button = (Button) grid.lookup("#b" + chuong_data);
            button.setStyle("-fx-background-color: red;");
        }
        else{
            System.out.println("phòng này đã đầy");
        };
        FunctionClient.FC().UpdateHD(kh_data, Integer.parseInt(priceTH.getText().trim()), ft.format(date));

        FunctionClient.FC().InsertPet(kh_data, TC_KIND.getSelectionModel().getSelectedItem().trim(),
                TC_NAME.getText().trim(), TC_SEX.getSelectionModel().getSelectedItem().trim(),
                TC_NOTE.getText().trim(), ft.format(date), ft.format(java.sql.Date.valueOf(selectedDate)),
                chuong_data, folderField.getText().trim(), Integer.parseInt(priceTH.getText().trim()));
        ClearFieldCH();
        }
    }
    //THÚ CƯNG
    int acc;
    ObservableList<pet> PetList;
    public void SetUpTableViewPet() throws SQLException{
        PetList = petDAL.pet().GetPet();
        ObservableList<pet> PetInShop = FXCollections.observableArrayList();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        for (pet pet : PetList){
            LocalDate date = LocalDate.parse(pet.getNgaytra(), formatter);
            if (date.isAfter(LocalDate.now())){
                PetInShop.add(pet);
            }
        }
        acc = PetList.size();
        mkh.setCellValueFactory(new PropertyValueFactory<>("kh"));
        mtc.setCellValueFactory(new PropertyValueFactory<>("id"));
        kindpet.setCellValueFactory(new PropertyValueFactory<>("kind"));
        namepet.setCellValueFactory(new PropertyValueFactory<>("name"));
        sexpet.setCellValueFactory(new PropertyValueFactory<>("sex"));
        notepet.setCellValueFactory(new PropertyValueFactory<>("note"));
        nhanpet.setCellValueFactory(new PropertyValueFactory<>("day"));
        trapet.setCellValueFactory(new PropertyValueFactory<>("ngaytra"));
        chuongpet.setCellValueFactory(new PropertyValueFactory<>("id_phong"));
        TablePet.setItems(PetInShop);
    }

    public void DoanhThu() throws SQLException, IOException{
        int sotien = 0;
        ObservableList<SPDaMua> data = FXCollections.observableArrayList(FunctionClient.FC().DanhSachTatCaDaMua());
        for (SPDaMua sp : data){
            sotien =  Integer.parseInt(sp.getChiphi())+ sotien;
        }
        NotificationMoney.setText(String.valueOf(acc*200000+sotien));
    }
    public void ChartMoney() throws SQLException {
        chartHT.getData().clear();
        XYChart.Series<String, Integer> data = new XYChart.Series<>();
        // SanPhamDaMuaDAL.sanphamdamua().DoanhThuTheoNgay(chartHT, data);
        hoadonDAL.hd().DoanhThuTheoNgay(chartHT, data);

    }
    public void SetUpTableViewDaMua() throws SQLException, IOException{
        ObservableList<SPDaMua> data = FXCollections.observableArrayList(FunctionClient.FC().DanhSachTatCaDaMua());
        int size = data.size();
        NotificationSpSell.setText(String.valueOf(size));
        makh_damua.setCellValueFactory(new PropertyValueFactory<>("makh"));
        tenkh_damua.setCellValueFactory(new PropertyValueFactory<>("ten"));
        tensanpham_damua.setCellValueFactory(new PropertyValueFactory<>("tensp"));
        price_damua.setCellValueFactory(new PropertyValueFactory<>("chiphi"));
        // date_damua.setCellValueFactory(new PropertyValueFactory<>("ngay"));
        TableSPAlreadyBuy.setItems(data);
    }
    public void SetUpTablePetDaChamSoc() throws SQLException{
        NotificationService.setText(String.valueOf(acc));
        mkhn.setCellValueFactory(new PropertyValueFactory<>("kh"));
        mtcn.setCellValueFactory(new PropertyValueFactory<>("id"));
        kindpetn.setCellValueFactory(new PropertyValueFactory<>("kind"));
        namepetn.setCellValueFactory(new PropertyValueFactory<>("name"));
        sexpetn.setCellValueFactory(new PropertyValueFactory<>("sex"));
        nhanpetn.setCellValueFactory(new PropertyValueFactory<>("day"));
        trapetn.setCellValueFactory(new PropertyValueFactory<>("ngaytra"));
        tiendichvu.setCellValueFactory(cellData -> new SimpleStringProperty("💰 +20000 đồng"));
        try {
            imageview.setCellValueFactory(new PropertyValueFactory<>("image"));
        } catch (Exception e) {
            // TODO: handle exception
        }        
        TablePet1.setItems(PetList);
    }
}