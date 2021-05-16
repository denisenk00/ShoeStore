package ua.edu.j2ee.shoestore.model;

import java.time.LocalDate;
import java.util.List;

public class Order {

    private int id = -1;
    private int customerId;
    private double totalPrice;
    private LocalDate orderDate;
    private List<Integer> shoeIdList;

    public Order() {

    }

    public Order(int id, int customerId, double totalPrice, LocalDate orderDate, List<Integer> shoeIdList) {
        this.id = id;
        this.customerId = customerId;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
        this.shoeIdList = shoeIdList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public List<Integer> getShoeIdList() {
        return shoeIdList;
    }

    public void setShoeIdList(List<Integer> shoeIdList) {
        this.shoeIdList = shoeIdList;
    }
}
