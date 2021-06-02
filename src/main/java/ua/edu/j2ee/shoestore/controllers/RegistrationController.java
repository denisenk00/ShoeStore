package ua.edu.j2ee.shoestore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ua.edu.j2ee.shoestore.model.User;
import ua.edu.j2ee.shoestore.services.UserService;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public ModelAndView registration(){
        return new ModelAndView("registrationPage");
    }

    @PostMapping("/registration")
    public void registerUser(@ModelAttribute("user") User user){
        userService.registerAccount(user);
    }


}
