package ua.edu.j2ee.shoestore.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ua.edu.j2ee.shoestore.dao.implementations.OrderDaoImpl;
import ua.edu.j2ee.shoestore.model.User;
import ua.edu.j2ee.shoestore.services.UserService;

@Controller
@RequestMapping("/authorization")
public class AuthorizationController {

    private static final Logger LOG = Logger.getLogger(OrderDaoImpl.class);

    private UserService userService;

    @Autowired
    public AuthorizationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public ModelAndView registration(){
        LOG.info("AuthorizationController, registration: user go to registration page");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("registrationPage");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @PostMapping("/registration")
    public String registerUser(@ModelAttribute("user") User user){
        LOG.info("AuthorizationController, registerUser: user try to register");
        userService.registerAccount(user);
        return "redirect:/authorization/login";
    }

    @GetMapping("/login")
    public ModelAndView login(){
        LOG.info("AuthorizationController, login: user go to login page");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("loginPage");
        return modelAndView;
    }
}
