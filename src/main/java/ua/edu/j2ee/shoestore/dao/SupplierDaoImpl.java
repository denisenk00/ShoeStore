package ua.edu.j2ee.shoestore.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ua.edu.j2ee.shoestore.model.Supplier;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class SupplierDaoImpl implements Dao<Supplier> {

    @Autowired
    private DataSource dataSource;

    @Override
    public List<Supplier> getAll() {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM SUPPLIERS");
            ResultSet rs = ps.executeQuery();

            List<Supplier> suppliers = new LinkedList<>();
            while (rs.next()) {
                suppliers.add(extractSupplier(rs));
            }
            return suppliers;
        } catch (SQLException sqlException) {
            throw new RuntimeException("Cant get all suppliers");
        }
    }

    @Override
    public Supplier get(int id) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM SUPPLIERS WHERE SUPPLIERID = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return extractSupplier(rs);
        } catch (SQLException sqlException) {
            throw new RuntimeException("Cant get supplier " + id);
        }
    }

    @Override
    public void save(Supplier supplier) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO " +
                    "SUPPLIERS (SUPPLIERID, COMPANY, CITY, COUNTRY, ADDRESS, PHONE, POSTALCODE) " +
                    "VALUES (SUPPLIERID_SEQ.nextval, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, supplier.getCompany());
            ps.setString(2, supplier.getCity());
            ps.setString(3, supplier.getCountry());
            ps.setString(4, supplier.getAddress());
            ps.setString(5, supplier.getPhone());
            ps.setString(6, supplier.getPostalCode());
            ps.executeUpdate();
        } catch (SQLException sqlException) {
            throw new RuntimeException("Cant save supplier");
        }
    }

    @Override
    public void update(Supplier supplier) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("UPDATE SUPPLIERS SET COMPANY = ?, CITY = ?, " +
                    "COUNTRY = ?, ADDRESS = ?, PHONE = ?, POSTALCODE = ? WHERE SUPPLIERID = ?");
            ps.setString(1, supplier.getCompany());
            ps.setString(2, supplier.getCity());
            ps.setString(3, supplier.getCountry());
            ps.setString(4, supplier.getAddress());
            ps.setString(5, supplier.getPhone());
            ps.setString(6, supplier.getPostalCode());
            ps.setInt(7, supplier.getId());
            ps.executeUpdate();
        } catch (SQLException sqlException) {
            throw new RuntimeException("Cant update supplier");
        }
    }

    private Supplier extractSupplier(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("supplierid");
        String company = resultSet.getString("company");
        String city = resultSet.getString("city");
        String country = resultSet.getString("country");
        String address = resultSet.getString("address");
        String phone = resultSet.getString("phone");
        String postalCode = resultSet.getString("postalcode");

        return new Supplier(id, company, city, country, address, phone, postalCode);
    }
}
