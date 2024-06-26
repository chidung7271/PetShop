package qltc.Entity;

public class customer {
    public customer(String name, String sdt, String gioitinh, String username, String email) {
        this.name = name;
        this.sdt = sdt;
        this.gioitinh = gioitinh;
        this.username = username;
        this.email = email;
    }

    private String id;
    private String name, sdt, gioitinh,password,username,email;


    public customer(String id, String name, String gioitinh, String sdt) {
        this.id = id;
        this.name = name;
        this.sdt = sdt;
        this.gioitinh = gioitinh;

    }

    public customer(String id, String name, String sdt, String gioitinh, String password, String username,
            String email) {
        this.id = id;
        this.name = name;
        this.sdt = sdt;
        this.gioitinh = gioitinh;
        this.password = password;
        this.username = username;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public String getSdt() {
        return sdt;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
