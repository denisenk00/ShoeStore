package ua.edu.j2ee.shoestore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.edu.j2ee.shoestore.model.ProductCart;
import ua.edu.j2ee.shoestore.model.User;
import ua.edu.j2ee.shoestore.services.OrderManagementService;


@Controller
@RequestMapping("/order")
public class OrderController {

    private OrderManagementService orderService;

    @Autowired
    public OrderController(OrderManagementService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/create")
    public String createOrder(@AuthenticationPrincipal User user){
        ProductCart userCart = user.getProductCart();
        orderService.createOrder(user.getId(), userCart);
        return "redirect:/store/basket";
    }


}
