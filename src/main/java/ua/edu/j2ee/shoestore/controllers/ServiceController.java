package ua.edu.j2ee.shoestore.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.edu.j2ee.shoestore.dao.implementations.OrderDaoImpl;
import ua.edu.j2ee.shoestore.services.UserService;

@Controller
@RequestMapping("/service")
public class ServiceController {

    private static final Logger LOG = Logger.getLogger(OrderDaoImpl.class);

    private UserService userService;

    @Autowired
    public ServiceController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/checkPhoneForExistence")
    @ResponseBody
    public ResponseEntity checkPhoneForExistence(@RequestParam(name="phone") String phone){
        LOG.info("ServiceController, checkPhoneForExistence: checking for existence in DB phone");
        return ResponseEntity.ok().body(userService.phoneExists(phone));
    }

    @GetMapping("/checkEmailForExistence")
    @ResponseBody
    public ResponseEntity checkEmailForExistence(@RequestParam(name="email") String email){
        LOG.info("ServiceController, checkEmailForExistence: checking for existence in DB email");
        return ResponseEntity.ok().body(userService.emailExists(email));
    }
}
