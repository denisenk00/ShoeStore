package ua.edu.j2ee.shoestore.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ua.edu.j2ee.shoestore.model.Customer;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@Repository
public class CustomerDaoImpl implements Dao<Customer> {


    private DataSource dataSource;

    @Autowired
    public CustomerDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Customer> getAll() {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM CUSTOMERS");
            ResultSet rs = ps.executeQuery();

            List<Customer> customers = new LinkedList<>();
            while (rs.next()) {
                customers.add(extractCustomer(rs));
            }
            return customers;
        } catch (SQLException sqlException) {
            throw new RuntimeException("Cant get all customers");
        }
    }

    @Override
    public Customer get(int id) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM CUSTOMERS WHERE CUSTOMERID = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return extractCustomer(rs);
        } catch (SQLException sqlException) {
            throw new RuntimeException("Cant get customer " + id);
        }
    }

    @Override
    public void save(Customer customer) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO " +
                    "CUSTOMERS (CUSTOMERID, NAME, SURNAME, PHONE) " +
                    "VALUES (CUSTOMERID_SEQ.nextval, ?, ?, ?)");
            ps.setString(1, customer.getName());
            ps.setString(2, customer.getSurname());
            ps.setString(3, customer.getPhone());
            ps.executeUpdate();
        } catch (SQLException sqlException) {
            throw new RuntimeException("Cant save customer");
        }
    }

    @Override
    public void update(Customer customer) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("UPDATE CUSTOMERS " +
                    "SET NAME = ?, SURNAME = ?, PHONE = ? WHERE CUSTOMERID = ?");
            ps.setString(1, customer.getName());
            ps.setString(2, customer.getSurname());
            ps.setString(3, customer.getPhone());
            ps.setInt(4, customer.getId());
            ps.executeUpdate();
        } catch (SQLException sqlException) {
            throw new RuntimeException("Cant update customer " + customer.getId());
        }
    }

    private Customer extractCustomer(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("customerid");
        String name = resultSet.getString("name");
        String surname =  resultSet.getString("surname");
        String phone = resultSet.getString("phone");

        return new Customer(id, name, surname, phone);
    }
}
