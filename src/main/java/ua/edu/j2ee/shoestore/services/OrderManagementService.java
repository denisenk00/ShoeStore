package ua.edu.j2ee.shoestore.services;

import ua.edu.j2ee.shoestore.model.Order;
import ua.edu.j2ee.shoestore.model.ProductCart;
import ua.edu.j2ee.shoestore.model.User;

import java.util.List;

public interface OrderManagementService {

    void createOrder(ProductCart cart);

    List<Order> getOrderHistory(User user);

}