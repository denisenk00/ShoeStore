package ua.edu.j2ee.shoestore.model;

import java.time.LocalDate;
import java.util.List;

public class Order {

    private int id = -1;
    private int userId;
    private double totalPrice;
    private LocalDate orderDate;
    private List<Integer> shoeIdList;

    public Order() {

    }

    public Order(int userId, double totalPrice, LocalDate orderDate, List<Integer> shoeIdList) {
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
        this.shoeIdList = shoeIdList;
    }

    public Order(int id, int userId, double totalPrice, LocalDate orderDate, List<Integer> shoeIdList) {
        this.id = id;
        this.userId = userId;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", totalPrice=" + totalPrice +
                ", orderDate=" + orderDate +
                ", shoeIdList=" + shoeIdList +
                '}';
    }
}
