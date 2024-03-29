package ua.edu.j2ee.shoestore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.edu.j2ee.shoestore.dao.interfaces.OrderDao;
import ua.edu.j2ee.shoestore.dao.interfaces.ShoeDao;
import ua.edu.j2ee.shoestore.dao.interfaces.ShoeModelDao;
import ua.edu.j2ee.shoestore.model.*;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class OrderManagementServiceImpl implements OrderManagementService {

    @Autowired
    OrderDao orderDao;

    @Autowired
    ShoeDao shoeDao;

    @Autowired
    ShoeModelDao shoeModelDao;

    @Override
    public void createOrder(int userId, ProductCart cart) {
        List<Integer> shoeList = new LinkedList<>();
        double totalPrice = 0;
        for (Map.Entry<Integer, Integer> entry : cart.getShoeCart().entrySet()) {
            Shoe shoe = shoeDao.getAnyExistingByParams(entry.getKey(), entry.getValue());
            shoeList.add(shoe.getId());
            shoe.setStatus("BOOKED");
            shoeDao.update(shoe);
            totalPrice += shoeModelDao.get(shoe.getModelId()).getPrice();
        }
        orderDao.save(new Order(-1, userId, totalPrice, LocalDate.now(), shoeList));
    }

    @Override
    public List<Order> getOrderHistory(int userId) {
        return orderDao.getOrdersByUser(userId);
    }
}
