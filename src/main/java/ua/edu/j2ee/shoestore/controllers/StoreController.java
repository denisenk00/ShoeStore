package ua.edu.j2ee.shoestore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.edu.j2ee.shoestore.model.ShoeModel;
import ua.edu.j2ee.shoestore.services.PaginationService;
import ua.edu.j2ee.shoestore.services.ShoeModelFilterService;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/store")
public class StoreController {
    private Set<String> wishedSeasons;
    private Set<String> wishedTypes;
    private Set<String> wishedBrands;
    private Set<Integer> wishedSizes;
    private Set<String> wishedColors;
    private Set<String> wishedGenders;
    private double minPrice;
    private double maxPrice;
    private final int itemsPerPage = 25;
    @Autowired
    private ShoeModelFilterService modelFilterService;

    @GetMapping("/")
    public ModelAndView mainPage(@RequestParam(name="page", required = false, defaultValue = "1") int currentPage){
        List models = modelFilterService.getModelsInStockByFilters(wishedBrands, minPrice, maxPrice, wishedTypes,
                wishedSeasons, wishedColors, wishedGenders, wishedSizes);
        PaginationService paginationService = new PaginationService(models.size(), itemsPerPage, currentPage, models);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("allShoeModel", models);
        modelAndView.addObject("pagination", paginationService.makePagingLinks("", ""));
        modelAndView.setViewName("mainPage");
        return modelAndView;
    }

    @PostMapping("/setFilters")
    public void setFilters(){


    }



}
