package ua.edu.j2ee.shoestore.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.edu.j2ee.shoestore.model.ShoeModel;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Repository
public class ShoeModelDaoImpl implements ShoeModelDao {

    @Autowired
    private DataSource dataSource;

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
                    "MODELS (MODELID, NAME, BRAND, PRICE, TYPE, SEASON, COLOR, GENDER, SUPPLIERID) " +
                    "VALUES (MODELID_SEQ.nextval, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, model.getName());
            ps.setString(2, model.getBrand());
            ps.setDouble(3, model.getPrice());
            ps.setString(4, model.getType());
            ps.setString(5, model.getSeason());
            ps.setString(6, model.getColor());
            ps.setString(7, model.getGender());
            ps.setInt(8, model.getSupplierId());
            ps.executeUpdate();
        } catch (SQLException sqlException) {
            throw new RuntimeException("Cant save shoe model");
        }
    }

    @Override
    public void update(ShoeModel model) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("UPDATE MODELS " +
                    "SET PRICE = ? WHERE MODELID = ?");
            ps.setDouble(1, model.getPrice());
            ps.setInt(2, model.getId());
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
        String season = resultSet.getString("season");
        String color = resultSet.getString("color");
        String gender = resultSet.getString("gender");
        int supplierId = resultSet.getInt("supplierid");

        return new ShoeModel(id, name, brand, price, type, season, color, gender, supplierId);
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

    @Override
    public Set<String> getExistingBrands() {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT DISTINCT BRAND FROM MODELS");
            ResultSet rs = ps.executeQuery();

            Set<String> brands = new HashSet<>();
            while (rs.next()) {
                brands.add(extractBrand(rs));
            }
            return brands;
        } catch (SQLException sqlException) {
            throw new RuntimeException("Cant get existing brands");
        }
    }

    @Override
    public double getExistingMinPrice() {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT MIN(PRICE) FROM MODELS");
            ResultSet rs = ps.executeQuery();
            rs.next();
            return extractPrice(rs);
        } catch (SQLException sqlException) {
            throw new RuntimeException("Cant get minimal price");
        }
    }

    @Override
    public double getExistingMaxPrice() {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT MAX(PRICE) FROM MODELS");
            ResultSet rs = ps.executeQuery();
            rs.next();
            return extractPrice(rs);
        } catch (SQLException sqlException) {
            throw new RuntimeException("Cant get max price");
        }
    }

    @Override
    public Set<String> getExistingTypes() {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT DISTINCT TYPE FROM MODELS");
            ResultSet rs = ps.executeQuery();

            Set<String> types = new HashSet<>();
            while (rs.next()) {
                types.add(extractType(rs));
            }
            return types;
        } catch (SQLException sqlException) {
            throw new RuntimeException("Cant get existing types");
        }
    }

    @Override
    public Set<String> getExistingColors() {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT DISTINCT COLOR FROM MODELS");
            ResultSet rs = ps.executeQuery();

            Set<String> colors = new HashSet<>();
            while (rs.next()) {
                colors.add(extractColor(rs));
            }
            return colors;
        } catch (SQLException sqlException) {
            throw new RuntimeException("Cant get existing colors");
        }
    }

    @Override
    public Set<Integer> getExistingSizes() {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT DISTINCT 'SIZE' FROM PRODUCTS");
            ResultSet rs = ps.executeQuery();

            Set<Integer> sizes = new HashSet<>();
            while (rs.next()) {
                sizes.add(extractSize(rs));
            }
            return sizes;
        } catch (SQLException sqlException) {
            throw new RuntimeException("Cant get existing sizes");
        }
    }

    @Override
    public Set<Integer> getExistingSizesByModelId(int id) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT DISTINCT 'SIZE' " +
                    "FROM PRODUCTS WHERE MODELID = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            Set<Integer> sizes = new HashSet<>();
            while (rs.next()) {
                sizes.add(extractSize(rs));
            }
            return sizes;
        } catch (SQLException sqlException) {
            throw new RuntimeException("Cant get existing sizes for model " + id);
        }
    }

    private String extractBrand(ResultSet resultSet) throws SQLException {
        return resultSet.getString("brand");
    }

    private String extractType(ResultSet resultSet) throws SQLException {
        return resultSet.getString("type");
    }

    private String extractColor(ResultSet resultSet) throws SQLException {
        return resultSet.getString("color");
    }

    private Integer extractSize(ResultSet resultSet) throws SQLException {
        return resultSet.getInt("size");
    }

    private double extractPrice(ResultSet resultSet) throws SQLException {
        return resultSet.getDouble("price");
    }
}
