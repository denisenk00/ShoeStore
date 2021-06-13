package ua.edu.j2ee.shoestore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ua.edu.j2ee.shoestore.dao.UserDao;
import ua.edu.j2ee.shoestore.model.CustomUser;
import ua.edu.j2ee.shoestore.model.ProductCart;
import ua.edu.j2ee.shoestore.services.OrderManagementService;


@Controller
@EnableWebMvc
@RequestMapping("/order")
public class OrderController {

    private OrderManagementService orderService;
    private UserDao userDao;

    @Autowired
    public OrderController(OrderManagementService orderService, UserDao userDao) {
        this.orderService = orderService;
        this.userDao = userDao;
    }

    @PostMapping("/create")
    public String createOrder(@AuthenticationPrincipal UserDetails userSession){
        CustomUser customUser = userDao.getByEmail(userSession.getUsername());
        ProductCart userCart = customUser.getProductCart();
        orderService.createOrder(customUser.getId(), userCart);
        return "redirect:/store/basket";
    }


}
