package ua.edu.j2ee.shoestore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ua.edu.j2ee.shoestore.dao.ShoeDao;
import ua.edu.j2ee.shoestore.model.ProductCart;
import ua.edu.j2ee.shoestore.model.CustomUser;

@Controller
@EnableWebMvc
@RequestMapping("/cart")
public class CartController {

    private ShoeDao shoeDao;

    @Autowired
    public CartController(ShoeDao shoeDao) {
        this.shoeDao = shoeDao;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addToCart(@AuthenticationPrincipal CustomUser customUser, @RequestParam(name = "modelId") int modelId, @RequestParam(name="size") int size){
        ProductCart userCart = customUser.getProductCart();
        userCart.addToCart(modelId, size);
        return "redirect:/store/basket";
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public String removeFromCart(@AuthenticationPrincipal CustomUser customUser, @RequestParam(name = "modelId") int modelId, @RequestParam(name="size") int size){
        ProductCart userCart = customUser.getProductCart();
        userCart.removeFromCart(modelId, size);
        return "redirect:/store/basket";
    }

}
