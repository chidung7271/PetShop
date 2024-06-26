package qltc.BussinessLogicLayer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import com.google.gson.Gson;

import javafx.collections.ObservableList;
import qltc.Entity.PetView;
import qltc.Entity.SPDaMua;
import qltc.Entity.customer;
import qltc.Entity.product;

public class Server {
    private static final int port = 12345;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept(); //// Chấp nhận kết nối từ client
                new ClientHandler(clientSocket).start(); //// Tạo và khởi chạy thread mới
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}

class ClientHandler extends Thread {
    private Socket clientSocket;
    private PrintWriter out_to_client;
    private BufferedReader in_to_client;

    public ClientHandler(Socket socket) throws IOException {
        this.clientSocket = socket;
        out_to_client = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream(), "UTF-8"), true);
        in_to_client = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), "UTF-8"));

    }

    public void run() {
        String inputLine;
        try {
            while ((inputLine = in_to_client.readLine()) != null) { //// (readline sẽ chờ cho đến khi có dữ liệu mới chứ
                                                                    //// không phải chạy liên tục)
                String[] parts = inputLine.split("//");
                String key = parts[0];
                if (key.equals("Login")) {
                    String username = parts[1];
                    String password = parts[2];
                    boolean data = FunctionServer.FS().RequestCheckExist(username, password);
                    out_to_client.println(data);
                    System.out.println("Server send response: " + key + " " + data);
                } else if (key.equals("UpdateKH")) {
                    String username = parts[1];
                    String hoten = parts[2];
                    String sex = parts[3];
                    String sdt = parts[4];
                    boolean data = FunctionServer.FS().UpdateInformationKH(username, hoten, sex, sdt);
                    out_to_client.println(data);
                    System.out.println("Server send response: " + key + " " + data);
                } else if (key.equals("GetKH")) {
                    String username = parts[1];
                    customer kh = FunctionServer.FS().GetInformationKH(username);
                    out_to_client.println(kh.getName() + "//" + kh.getSdt() + "//" + kh.getGioitinh() + "//"
                            + kh.getUsername() + "//" + kh.getEmail());
                    System.out.println("Server send response: " + key);
                } else if (key.equals("GetIdKH")) {
                    String username = parts[1];
                    int data = FunctionServer.FS().GetIdKH(username);
                    out_to_client.println(data);
                    System.out.println("Server send response: " + key);
                } else if (key.equals("GetListSP")) {

                    ObservableList<product> list = FunctionServer.FS().GetListSp();
                    ArrayList<product> arrayList = new ArrayList<>(list);
                    Gson gson = new Gson();
                    String json = gson.toJson(arrayList);
                    out_to_client.println(json);
                    System.out.println("Server send response: " + key);
                } else if (key.equals("GioHang")) {
                    int id = Integer.parseInt(parts[1]);
                    ObservableList<SPDaMua> list = FunctionServer.FS().GioHang(id);
                    ArrayList<SPDaMua> arrayList = new ArrayList<>(list);
                    Gson gson = new Gson();
                    String json = gson.toJson(arrayList);
                    out_to_client.println(json);
                    System.out.println("Server send response: " + key);
                } else if (key.equals("XoaSp")) {
                    int id_select = Integer.parseInt(parts[1]);
                    boolean data = FunctionServer.FS().XoaSp(id_select);
                    out_to_client.println(data);
                    System.out.println("Server send response: " + key + " " + data);
                } else if (key.equals("MuaSp")) {
                    int id = Integer.parseInt(parts[1]);
                    boolean data = FunctionServer.FS().MuaSp(id);
                    out_to_client.println(data);
                    System.out.println("Server send response: " + key + " " + data);
                } else if (key.equals("GetChuong")) {
                    List<Integer> list = FunctionServer.FS().GetChuongRong();
                    Gson gson = new Gson();
                    String json = gson.toJson(list);
                    out_to_client.println(json);
                    System.out.println("Server send response: " + key);
                } else if (key.equals("SetChuong")) {
                    FunctionServer.FS().SetUpChuongRong();
                    System.out.println("Server send response: " + key);
                } else if (key.equals("InsertChuong")) {
                    int chuong = Integer.parseInt(parts[1]);
                    int data = FunctionServer.FS().InsertChuong(chuong);
                    out_to_client.println(data);
                    System.out.println("Server send response: " + key);
                } else if (key.equals("InsertPet")) {
                    int id_kh = Integer.parseInt(parts[1]);
                    String loai = parts[2];
                    String ten = parts[3];
                    String gioitinh = parts[4];
                    String note = parts[5];
                    String nhan = parts[6];
                    String tra = parts[7];
                    int chuong = Integer.parseInt(parts[8]);
                    String image = parts[9];
                    int giatien = Integer.parseInt(parts[10]);
                    FunctionServer.FS().InsertPet(id_kh, loai, ten, gioitinh, note, nhan, tra, chuong, image, giatien);
                    System.out.println("Server send response: " + key);
                } else if (key.equals("UpdateHD")) {
                    int id = Integer.parseInt(parts[1]);
                    int tt = Integer.parseInt(parts[2]);
                    String date = parts[3];
                    boolean data = FunctionServer.FS().UpdateHD(id, tt, date);
                    out_to_client.println(data);
                    System.out.println("Server send response: " + key + " " + data);
                } else if (key.equals("TkKhDamua")) {
                    int id = Integer.parseInt(parts[1]);
                    int data = FunctionServer.FS().TkKhDamua(id);
                    out_to_client.println(data);
                    System.out.println("Server send response: " + key + " " + data);
                } else if (key.equals("GetListKhDaMua")) {
                    int id = Integer.parseInt(parts[1]);
                    ObservableList<SPDaMua> list = FunctionServer.FS().GetListKhDaMua(id);
                    System.out.println(list);
                    Gson gson = new Gson();
                    String json = gson.toJson(list);
                    out_to_client.println(json);
                    System.out.println("Server send response: " + key);
                } else if (key.equals("SlUserDichVu")) {
                    int id = Integer.parseInt(parts[1]);
                    int data = FunctionServer.FS().SlUserDichVu(id);
                    out_to_client.println(data);
                    System.out.println("Server send response: " + key + " " + data);
                } else if (key.equals("ListKHSuDungDichVu")) {
                    int id = Integer.parseInt(parts[1]);
                    ObservableList<PetView> list = FunctionServer.FS().ListKHSuDungDichVu(id);
                    Gson gson = new Gson();
                    String json = gson.toJson(list);
                    out_to_client.println(json);
                    System.out.println("Server send response: " + key);
                } else if (key.equals("TableDaMuaAdmin")) {
                    ObservableList<SPDaMua> list = FunctionServer.FS().DanhSachTatCaDaMua();
                    Gson gson = new Gson();
                    String json = gson.toJson(list);
                    out_to_client.println(json);
                    System.out.println("Server send response: " + key);
                }
                // else if(key.equals("InsertSP")){
                // int id_sp = Integer.parseInt(parts[1]);
                // int id_kh = Integer.parseInt(parts[2]);
                // String date = parts[3];
                // int gia = Integer.parseInt(parts[4]);
                // int sl = Integer.parseInt(parts[5]);
                // boolean data = FunctionServer.FS().InsertSP(id_sp,id_kh,date,gia,sl);
                // out_to_client.println(data);
                // System.out.println("Server send response: " + key + " " + data);
                // }
                else if (key.equals("GetListSpAdmin")) {
                    ObservableList<product> list = FunctionServer.FS().GetListSpAdmin();
                    ArrayList<product> arrayList = new ArrayList<>(list);
                    Gson gson = new Gson();
                    String json = gson.toJson(arrayList);
                    out_to_client.println(json);
                    System.out.println("Server send response: " + key);
                } else if (key.equals("CheckUsername")) {
                    String username = parts[1];
                    boolean data = FunctionServer.FS().UsernameExist(username);
                    out_to_client.println(data);
                    System.out.println("Server response: " + data);
                } else if (key.equals("SignUp")) {
                    System.out.println("lammmmmmmmmmm");
                    String username = parts[1];
                    String password = parts[2];
                    String email = parts[3];
                    String hash = BCrypt.hashpw(password, BCrypt.gensalt(12));
                    System.out.println("Server response: " + hash);
                    FunctionServer.FS().SignUpUser(username, hash, email);
                    out_to_client.println("");
                    System.out.println("Server response: " + hash);
                } else if (key.equals("CheckEmail")) {
                    String email = parts[1];
                    boolean data = FunctionServer.FS().CheckEmailExist(email);
                    out_to_client.println(data);
                    System.out.println("Server response: " + data);
                } else if (key.equals("ChangePass")) {
                    String email = parts[1];
                    String password = parts[2];
                    FunctionServer.FS().ChangePass(email, password);
                    out_to_client.println("");
                    System.out.println("Server response: OK");
                } else if (key.equals("LoginAdmin")) {
                    String username = parts[1];
                    String password = parts[2];
                    boolean data = FunctionServer.FS().LogAdmin(username, password);
                    out_to_client.println(data);
                    System.out.println("Server response: " + data);
                } else if (key.equals("SendEmail")) {
                    String to = parts[1];
                    String otp = parts[2];
                    FunctionServer.FS().sendEmail(to, otp);
                    out_to_client.println("");
                    System.out.println("Server response: OK");
                }

                else if (key.equals("CheckCustomerExist")) {
                    String name = parts[1];
                    String sex = parts[2];
                    String phone = parts[3];
                    boolean data = FunctionServer.FS().CheckCustomerExist(name, sex, phone);
                    out_to_client.println(data);
                    System.out.println("Server response: " + key + " " + data);
                } else if (key.equals("UpdateKh")) {
                    int id = Integer.parseInt(parts[1]);
                    String name = parts[2];
                    String sex = parts[3];
                    String phone = parts[4];
                    FunctionServer.FS().UpdateKh(id, name, sex, phone);
                    ;
                    out_to_client.println("");
                    System.out.println("Server response: " + key + " OK");
                }

                else if (key.equals("InsertKh")) {
                    String name = parts[1];
                    String sex = parts[2];
                    String phone = parts[3];
                    FunctionServer.FS().InsertKh(name, sex, phone);
                    out_to_client.println("");
                    System.out.println("Server response: " + key + " OK");

                } else if (key.equals("DeleteKh")) {
                    int id = Integer.parseInt(parts[1]);

                    FunctionServer.FS().DeleteKh(id);
                    ;
                    out_to_client.println("");
                    System.out.println("Server response: " + key + " OK");
                } else if (key.equals("CheckProductExist")) {
                    String loai = parts[1];
                    String name = parts[2];
                    int sl = Integer.parseInt(parts[3]);
                    int gia = Integer.parseInt(parts[4]);
                    String org = parts[5];
                    String mota = parts[6];
                    boolean data = FunctionServer.FS().CheckProductExist(loai, name, sl, gia, org, mota);
                    out_to_client.println(data);
                    System.out.println("Server response: " + key + " " + data);
                } else if (key.equals("InsertSanpham")) {
                    String loai = parts[1];
                    String name = parts[2];
                    int sl = Integer.parseInt(parts[3]);
                    int gia = Integer.parseInt(parts[4]);
                    String org = parts[5];
                    String mota = parts[6];
                    String image = parts[7];
                    FunctionServer.FS().InsertSanpham(loai, name, sl, gia, org, mota, image);
                    out_to_client.println("");
                    System.out.println("Server response: " + key + " OK");
                } else if (key.equals("UpdateSanPham")) {
                    int id = Integer.parseInt(parts[1]);
                    String loai = parts[2];
                    String name = parts[3];
                    int sl = Integer.parseInt(parts[4]);
                    int gia = Integer.parseInt(parts[5]);
                    String org = parts[6];
                    String mota = parts[7];
                    String image = parts[8];
                    FunctionServer.FS().UpdateSanPham(id, loai, name, sl, gia, org, mota, image);
                    out_to_client.println("");
                    System.out.println("Server response: " + key + " OK");
                } else {
                    System.out.println("Invalid key: " + key);
                }
            }
        } catch (IOException | SQLException e) {
            System.err.println("Error reading from client: " + e.getMessage());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            closeEverything();
        }
    }

    private void closeEverything() {
        System.out.println("Closing connection to client");
        try {
            if (out_to_client != null)
                out_to_client.close();
            if (in_to_client != null)
                in_to_client.close();
            if (clientSocket != null)
                clientSocket.close();
        } catch (IOException e) {
            System.err.println("Error closing streams or socket: " + e.getMessage());
        }
    }
}
