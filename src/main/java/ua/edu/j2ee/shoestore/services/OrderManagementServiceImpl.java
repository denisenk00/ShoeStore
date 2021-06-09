package ua.edu.j2ee.shoestore.services;

import ua.edu.j2ee.shoestore.model.Order;
import ua.edu.j2ee.shoestore.model.ProductCart;
import ua.edu.j2ee.shoestore.model.User;

import java.util.List;

public class OrderManagementServiceImpl implements OrderManagementService {

    @Override
    public void createOrder(ProductCart cart) {
        Order order = new Order();

    }

    @Override
    public List<Order> getOrderHistory(User user) {
        return null;
    }
}
