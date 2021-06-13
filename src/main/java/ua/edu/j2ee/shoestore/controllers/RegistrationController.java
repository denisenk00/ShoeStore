package ua.edu.j2ee.shoestore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.edu.j2ee.shoestore.model.User;
import ua.edu.j2ee.shoestore.services.UserService;

@Controller
public class RegistrationController {

    private UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("registrationPage");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @PostMapping("/registration")
    public void registerUser(@ModelAttribute("user") User user){
        userService.registerAccount(user);
    }

    @GetMapping("/registration/checkPhoneNumber")
    @ResponseBody
    public boolean checkPhoneNumber(@RequestParam(name="number") String number){
        return userService.phoneExists(number);
    }

    @GetMapping("/registration/checkEmail")
    @ResponseBody
    public boolean checkEmail(@RequestParam(name="email") String email){
        return userService.emailExists(email);
    }

}
