package qltc.BussinessLogicLayer;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import qltc.Entity.PetView;
import qltc.Entity.SPDaMua;
import qltc.Entity.customer;
import qltc.Entity.pet;
import qltc.Entity.product;

public class CustomerController {

    @FXML
    private DatePicker DateReturn;

    @FXML
    private ComboBox<String> KH_buy;

    @FXML
    private TextField KHname_buy;

    @FXML
    private Label NotificationSpSell;

    @FXML
    private Label NotificationSpSell1;

    @FXML
    private TextField PetKH;

    @FXML
    private TextField PetKH1;

    @FXML
    private ChoiceBox<String> PetKind;

    @FXML
    private ChoiceBox<String> PetKind1;

    @FXML
    private TextField PetName;

    @FXML
    private TextField PetName1;

    @FXML
    private TextField PetNote;

    @FXML
    private TextField PetNote1;

    @FXML
    private Button SPButton;

    @FXML
    private Button SPButton1;

    @FXML
    private ComboBox<String> SPkind_buy;

    @FXML
    private ComboBox<String> SPname_buy;

    @FXML
    private TextField SPprice_buy;

    @FXML
    private TextField SPsl_buy;

    @FXML
    private ComboBox<String> TC_Chuong;

    @FXML
    private ComboBox<String> TC_KIND;

    @FXML
    private TextField TC_NAME;

    @FXML
    private TextField TC_NOTE;

    @FXML
    private ComboBox<String> TC_SEX;

    @FXML
    private TableView<?> TablePet;

    @FXML
    private TableView<PetView> TablePet1;

    @FXML
    private TableView<SPDaMua> TableSPAlreadyBuy;

    @FXML
    private Button add_button_user;

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
    private TableColumn<?, ?> chuongpet;

    @FXML
    private TableColumn<?, ?> date_damua;

    @FXML
    private TextField folderField;

    @FXML
    private TableColumn<?, ?> gender;

    @FXML
    private Button get_ava;

    @FXML
    private GridPane grid;

    @FXML
    private TableColumn<?, ?> imageview;

    @FXML
    private TableColumn<?, ?> kindpet;

    @FXML
    private TableColumn<pet, String> kindpetn;

    @FXML
    private TableColumn<?, ?> makh_damua;

    @FXML
    private TableColumn<?, ?> mkh;

    @FXML
    private TableColumn<?, ?> mkhn;

    @FXML
    private TableColumn<?, ?> mtc;

    @FXML
    private TableColumn<?, ?> mtcn;

    @FXML
    private TableColumn<?, ?> name;

    @FXML
    private TableColumn<?, ?> namepet;

    @FXML
    private TableColumn<pet, String> namepetn;

    @FXML
    private TableColumn<?, ?> nhanpet;

    @FXML
    private TableColumn<pet, String> nhanpetn;

    @FXML
    private TableColumn<?, ?> notepet;

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
    private AnchorPane page7;

    @FXML
    private AnchorPane page8;

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
    private AnchorPane page_muahang;

    @FXML
    private AnchorPane page_trongho;

    @FXML
    private TableColumn<?, ?> phone;

    @FXML
    private TextField priceTH;

    @FXML
    private TableColumn<?, ?> price_damua;

    @FXML
    private TableColumn<SPDaMua, String> pricetbv_buy;

    @FXML
    private TextField searchKH;

    @FXML
    private TableColumn<?, ?> sexpet;

    @FXML
    private TableColumn<pet, String> sexpetn;

    @FXML
    private TableColumn<SPDaMua, Integer> sltbv_buy;

    @FXML
    private TableColumn<SPDaMua, String> sptbv_buy;

    @FXML
    private TableColumn<SPDaMua, String> sumpricetbv_buy;

    @FXML
    private TableView<?> tableKH;

    @FXML
    private TableView<SPDaMua> tablebuy;

    @FXML
    private TableColumn<SPDaMua, String> ngaycustomerdamua;

    @FXML
    private TableColumn<SPDaMua, String> tencustomerdamua;

    @FXML
    private TableColumn<SPDaMua, String> pricecustomerdamua;

    @FXML
    private TableColumn<SPDaMua, Integer> slcustomerdamua;

    @FXML
    private TableColumn<pet, String> tiendichvu;

    @FXML
    private TableColumn<?, ?> trapet;

    @FXML
    private TableColumn<pet, String> trapetn;

    @FXML
    private TableColumn<?, ?> user_id;

    @FXML
    private TextField user_name;

    @FXML
    private TextField user_name1;

    @FXML
    private TextField user_name11;

    @FXML
    private TextField user_phone;

    @FXML
    private TextField user_phone1;

    @FXML
    private TextField user_phone11;

    @FXML
    private ChoiceBox<?> user_sex;

    @FXML
    private ChoiceBox<String> user_sex1;
    @FXML
    private Button ButtonUpdateUser;
    @FXML
    private GridPane menu_gridpane;
    @FXML
    private Button mua_quaylai;
    @FXML
    private AnchorPane pagemenu;
    @FXML
    private Button giohangbtn;
    @FXML
    private TextField tensp;

    @FXML
    void AddButtonBuy(ActionEvent event) {

    }

    @FXML
    void ButtonAddUser(ActionEvent event) {

    }

    @FXML
    void ButtonDeleteUser(ActionEvent event) {
    }

    @FXML
    void BuyButton(ActionEvent event) {

    }

    @FXML
    void GetNameKH(ActionEvent event) {

    }

    @FXML
    void GetNameSp(ActionEvent event) {

    }

    @FXML
    void GetPriceSp(ActionEvent event) {

    }

    @FXML
    void getSelectedItemToTheField(MouseEvent event) {

    }

    @FXML
    void onClickMuaHang(ActionEvent event) {
        pagemenu.setVisible(true);
        page_trongho.setVisible(false);

    }

    @FXML
    void onClickTrongHo(ActionEvent event) {
        pagemenu.setVisible(false);
        page_trongho.setVisible(true);
    }

    public static void main(String[] args) throws IOException, SQLException {

    }

    public void initialize() throws SQLException, IOException {
        menu();
        khData();
        SetUpSexUser();
        TkSpDaMua();
        TableDichVuUser();
        TableDaMua();
        SoDichVu();

    }

    public void SwitchPage(ActionEvent event) throws SQLException, IOException {
        if (event.getSource() == pageDashboard) {
            TkSpDaMua();
            TableDichVuUser();
            TableDaMua();
            SoDichVu();
            page1.setVisible(true);
            page2.setVisible(false);
            page3.setVisible(false);
            page4.setVisible(false);
            page5.setVisible(false);
            page7.setVisible(false);
            page8.setVisible(false);
        } else if (event.getSource() == pageService) {
            // SetUpIdUserList(KH_buy);
            // SetUpIdUserList(KH_TH);
            // SetUpKindProduct(SPkind_buy);
            SetUpKindAnimal();
            SetUpSexAnimal();
            SetUpChuong();
            SetUpChuongRong();
            SetUpChuongTrong(FunctionClient.FC().GetChuongRong());
            page1.setVisible(false);
            page2.setVisible(false);
            page3.setVisible(true);
            page4.setVisible(false);
            page5.setVisible(false);
            page7.setVisible(false);
            page8.setVisible(false);
        } else if (event.getSource() == mua_quaylai) {
            page_muahang.setVisible(false);
            pagemenu.setVisible(true);
        } else if (event.getSource() == giohangbtn) {
            page_muahang.setVisible(true);
            pagemenu.setVisible(false);
            setUpTableMua();
        } else if (event.getSource() == SPButton) {
            TableDaMua();
            page1.setVisible(false);
            page7.setVisible(true);
        } else if (event.getSource() == SPButton1) {
            TableDichVuUser();
            page1.setVisible(false);
            page8.setVisible(true);
        }
    }

    public void khData() throws SQLException, IOException {
        customer kh = FunctionClient.FC().GetInformationKH(LogController.username1);
        user_name1.setText(kh.getUsername());
        user_phone1.setText(kh.getEmail());
        user_sex1.setValue(kh.getGioitinh());
        user_name11.setText(kh.getName());
        user_phone11.setText(kh.getSdt());
    }

    public void ButtonUpdateUser() throws SQLException, IOException {

        FunctionClient.FC().UpdateInformationKH(user_name1.getText(), user_name11.getText(),
                (String) user_sex1.getValue(), user_phone11.getText());
        khData();

    }

    private String[] sex = { "Nam", "Nữ" };

    public void SetUpSexUser() {
        List<String> lstSex = Arrays.asList(sex); // mảng thành list
        ObservableList<String> lstsexFX = FXCollections.observableArrayList(lstSex);
        user_sex1.setItems(lstsexFX);
    }

    public int tk_sp() throws SQLException, IOException {
        int userId = FunctionClient.FC().GetIdKH(LogController.username1);
        System.out.println(userId);
        return userId;
    }

    // mua hàng
    private ObservableList<product> cardListData;

    public void menu() throws SQLException, IOException {

        try {
            cardListData.clear();
        } catch (Exception e) {
            // TODO: handle exception
        }
        cardListData = FXCollections.observableArrayList(FunctionClient.FC().GetListSp());
        int row = 0;
        int column = 0;
        menu_gridpane.getChildren().clear();
        menu_gridpane.getRowConstraints().clear();
        menu_gridpane.getColumnConstraints().clear();
        for (product q : cardListData) {
            System.out.print(cardListData.size());
            System.out.print(cardListData.toString());
            try {
                FXMLLoader load = new FXMLLoader();
                // load.setLocation(getClass().getResource("qltc\\Resource\\FXML_CSS\\cardProduct.fxml"));
                load.setLocation(getClass().getResource("/qltc/Resource/FXML_CSS/cardProduct.fxml"));
                AnchorPane pane = load.load();
                CardController cardC = load.getController();
                cardC.setData(q);
                if (column == 3) {
                    column = 0;
                    row++;
                }

                menu_gridpane.add(pane, column++, row);
                GridPane.setMargin(pane, new Insets(20));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setUpTableMua() throws SQLException {
        try {
            ArrayList<SPDaMua> lstData = FunctionClient.FC().GioHang(tk_sp());
            ObservableList<SPDaMua> lstData1 = FXCollections.observableArrayList(lstData);
            sptbv_buy.setCellValueFactory(new PropertyValueFactory<>("tensp"));
            sltbv_buy.setCellValueFactory(new PropertyValueFactory<>("soluong"));
            pricetbv_buy.setCellValueFactory(new PropertyValueFactory<>("dongia"));
            sumpricetbv_buy.setCellValueFactory(new PropertyValueFactory<>("chiphi"));
            tablebuy.setItems(lstData1);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public int md;
    int num = -1;

    public void selectMua() {
        if (num >= 0 && tablebuy.getSelectionModel().isSelected(num)) {
            tablebuy.getSelectionModel().clearSelection();
            clearSelect();
        }
        num = tablebuy.getSelectionModel().getSelectedIndex();
        if ((num - 1) < -1)
            return;
        SPDaMua sp = tablebuy.getSelectionModel().getSelectedItem();
        tensp.setText(sp.getTensp());
        SPsl_buy.setText(sp.getSoluong() + "");
        SPprice_buy.setText(String.valueOf(sp.getDongia()));
        md = sp.getMadat();
    }

    public void clearSelect() {
        tensp.setText("");
        SPsl_buy.setText("");
        SPprice_buy.setText("");
    }

    public void xoaMua() throws SQLException, IOException {
        if (md == 0) {
            System.out.println("Vui long chon san pham");
        } else {
            FunctionClient.FC().XoaSp(md);
            setUpTableMua();
        }
    }

    public void muasp() throws SQLException, IOException {
        FunctionClient.FC().MuaSp(tk_sp());
        setUpTableMua();
        clearSelect();
    }
    // Trông hộ

    private String[] kind_animal = { "Chó", "Mèo", "Thỏ" };

    public void SetUpKindAnimal() {
        List<String> lstPro = Arrays.asList(kind_animal);

        ObservableList<String> ListKind = FXCollections.observableArrayList(lstPro);
        TC_KIND.setItems(ListKind);
    }

    private String[] sexanimal = { "Đực", "Cái" };

    public void SetUpSexAnimal() {
        List<String> lstPro = Arrays.asList(sexanimal);

        ObservableList<String> ListKind = FXCollections.observableArrayList(lstPro);
        TC_SEX.setItems(ListKind);
    }

    private String[] Chuong = { "Chuồng 1", "Chuồng 2", "Chuồng 3", "Chuồng 4", "Chuồng 5", "Chuồng 6", "Chuồng 7",
            "Chuồng 8", "Chuồng 9", "Chuồng 10", "Chuồng 11", "Chuồng 12", };

    public void SetUpChuong() {
        List<String> lstPro = Arrays.asList(Chuong);

        ObservableList<String> ListCH = FXCollections.observableArrayList(lstPro);
        TC_Chuong.setItems(ListCH);
    }

    public void ClearFieldCH() {
        TC_KIND.getSelectionModel().clearSelection();
        TC_SEX.getSelectionModel().clearSelection();
        TC_NAME.clear();
        TC_NOTE.clear();
        DateReturn.setValue(null);
        TC_Chuong.getSelectionModel().clearSelection();
        folderField.clear();

    }

    public void GetLinkAvatar() {
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

    public void SetUpChuongTrong(List<Integer> MyList) {
        for (int num : MyList) {
            Button button = (Button) grid.lookup("#b" + num);
            button.setStyle("-fx-background-color: red;");
        }
    }

    public void SetUpChuongRong() throws SQLException, IOException {
        FunctionClient.FC().SetChuong();
    }

    public void TrongHoButton() throws SQLException, NumberFormatException, IOException {
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy");
        LocalDate selectedDate = DateReturn.getValue();
        String chuong = TC_Chuong.getSelectionModel().getSelectedItem().trim().substring(7);
        int chuong_data = Integer.parseInt(chuong);
        if (TC_SEX.getSelectionModel().getSelectedItem().isEmpty()
                || TC_KIND.getSelectionModel().getSelectedItem().isEmpty() || TC_NAME.getText().trim().isEmpty()
                || TC_NOTE.getText().trim().isEmpty() || TC_Chuong.getSelectionModel().getSelectedItem().isEmpty()
                || DateReturn.getValue().toString().isEmpty()) {
            System.out.println("Điền chưa đủ");
        } else {
            if (FunctionClient.FC().InsertChuong(chuong_data) == 1) {
                System.out.println("b" + chuong_data);
                Button button = (Button) grid.lookup("#b" + chuong_data);
                button.setStyle("-fx-background-color: red;");
            } else {
                System.out.println("phòng này đã đầy");
            }
            ;
            FunctionClient.FC().UpdateHD(tk_sp(), Integer.parseInt(priceTH.getText().trim()), ft.format(date));

            FunctionClient.FC().InsertPet(tk_sp(), TC_KIND.getSelectionModel().getSelectedItem().trim(),
                    TC_NAME.getText().trim(), TC_SEX.getSelectionModel().getSelectedItem().trim(),
                    TC_NOTE.getText().trim(), ft.format(date), ft.format(java.sql.Date.valueOf(selectedDate)),
                    chuong_data, folderField.getText().trim(), Integer.parseInt(priceTH.getText().trim()));
            ClearFieldCH();
        }
    }

    // Thống kê
    public void TkSpDaMua() throws IOException, SQLException {
        NotificationSpSell.setText(String.valueOf(FunctionClient.FC().TkKhDamua(tk_sp())));
    }

    public void TableDaMua() throws IOException, SQLException {
        ObservableList<SPDaMua> list = FXCollections.observableArrayList(FunctionClient.FC().GetListKhDaMua(tk_sp()));
        ngaycustomerdamua.setCellValueFactory(new PropertyValueFactory<>("ngay"));
        tencustomerdamua.setCellValueFactory(new PropertyValueFactory<>("tensp"));
        pricecustomerdamua.setCellValueFactory(new PropertyValueFactory<>("dongia"));
        slcustomerdamua.setCellValueFactory(new PropertyValueFactory<>("soluong"));
        TableSPAlreadyBuy.setItems(list);
    }

    public void SoDichVu() throws IOException, SQLException {
        NotificationSpSell1.setText(String.valueOf(FunctionClient.FC().SlUserDichVu(tk_sp())));
    }

    public void TableDichVuUser() throws IOException, SQLException {
        try {
            ArrayList<PetView> lstData = FunctionClient.FC().GetListKhDichVu(tk_sp());
            ObservableList<PetView> lstData1 = FXCollections.observableArrayList(lstData);
            System.out.println(lstData1.size());
            // ObservableList<pet> list =
            // FXCollections.observableArrayList(FunctionClient.FC().GetListKhDichVu(tk_sp()));
            kindpetn.setCellValueFactory(new PropertyValueFactory<>("loai"));
            namepetn.setCellValueFactory(new PropertyValueFactory<>("name"));
            sexpetn.setCellValueFactory(new PropertyValueFactory<>("gioitinh"));
            nhanpetn.setCellValueFactory(new PropertyValueFactory<>("day"));
            trapetn.setCellValueFactory(new PropertyValueFactory<>("ngaytra"));
            tiendichvu.setCellValueFactory(new PropertyValueFactory<>("giatien"));
            TablePet1.setItems(lstData1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}