package ua.edu.j2ee.shoestore.model;

public class Supplier {

    private int id;
    private String company;
    private String city;
    private String country;
    private String address;
    private String phone;
    private String postalCode;

    public Supplier () {

    }

    public Supplier(String company, String city, String country, String address, String phone, String postalCode) {
        this.company = company;
        this.city = city;
        this.country = country;
        this.address = address;
        this.phone = phone;
        this.postalCode = postalCode;
    }

    public Supplier(int id, String company, String city, String country,
                    String address, String phone, String postalCode) {
        this.id = id;
        this.company = company;
        this.city = city;
        this.country = country;
        this.address = address;
        this.phone = phone;
        this.postalCode = postalCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
