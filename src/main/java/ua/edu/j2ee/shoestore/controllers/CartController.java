package ua.edu.j2ee.shoestore.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ua.edu.j2ee.shoestore.model.ProductCart;
import ua.edu.j2ee.shoestore.model.User;

@Controller
@EnableWebMvc
@RequestMapping("/cart")
public class CartController {

    @PostMapping("/add")
    @ResponseBody
    public String addToCart(@AuthenticationPrincipal User user, @RequestParam(name = "modelId") int modelId, @RequestParam(name="size") int size){
        ProductCart userCart = user.getProductCart();
        userCart.addToCart(modelId, size);
        return "{\"msg\":\"success\"}";
    }

    @PostMapping("/remove")
    public String removeFromCart(@AuthenticationPrincipal User user, @RequestParam(name = "modelId") int modelId, @RequestParam(name="size") int size){
        ProductCart userCart = user.getProductCart();
        userCart.removeFromCart(modelId, size);
        return "redirect:/store/basket";
    }

}
