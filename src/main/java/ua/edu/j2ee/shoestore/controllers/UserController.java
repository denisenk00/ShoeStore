package ua.edu.j2ee.shoestore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ua.edu.j2ee.shoestore.dao.UserDao;
import ua.edu.j2ee.shoestore.model.CustomUser;
@Controller
@RequestMapping("/user")
@EnableWebMvc
public class UserController {

    private UserDao userDao;

    @Autowired
    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @PostMapping("/edit")
    @ResponseBody
    public String updateUserInfo(@AuthenticationPrincipal UserDetails userSession, @RequestParam(name = "name") String name,
                               @RequestParam(name="surname") String surname, @RequestParam(name="phone") String phone,
                               @RequestParam(name="email") String email){
        System.out.println(userSession.getUsername());
        CustomUser customUser = userDao.getByEmail(userSession.getUsername());
        customUser.setName(name);
        customUser.setSurname(surname);
        customUser.setPhone(phone);
        customUser.setEmail(email);
        userDao.update(customUser);
        return "{\"msg\":\"success\"}";
    }


}
