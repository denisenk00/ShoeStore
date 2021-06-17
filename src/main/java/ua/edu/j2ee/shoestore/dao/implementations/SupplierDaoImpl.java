package ua.edu.j2ee.shoestore.dao.implementations;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.edu.j2ee.shoestore.dao.interfaces.Dao;
import ua.edu.j2ee.shoestore.exceptions.DaoRuntimeException;
import ua.edu.j2ee.shoestore.model.Supplier;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@Repository
public class SupplierDaoImpl implements Dao<Supplier> {

    private static final Logger LOG = Logger.getLogger(SupplierDaoImpl.class);
    private DataSource dataSource;

    @Autowired
    public SupplierDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

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
            LOG.error("SupplierDao, getAll: cant get all suppliers. SQL error code: " + sqlException.getErrorCode());
            throw new DaoRuntimeException("Невозможно получить всех поставщиков");
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
            LOG.error("SupplierDao, get: cant get supplier " + id + ". SQL error code: " + sqlException.getErrorCode());
            throw new DaoRuntimeException("Невозможно получить поставщика");
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
            LOG.error("SupplierDao, save: cant save supplier. SQL error code: " + sqlException.getErrorCode());
            LOG.error("Supplier info: " + supplier);
            throw new DaoRuntimeException("Невозможно сохранить поставщика");
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
            LOG.error("SupplierDao, update: cant update supplier. SQL error code: " + sqlException.getErrorCode());
            LOG.error("Supplier info: " + supplier);
            throw new DaoRuntimeException("Невозможно обновить поставщика");
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
