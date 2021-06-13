package ua.edu.j2ee.shoestore.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.edu.j2ee.shoestore.model.CustomUser;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    @Autowired
    private DataSource dataSource;

    @Override
    public List<CustomUser> getAll() {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM USERS");
            ResultSet rs = ps.executeQuery();

            List<CustomUser> customUsers = new LinkedList<>();
            while (rs.next()) {
                customUsers.add(extractUser(rs));
            }
            return customUsers;
        } catch (SQLException sqlException) {
            throw new RuntimeException("Cant get all users");
        }
    }

    @Override
    public CustomUser get(int id) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM USERS WHERE USERID = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return extractUser(rs);
        } catch (SQLException sqlException) {
            throw new RuntimeException("Cant get user " + id);
        }
    }

    @Override
    public void save(CustomUser customUser) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO " +
                    "USERS (USERID, NAME, SURNAME, PHONE, EMAIL, PASSWORD, ROLE) " +
                    "VALUES (USERID_SEQ.nextval, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, customUser.getName());
            ps.setString(2, customUser.getSurname());
            ps.setString(3, customUser.getPhone());
            ps.setString(4, customUser.getEmail());
            ps.setString(5, customUser.getPassword());
            ps.setString(6, customUser.getRole());
            ps.executeUpdate();
        } catch (SQLException sqlException) {
            throw new RuntimeException("Cant save user");
        }
    }

    @Override
    public void update(CustomUser customUser) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("UPDATE USERS " +
                    "SET NAME = ?, SURNAME = ?, PHONE = ?, PASSWORD = ? WHERE USERID = ?");
            ps.setString(1, customUser.getName());
            ps.setString(2, customUser.getSurname());
            ps.setString(3, customUser.getPhone());
            ps.setString(4, customUser.getPassword());
            ps.setInt(5, customUser.getId());
            ps.executeUpdate();
        } catch (SQLException sqlException) {
            throw new RuntimeException("Cant update user " + customUser.getId());
        }
    }

    @Override
    public CustomUser getByEmail(String email) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM USERS WHERE EMAIL = ?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return extractUser(rs);
        } catch (SQLException sqlException) {
            return null;
        }
    }

    @Override
    public CustomUser getByPhone(String phone) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM USERS WHERE PHONE = ?");
            ps.setString(1, phone);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return extractUser(rs);
        } catch (SQLException sqlException) {
            return null;
        }
    }

    private CustomUser extractUser(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("userid");
        String name = resultSet.getString("name");
        String surname =  resultSet.getString("surname");
        String phone = resultSet.getString("phone");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        String role = resultSet.getString("role");

        return new CustomUser(id, name, surname, phone, email, password, role);
    }
}
