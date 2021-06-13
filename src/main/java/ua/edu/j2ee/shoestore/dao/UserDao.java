package ua.edu.j2ee.shoestore.dao;

import ua.edu.j2ee.shoestore.model.CustomUser;

public interface UserDao extends Dao<CustomUser> {

    CustomUser getByEmail(String email);

    CustomUser getByPhone(String phone);

}
