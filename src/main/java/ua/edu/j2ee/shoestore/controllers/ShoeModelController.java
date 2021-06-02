package ua.edu.j2ee.shoestore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ua.edu.j2ee.shoestore.dao.ShoeModelDao;
import ua.edu.j2ee.shoestore.services.ShoeModelFilterService;

@Controller
public class ShoeModelController {
    @Autowired
    private ShoeModelDao modelDao;




}
