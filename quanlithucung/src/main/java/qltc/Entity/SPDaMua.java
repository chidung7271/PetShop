package qltc.Entity;
public class SPDaMua {
    private String makh, ten, tensp, chiphi, ngay,dongia;
    public SPDaMua(String ngay ,String tensp, String dongia, int soluong) {
        this.ngay = ngay;
        this.tensp = tensp;
        this.dongia = dongia;
        this.soluong = soluong;
    }
    int madat,soluong;

    public String getDongia() {
        return dongia;
    }
    public void setDongia(String dongia) {
        this.dongia = dongia;
    }
    public int getMadat() {
        return madat;
    }
    public void setMadat(int madat) {
        this.madat = madat;
    }

    public SPDaMua(String makh, String ten, String tensp, String chiphi, String ngay) {
        this.makh = makh;
        this.ten = ten;
        this.tensp = tensp;
        this.chiphi = chiphi;
        this.ngay = ngay;
    }
    public SPDaMua(int madat, String tensp,int soluong,String dongia, String chiphi) {
        this.chiphi=chiphi;
        this.dongia=dongia;
        this.tensp = tensp;
        this.madat=madat;
        this.soluong=soluong;
    }
    public SPDaMua(String makh, String ten, String tensp, String chiphi) {
        this.makh = makh;
        this.ten = ten;
        this.tensp = tensp;
        this.chiphi = chiphi;
    }
    public String getChiphi() {
        return chiphi;
    }
    public String getMakh() {
        return makh;
    }
    public String getNgay() {
        return ngay;
    }
    public String getTen() {
        return ten;
    }
    public String getTensp() {
        return tensp;
    }

    public void setChiphi(String chiphi) {
        this.chiphi = chiphi;
    }

    public void setMakh(String makh) {
        this.makh = makh;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }
    public int getSoluong() {
        return soluong;
    }
    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
}
