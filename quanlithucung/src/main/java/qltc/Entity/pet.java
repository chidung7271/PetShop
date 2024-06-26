package qltc.Entity;

import javafx.scene.image.ImageView;

public class pet {
    private String kh, id, kind, name, sex, note, day, ngaytra, id_phong;
    private ImageView image;
    private int giatien;
    public pet(String kh, String id, String kind, String name, String sex, String note, String day, String ngaytra,
            String id_phong,ImageView image) {
        this.kh = kh;
        this.id = id;
        this.sex = sex;
        this.note = note;
        this.kind = kind;
        this.name = name;
        this.ngaytra = ngaytra;
        this.id_phong = id_phong;
        this.day = day;
        this.image = image;

    }
    public pet(String loai, String name, String gioitinh, String day, String ngaytra,int giatien) {
        this.kind = loai;
        this.name = name;
        this.sex = gioitinh;
        this.day = day;
        this.ngaytra = ngaytra;
        this.giatien = giatien;
    }
    public int getGiatien() {
        return giatien;
    }
    public void setGiatien(int giatien) {
        this.giatien = giatien;
    }
    public String getId_phong() {
        return id_phong;
    }

    public String getNgaytra() {
        return ngaytra;
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public void setId_phong(String id_phong) {
        this.id_phong = id_phong;
    }

    public void setNgaytra(String ngaytra) {
        this.ngaytra = ngaytra;
    }

    public String getDay() {
        return day;
    }

    public String getId() {
        return id;
    }

    public String getKh() {
        return kh;
    }

    public String getKind() {
        return kind;
    }

    public String getName() {
        return name;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setKh(String kh) {
        this.kh = kh;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNote() {
        return note;
    }

    public String getSex() {
        return sex;
    }

}
