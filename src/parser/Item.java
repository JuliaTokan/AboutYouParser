package parser;

import java.util.List;

public class Item {
    private String productName;
    private String brand;
    private List<String> colors;
    private String price;
    private String articleID;

    public Item(String productName, String brand, List<String> colors, String price, String articleID) {
        this.productName = productName;
        this.brand = brand;
        this.colors = colors;
        this.price = price;
        this.articleID = articleID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public List<String> getColors() {
        return colors;
    }

    public void setColors(List<String> colors) {
        this.colors = colors;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getArticleID() {
        return articleID;
    }

    public void setArticleID(String articleID) {
        this.articleID = articleID;
    }
}
