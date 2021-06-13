package ua.edu.j2ee.shoestore.services;

import org.springframework.stereotype.Service;
import ua.edu.j2ee.shoestore.model.CustomUser;

@Service
public interface UserService {

    void registerAccount(CustomUser user);
    boolean emailExists(String email);
    boolean phoneExists(String phone);

}
