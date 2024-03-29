package ua.edu.j2ee.shoestore.model;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;


@JacksonXmlRootElement(localName = "shoeModel")
public class ShoeModel {

    private int id;
    private String name;
    private String brand;
    private double price;
    private String type;
    private String season;
    private String color;
    private String gender;
    private int supplierId;

    public ShoeModel() {

    }

    public ShoeModel(String name, String brand, double price, String type, String season, String color, String gender, int supplierId) {
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.type = type;
        this.season = season;
        this.color = color;
        this.gender = gender;
        this.supplierId = supplierId;
    }

    public ShoeModel(int id, String name, String brand, double price, String type,
                     String season, String color, String gender, int supplierId) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.type = type;
        this.season = season;
        this.color = color;
        this.gender = gender;
        this.supplierId = supplierId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    @Override
    public String toString() {
        return "ShoeModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", price=" + price +
                ", type='" + type + '\'' +
                ", season='" + season + '\'' +
                ", color='" + color + '\'' +
                ", gender='" + gender + '\'' +
                ", supplierId=" + supplierId +
                '}';
    }
}
