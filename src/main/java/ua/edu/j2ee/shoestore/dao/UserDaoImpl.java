package ua.edu.j2ee.shoestore.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.edu.j2ee.shoestore.model.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {


    private DataSource dataSource;

    @Autowired
    public UserDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<User> getAll() {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM USERS");
            ResultSet rs = ps.executeQuery();

            List<User> users = new LinkedList<>();
            while (rs.next()) {
                users.add(extractUser(rs));
            }
            return users;
        } catch (SQLException sqlException) {
            throw new RuntimeException("Cant get all users");
        }
    }

    @Override
    public User get(int id) {
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
    public void save(User user) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO " +
                    "USERS (USERID, NAME, SURNAME, PHONE, EMAIL, PASSWORD, ROLE) " +
                    "VALUES (USERID_SEQ.nextval, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, user.getName());
            ps.setString(2, user.getSurname());
            ps.setString(3, user.getPhone());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getPassword());
            ps.setString(6, user.getRole());
            ps.executeUpdate();
        } catch (SQLException sqlException) {
            throw new RuntimeException("Cant save user");
        }
    }

    @Override
    public void update(User user) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("UPDATE USERS " +
                    "SET NAME = ?, SURNAME = ?, PHONE = ?, PASSWORD = ? WHERE USERID = ?");
            ps.setString(1, user.getName());
            ps.setString(2, user.getSurname());
            ps.setString(3, user.getPhone());
            ps.setString(4, user.getPassword());
            ps.setInt(5, user.getId());
            ps.executeUpdate();
        } catch (SQLException sqlException) {
            throw new RuntimeException("Cant update user " + user.getId());
        }
    }

    private User extractUser(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("userid");
        String name = resultSet.getString("name");
        String surname =  resultSet.getString("surname");
        String phone = resultSet.getString("phone");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        String role = resultSet.getString("role");

        return new User(id, name, surname, phone, email, password, role);
    }

    @Override
    public User getByEmail(String email) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM USERS WHERE EMAIL = ?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return extractUser(rs);
        } catch (SQLException sqlException) {
            //throw new RuntimeException("Cant get user with email " + email);
            return null;
        }
    }
}
