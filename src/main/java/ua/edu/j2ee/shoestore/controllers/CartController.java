package ua.edu.j2ee.shoestore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.edu.j2ee.shoestore.dao.ShoeDao;
import ua.edu.j2ee.shoestore.model.ProductCart;
import ua.edu.j2ee.shoestore.model.User;

@Controller
@RequestMapping("/cart")
public class CartController {

    private ShoeDao shoeDao;

    @Autowired
    public CartController(ShoeDao shoeDao) {
        this.shoeDao = shoeDao;
    }

    @PostMapping("/add")
    public String addToCart(@AuthenticationPrincipal User user, @RequestParam(name = "modelId") int modelId, @RequestParam(name="size") int size){
        ProductCart userCart = user.getProductCart();
        userCart.addToCart(modelId, size);
        return "redirect:/store/basket";
    }

    @PostMapping("/remove")
    public String removeFromCart(@AuthenticationPrincipal User user, @RequestParam(name = "modelId") int modelId, @RequestParam(name="size") int size){
        ProductCart userCart = user.getProductCart();
        userCart.removeFromCart(modelId, size);
        return "redirect:/store/basket";
    }

}
