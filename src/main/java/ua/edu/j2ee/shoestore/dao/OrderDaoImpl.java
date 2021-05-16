package ua.edu.j2ee.shoestore.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ua.edu.j2ee.shoestore.model.Customer;
import ua.edu.j2ee.shoestore.model.Order;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {

    private DataSource dataSource;

    @Autowired
    public OrderDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Order> getAll() {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM ORDERS");
            ResultSet rs = ps.executeQuery();

            List<Order> orders = new LinkedList<>();
            while (rs.next()) {
                orders.add(extractOrder(connection, rs));
            }

            return orders;
        } catch (SQLException sqlException) {
            throw new RuntimeException("Cant get all orders");
        }
    }

    @Override
    public Order get(int id) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM ORDERS WHERE ORDERID = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return extractOrder(connection, rs);
        } catch (SQLException sqlException) {
            throw new RuntimeException("Cant get order with ID " + id);
        }
    }

    @Override
    public void save(Order order) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement orderStatement;
            PreparedStatement priceStatement;
            orderStatement = connection.prepareStatement("INSERT INTO " +
                    "ORDERS (ORDERID, CUSTOMERID, PRICE, ORDERDATE)  " +
                    "VALUES (ORDERID_SEQ.nextval, ?, ?, ?)");
            orderStatement.setInt(1, order.getCustomerId());
            orderStatement.setDouble(2, order.getTotalPrice());
            orderStatement.setDate(3, Date.valueOf(order.getOrderDate()));
            orderStatement.executeUpdate();

            orderStatement = connection.prepareStatement("INSERT INTO " +
                    "ORDERDETAILS (ORDERID, PRODUCTID, PRICE) VALUES (?, ?, ?)");
            priceStatement = connection.prepareStatement("SELECT PRICE " +
                    "FROM MODELS, PRODUCTS WHERE MODELS.MODELID = PRODUCTS.MODELID AND PRODUCTS.PRODUCTID = ?");
            orderStatement.setInt(1, order.getId());
            for (int shoeId : order.getShoeIdList()) {
                orderStatement.setInt(2, shoeId);
                priceStatement.setInt(1, shoeId);

                ResultSet priceSet = priceStatement.executeQuery();
                priceSet.next();
                double price = priceSet.getDouble("price");

                orderStatement.setDouble(3, price);
                orderStatement.executeUpdate();
            }
        } catch (SQLException sqlException) {
            throw new RuntimeException("Cant save order " + order.getId());
        }
    }

    @Override
    public void update(Order order) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Order> getOrdersByCustomer(Customer customer) {
        List<Order> orders = new LinkedList<>();
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM ORDERS WHERE CUSTOMERID = ?");
            ps.setInt(1, customer.getId());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                orders.add(extractOrder(connection, rs));
            }

            return orders;
        } catch (SQLException sqlException) {
            throw new RuntimeException("Cant get order history for " +
                    customer.getSurname() + ' ' + customer.getName());
        }
    }

    private Order extractOrder(Connection connection, ResultSet orderResultSet) throws SQLException {
        int id = orderResultSet.getInt("orderid");
        int customerId = orderResultSet.getInt("customerid");
        double totalPrice = orderResultSet.getDouble("price");
        LocalDate orderDate = orderResultSet.getDate("orderdate").toLocalDate();

        List<Integer> shoeIdList = new LinkedList<>();
        PreparedStatement ps = connection.prepareStatement("SELECT PRODUCTID FROM ORDERDETAILS WHERE ORDERID = ?");
        ps.setInt(1, id);
        ResultSet detailsResultSet = ps.executeQuery();
        while (detailsResultSet.next()) {
            shoeIdList.add(detailsResultSet.getInt("productid"));
        }

        return new Order(id, customerId, totalPrice, orderDate, shoeIdList);
    }
}
