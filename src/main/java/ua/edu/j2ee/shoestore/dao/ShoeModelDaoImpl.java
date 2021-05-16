package ua.edu.j2ee.shoestore.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ua.edu.j2ee.shoestore.model.ShoeModel;
import ua.edu.j2ee.shoestore.model.ShoeStatus;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@Repository
public class ShoeModelDaoImpl implements ShoeModelDao {

    private DataSource dataSource;

    @Autowired
    public ShoeModelDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<ShoeModel> getAll() {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM MODELS");
            ResultSet rs = ps.executeQuery();

            List<ShoeModel> models = new LinkedList<>();
            while (rs.next()) {
                models.add(extractShoeModel(rs));
            }
            return models;
        } catch (SQLException sqlException) {
            throw new RuntimeException("Cant get shoe models");
        }
    }

    @Override
    public ShoeModel get(int id) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM MODELS WHERE MODELID = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return extractShoeModel(rs);
        } catch (SQLException sqlException) {
            throw new RuntimeException("Cant get shoe models");
        }
    }

    @Override
    public void save(ShoeModel model) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO " +
                    "MODELS (MODELID, NAME, BRAND, PRICE, TYPE, AMOUNT, SEASON, COLOR, GENDER, SUPPLIERID) " +
                    "VALUES (MODELID_SEQ.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, model.getName());
            ps.setString(2, model.getBrand());
            ps.setDouble(3, model.getPrice());
            ps.setString(4, model.getType());
            ps.setInt(5, model.getAmount());
            ps.setString(6, model.getSeason());
            ps.setString(7, model.getColor());
            ps.setString(8, model.getGender());
            ps.setInt(9, model.getSupplierId());
            ps.executeUpdate();
        } catch (SQLException sqlException) {
            throw new RuntimeException("Cant save shoe model");
        }
    }

    @Override
    public void update(ShoeModel model) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("UPDATE MODELS " +
                    "SET PRICE = ?, AMOUNT = ? WHERE MODELID = ?");
            ps.setDouble(1, model.getPrice());
            ps.setInt(2, model.getAmount());
            ps.setInt(3, model.getId());
            ps.executeUpdate();
        } catch (SQLException sqlException) {
            throw new RuntimeException("Cant update shoe model");
        }
    }

    private ShoeModel extractShoeModel(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("modelid");
        String name = resultSet.getString("name");
        String brand = resultSet.getString("brand");
        double price = resultSet.getDouble("price");
        String type = resultSet.getString("type");
        int amount = resultSet.getInt("amount");
        String season = resultSet.getString("season");
        String color = resultSet.getString("color");
        String gender = resultSet.getString("gender");
        int supplierId = resultSet.getInt("supplierid");

        return new ShoeModel(id, name, brand, price, type, amount, season, color, gender, supplierId);
    }

    @Override
    public List<ShoeModel> getAllByStatus(String status) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM MODELS " +
                    "WHERE MODELID = ANY (SELECT DISTINCT MODELID FROM PRODUCTS WHERE STATUS = ?)");
            ps.setString(1, status);
            ResultSet rs = ps.executeQuery();

            List<ShoeModel> models = new LinkedList<>();
            while (rs.next()) {
                models.add(extractShoeModel(rs));
            }
            return models;
        } catch (SQLException sqlException) {
            throw new RuntimeException("Cant get shoe models");
        }
    }
}
