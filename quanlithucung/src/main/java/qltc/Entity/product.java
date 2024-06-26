package qltc.Entity;

public class product {

    private String id, price, describe, origin, kind, name, sl;
   private byte[] image;

    public product(String id, String kind, String name, String sl, String price, String origin, String describe) {
        this.id = id;
        this.describe = describe;
        this.kind = kind;
        this.name = name;
        this.price = price;
        this.origin = origin;
        this.sl = sl;
    }
    public product(String id, String kind, String name, String sl, String price, String origin, String describe,byte[] image) {
        this.id = id;
        this.describe = describe;
        this.kind = kind;
        this.name = name;
        this.price = price;
        this.origin = origin;
        this.sl = sl;
        this.image=image;
    }
    public String getSl() {
        return sl;
    }

    public void setSl(String sl) {
        this.sl = sl;
    }

    public String getDescribe() {
        return describe;
    }

    public String getId() {
        return id;
    }

    public String getKind() {
        return kind;
    }

    public String getName() {
        return name;
    }

    public String getOrigin() {
        return origin;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

}
