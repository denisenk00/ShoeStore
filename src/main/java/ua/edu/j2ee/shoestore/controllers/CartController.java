package ua.edu.j2ee.shoestore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ua.edu.j2ee.shoestore.dao.UserDao;
import ua.edu.j2ee.shoestore.model.ProductCart;
import ua.edu.j2ee.shoestore.model.CustomUser;

@Controller
@EnableWebMvc
@RequestMapping("/cart")
public class CartController {

    private UserDao userDao;

    @Autowired
    public CartController(UserDao userDao) {
        this.userDao = userDao;
    }

    @PostMapping("/add")
    public String addToCart(@AuthenticationPrincipal UserDetails userSession, @RequestParam(name = "modelId") int modelId, @RequestParam(name="size") int size){
        CustomUser customUser = userDao.getByEmail(userSession.getUsername());
        ProductCart userCart = customUser.getProductCart();
        userCart.addToCart(modelId, size);
        return "redirect:/store/basket";
    }

    @PostMapping("/remove")
    public String removeFromCart(@AuthenticationPrincipal UserDetails userSession, @RequestParam(name = "modelId") int modelId, @RequestParam(name="size") int size){
        CustomUser customUser = userDao.getByEmail(userSession.getUsername());
        ProductCart userCart = customUser.getProductCart();
        userCart.removeFromCart(modelId, size);
        return "redirect:/store/basket";
    }

}
