package ua.edu.j2ee.shoestore.dao.interfaces;

import ua.edu.j2ee.shoestore.model.User;

public interface UserDao extends Dao<User> {

    User getByEmail(String email);

    User getByPhone(String phone);

}
