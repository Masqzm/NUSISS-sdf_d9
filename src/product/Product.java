package product;

import java.util.Date;

public class Product {
    private long id;
    String prodName;
    String prodDesc;
    String prodCat;
    Float price;
    Date createDate;

    public Product(long id, String prodName, String prodDesc, String prodCat, Float price, Date createDate) {
        this.id = id;
        this.prodName = prodName;
        this.prodDesc = prodDesc;
        this.prodCat = prodCat;
        this.price = price;
        this.createDate = createDate;
    }
    
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getProdName() {
        return prodName;
    }
    public void setProdName(String prodName) {
        this.prodName = prodName;
    }
    public String getProdDesc() {
        return prodDesc;
    }
    public void setProdDesc(String prodDesc) {
        this.prodDesc = prodDesc;
    }
    public String getProdCat() {
        return prodCat;
    }
    public void setProdCat(String prodCat) {
        this.prodCat = prodCat;
    }
    public Float getPrice() {
        return price;
    }
    public void setPrice(Float price) {
        this.price = price;
    }
    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    
    @Override
    public String toString() {
        return "Product [id=" + id + ", prodName=" + prodName + ", prodDesc=" + prodDesc + ", prodCat=" + prodCat
                + ", price=" + price + ", createDate=" + createDate + "]";
    }
}
