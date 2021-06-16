package ua.edu.j2ee.shoestore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ua.edu.j2ee.shoestore.model.User;
import ua.edu.j2ee.shoestore.services.UserService;

@Controller
@RequestMapping("/authorization")
public class AuthorizationController {

    private UserService userService;

    @Autowired
    public AuthorizationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("registrationPage");
        modelAndView.addObject("customUser", new User());
        return modelAndView;
    }

    @PostMapping("/registration")
    public String registerUser(@ModelAttribute("user") User user){
        userService.registerAccount(user);
        return "redirect:/store/";
    }

    @GetMapping("/login")
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("loginPage");
        return modelAndView;
    }

    @GetMapping("/logout")
    public String logout(@AuthenticationPrincipal User user){
        return "redirect:/login";
    }
}
