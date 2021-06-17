package ua.edu.j2ee.shoestore.dao.implementations;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.edu.j2ee.shoestore.dao.interfaces.OrderDao;
import ua.edu.j2ee.shoestore.model.Order;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {

    private static final Logger LOG = Logger.getLogger(OrderDaoImpl.class);

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
            LOG.error("OrderDao, getAll: cant get orders. SQL error code: " + sqlException.getErrorCode());
            throw new DaoRuntimeException("Невозможно достать все заказы");
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
            LOG.error("OrderDao, get " + id + ": cant get order. SQL error code: " + sqlException.getErrorCode());
            throw new DaoRuntimeException("Невозможно получить заказ #" + id);
        }
    }

    @Override
    public void save(Order order) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps;
            ps = connection.prepareStatement("INSERT INTO " +
                    "ORDERS (ORDERID, USERID, PRICE, ORDERDATE)  " +
                    "VALUES (ORDERID_SEQ.nextval, ?, ?, ?)");
            ps.setInt(1, order.getUserId());
            ps.setDouble(2, order.getTotalPrice());
            ps.setDate(3, Date.valueOf(order.getOrderDate()));
            ps.executeUpdate();

            ps = connection.prepareStatement("INSERT INTO " +
                    "ORDERDETAILS (ORDERID, PRODUCTID) VALUES (ORDERID_SEQ.currval, ?)");
            for (int shoeId : order.getShoeIdList()) {
                ps.setInt(1, shoeId);
                ps.executeUpdate();
            }
        } catch (SQLException sqlException) {
            LOG.error("OrderDao, save: cant save order. SQL error code: " + sqlException.getErrorCode());
            LOG.error("Order info: " + order);
            throw new DaoRuntimeException("Невозможно сохранить заказ");
        }
    }

    @Override
    public void update(Order order) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Order> getOrdersByUser(int id) {
        List<Order> orders = new LinkedList<>();
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM ORDERS WHERE USERID = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                orders.add(extractOrder(connection, rs));
            }

            return orders;
        } catch (SQLException sqlException) {
            LOG.error("OrderDao, getOrdersByUser: cant get orders by user. SQL error code: " +
                    sqlException.getErrorCode());
            LOG.error("User id: " + id);
            throw new DaoRuntimeException("Невозможно получить историю заказов");
        }
    }

    private Order extractOrder(Connection connection, ResultSet orderResultSet) throws SQLException {
        int id = orderResultSet.getInt("orderid");
        int userId = orderResultSet.getInt("userid");
        double totalPrice = orderResultSet.getDouble("price");
        LocalDate orderDate = orderResultSet.getDate("orderdate").toLocalDate();

        List<Integer> shoeIdList = new LinkedList<>();
        PreparedStatement ps = connection.prepareStatement("SELECT PRODUCTID FROM ORDERDETAILS WHERE ORDERID = ?");
        ps.setInt(1, id);
        ResultSet detailsResultSet = ps.executeQuery();
        while (detailsResultSet.next()) {
            shoeIdList.add(detailsResultSet.getInt("productid"));
        }

        return new Order(id, userId, totalPrice, orderDate, shoeIdList);
    }
}
