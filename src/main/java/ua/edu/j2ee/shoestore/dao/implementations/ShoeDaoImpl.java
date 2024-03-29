package ua.edu.j2ee.shoestore.dao.implementations;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.edu.j2ee.shoestore.dao.interfaces.ShoeDao;
import ua.edu.j2ee.shoestore.exceptions.DaoRuntimeException;
import ua.edu.j2ee.shoestore.model.Shoe;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@Repository
public class ShoeDaoImpl implements ShoeDao {

    private static final Logger LOG = Logger.getLogger(ShoeDaoImpl.class);
    private DataSource dataSource;

    @Autowired
    public ShoeDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Shoe> getAll() {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM PRODUCTS");
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Shoe> shoes = new LinkedList<>();
            while (resultSet.next()) {
                shoes.add(extractShoe(resultSet));
            }
            return shoes;
        } catch (SQLException sqlException) {
            LOG.error("ShoeDao, getAll: cant get shoes. SQL error code: " + sqlException.getErrorCode());
            throw new DaoRuntimeException("Невозможно получить всю обувь");
        }
    }

    @Override
    public Shoe get(int id) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM PRODUCTS " +
                    "WHERE PRODUCTID = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return extractShoe(resultSet);
        } catch (SQLException sqlException) {
            LOG.error("ShoeDao, get" + id + ": cant get shoes. SQL error code: " + sqlException.getErrorCode());
            throw new DaoRuntimeException("Невозможно получить обувь " + id);
        }
    }

    @Override
    public void save(Shoe shoe) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO " +
                    "PRODUCTS (PRODUCTID, MODELID, \"SIZE\", STATUS) " +
                    "VALUES (PRODUCTID_SEQ.nextval, ?, ?, ?)");
            ps.setInt(1, shoe.getModelId());
            ps.setInt(2, shoe.getSize());
            ps.setString(3, shoe.getStatus());
            ps.executeUpdate();
        } catch (SQLException sqlException) {
            LOG.error("ShoeDao, save: cant save shoe. SQL error code: " + sqlException.getErrorCode());
            LOG.error("Shoe info: " + shoe);
            throw new DaoRuntimeException("Невозможно сохранить обувь");
        }
    }

    @Override
    public void update(Shoe shoe) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("UPDATE PRODUCTS " +
                    "SET STATUS = ? WHERE PRODUCTID = ?");
            ps.setString(1, shoe.getStatus());
            ps.setInt(2, shoe.getId());
            ps.executeUpdate();
        } catch (SQLException sqlException) {
            LOG.error("ShoeDao, update: cant update shoe. SQL error code: " + sqlException.getErrorCode());
            LOG.error("Shoe info: " + shoe);
            throw new DaoRuntimeException("Невозможно обновить обувь ");
        }
    }

    @Override
    public List<Shoe> getAllByModel(int modelId) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM PRODUCTS " +
                    "WHERE  MODELID = ?");
            preparedStatement.setInt(1, modelId);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Shoe> shoes = new LinkedList<>();
            while (resultSet.next()) {
                shoes.add(extractShoe(resultSet));
            }
            return shoes;
        } catch (SQLException sqlException) {
            LOG.error("ShoeDao, getAllByModel: cant get shoes by model " + modelId +
                    ". SQL error code: " + sqlException.getErrorCode());
            throw new DaoRuntimeException("Невозможно получить всю обувь по модели " + modelId);
        }
    }

    @Override
    public Shoe getAnyExistingByParams(int modelId, int size) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM PRODUCTS " +
                    "WHERE MODELID = ? AND \"SIZE\" = ?");
            preparedStatement.setInt(1, modelId);
            preparedStatement.setInt(2, size);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return extractShoe(resultSet);
        } catch (SQLException sqlException) {
            LOG.error("ShoeDao, getAnyExistingByParams: cant get shoe by model "+ modelId + " and size" + size +
                    ". SQL error code: " + sqlException.getErrorCode());
            throw new DaoRuntimeException("Невозможно получить обувь по модели " + modelId + " и размеру" + size);
        }
    }

    private Shoe extractShoe(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("productid");
        int modelId = resultSet.getInt("modelid");
        int size = resultSet.getInt("size");
        String status = resultSet.getString("status");
        return new Shoe(id, modelId, size, status);
    }
}
