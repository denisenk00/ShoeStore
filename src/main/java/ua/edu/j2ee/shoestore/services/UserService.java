package ua.edu.j2ee.shoestore.services;

import org.springframework.stereotype.Service;
import ua.edu.j2ee.shoestore.model.User;

@Service
public interface UserService {

    void registerAccount(User user);
    boolean emailExists(String email);
    boolean phoneExists(String phone);

}
