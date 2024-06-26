package qltc.Entity;

public class buySP {
    private String sp, sl, tonggiatri, price, id_sp, id_kh, name;
    private int hd, soluong, dongia, total;

    public buySP(int hd, String name, int sl, int price, int total) {
        this.hd = hd;
        this.name = name;
        this.soluong = sl;
        this.dongia = price;
        this.total = total;
    }

    public buySP(String id_sp, String sp, String sl, String price, String tonggiatri) {
        this.sp = sp;
        this.sl = sl;
        this.price = price;
        this.tonggiatri = tonggiatri;
        // this.id_kh = id_kh;
        this.id_sp = id_sp;
    }

    public void setDongia(int dongia) {
        this.dongia = dongia;
    }

    public void setHd(int hd) {
        this.hd = hd;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getHd() {
        return hd;
    }

    public int getDongia() {
        return dongia;
    }

    public String getName() {
        return name;
    }

    public int getSoluong() {
        return soluong;
    }

    public int getTotal() {
        return total;
    }

    public String getId_kh() {
        return id_kh;
    }

    public void setId_kh(String id_kh) {
        this.id_kh = id_kh;
    }

    public String getId_sp() {
        return id_sp;
    }

    public void setId_sp(String id_sp) {
        this.id_sp = id_sp;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrice() {
        return price;
    }

    public String getSl() {
        return sl;
    }

    public String getSp() {
        return sp;
    }

    public String getTonggiatri() {
        return tonggiatri;
    }

    public void setSl(String sl) {
        this.sl = sl;
    }

    public void setSp(String sp) {
        this.sp = sp;
    }

    public void setTonggiatri(String tonggiatri) {
        this.tonggiatri = tonggiatri;
    }
}
