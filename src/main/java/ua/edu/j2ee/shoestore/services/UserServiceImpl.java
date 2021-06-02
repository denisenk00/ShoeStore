package ua.edu.j2ee.shoestore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.edu.j2ee.shoestore.dao.UserDao;
import ua.edu.j2ee.shoestore.model.User;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void registerAccount(User user) {
        if (emailExists(user.getEmail()) || phoneExists(user.getPhone())) {
            throw new IllegalArgumentException("User with such email or phone already exists");
        }

        userDao.save(user);
    }

    private boolean emailExists(String email) {
        return userDao.getByEmail(email) != null;
    }

    private boolean phoneExists(String phone) {
        return userDao.getByPhone(phone) != null;
    }
}
