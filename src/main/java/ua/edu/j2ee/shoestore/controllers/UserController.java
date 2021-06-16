package ua.edu.j2ee.shoestore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ua.edu.j2ee.shoestore.dao.UserDao;
import ua.edu.j2ee.shoestore.model.User;

@Controller
@EnableWebMvc
public class UserController {

    private UserDao userDao;

    @Autowired
    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @PostMapping("/user/edit")
    @ResponseBody
    public String updateUserInfo(@AuthenticationPrincipal User user, @RequestParam(name = "name") String name,
                                 @RequestParam(name="surname") String surname, @RequestParam(name="phone") String phone,
                                 @RequestParam(name="email") String email){
        user.setName(name);
        user.setSurname(surname);
        user.setPhone(phone);
        user.setEmail(email);
        userDao.update(user);
        return "{\"msg\":\"success\"}";
    }

    @PostMapping("/user/changeRole")
    @ResponseBody
    public String changeRole(@RequestParam(name = "id") int id, @RequestParam(name = "role") String role){
        User user = userDao.get(id);
        user.setRole(role);
        userDao.update(user);
        return "{\"msg\":\"success\"}";
    }

    @GetMapping("/admin/allUsers")
    public ModelAndView users(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("usersPage");
        modelAndView.addObject("users", userDao.getAll());
        return modelAndView;
    }


}
