package ua.edu.j2ee.shoestore.dao;

import ua.edu.j2ee.shoestore.model.Order;

import java.util.List;

public interface OrderDao extends Dao<Order> {

    List<Order> getOrdersByUser(int id);

}
