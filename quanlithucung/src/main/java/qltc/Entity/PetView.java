package qltc.Entity;

public class PetView {
    private String loai, name, gioitinh, day, ngaytra;
    private int giatien;
    public PetView(String loai, String name, String gioitinh, String day, String ngaytra, int giatien) {
        this.loai = loai;
        this.name = name;
        this.gioitinh = gioitinh;
        this.day = day;
        this.ngaytra = ngaytra;
        this.giatien = giatien;
    }
    public String getLoai() {
        return loai;
    }
    public void setLoai(String loai) {
        this.loai = loai;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getGioitinh() {
        return gioitinh;
    }
    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }
    public String getDay() {
        return day;
    }
    public void setDay(String day) {
        this.day = day;
    }
    public String getNgaytra() {
        return ngaytra;
    }
    public void setNgaytra(String ngaytra) {
        this.ngaytra = ngaytra;
    }
    public int getGiatien() {
        return giatien;
    }
    public void setGiatien(int giatien) {
        this.giatien = giatien;
    }
    
    }
    
