package ua.edu.j2ee.shoestore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.edu.j2ee.shoestore.dao.UserDao;
import ua.edu.j2ee.shoestore.model.CustomUser;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void registerAccount(CustomUser customUser) {
        if (emailExists(customUser.getEmail()) || phoneExists(customUser.getPhone())) {
            throw new IllegalArgumentException("User with such email or phone already exists");
        }

        userDao.save(customUser);
    }

    public boolean emailExists(String email) {
        return userDao.getByEmail(email) != null;
    }

    public boolean phoneExists(String phone) {
        return userDao.getByPhone(phone) != null;
    }
}
