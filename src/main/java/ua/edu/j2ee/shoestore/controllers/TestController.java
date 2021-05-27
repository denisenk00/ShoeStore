package ua.edu.j2ee.shoestore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ua.edu.j2ee.shoestore.dao.SupplierDaoImpl;
import ua.edu.j2ee.shoestore.model.User;
import ua.edu.j2ee.shoestore.model.Order;
import ua.edu.j2ee.shoestore.model.Shoe;
import ua.edu.j2ee.shoestore.model.ShoeModel;

import javax.annotation.Generated;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Controller
public class TestController {
    @Autowired
    private SupplierDaoImpl supplierDao;
    private Set<String> brands = null;


    @PostMapping(path = "/setfilters")
    public void setFilters(){
        brands = new HashSet<>();
    }

    @GetMapping(path = "/index")
    public ModelAndView index(@RequestParam(value = "page") int page) {

        Set<String> allTypes = new HashSet<>();
        allTypes.add("Cross");
        allTypes.add("Bot");
        Set<String> allBrands = new HashSet<>();
        allBrands.add("Adidas");
        allBrands.add("nike");
        Set<Integer> allSizes = new HashSet<>();
        allSizes.add(35);
        allSizes.add(42);
        Set<String> allColors = new HashSet<>();
        allColors.add("Blue");
        allColors.add("Red");
        List<ShoeModel> allShoeModel = new LinkedList<>();
        allShoeModel.add(new ShoeModel(10, "Zx Flux", "Adidas", 2500, "Cross", 2, "Winter", "Red", "Male", 15));
        allShoeModel.add(new ShoeModel(10, "Zx Flux", "Nike", 2200, "Cross", 2, "Winter", "Red", "Male", 16));
        allShoeModel.add(new ShoeModel(10, "Zx Flux", "Nike", 2200, "Cross", 2, "Winter", "Red", "Male", 16));
        allShoeModel.add(new ShoeModel(10, "Zx Flux", "Nike", 2200, "Cross", 2, "Winter", "Red", "Male", 16));
        allShoeModel.add(new ShoeModel(10, "Zx Flux", "Nike", 2200, "Cross", 2, "Winter", "Red", "Male", 16));
        allShoeModel.add(new ShoeModel(10, "sss", "Nike", 2200, "Crаааа", 2, "Winter", "Red", "Male", 16));
        allShoeModel.add(new ShoeModel(10, "Zx Flux", "Nike", 2200, "Cross", 2, "Winter", "Red", "Male", 16));
        allShoeModel.add(new ShoeModel(10, "Zx Flux", "Nike", 2200, "Cross", 2, "Winter", "Rnn", "Male", 16));
        allShoeModel.add(new ShoeModel(10, "Zx Flux", "Nike", 2200, "Cros", 2, "Winter", "Red", "Male", 16));
        allShoeModel.add(new ShoeModel(10, "Zx Flux", "Nike", 2200, "Cross", 2, "Winter", "Red", "Male", 16));
        allShoeModel.add(new ShoeModel(10, "Zx Flux", "Nike", 2200, "Cross", 2, "Winter", "Red", "Male", 16));
        allShoeModel.add(new ShoeModel(10, "Zx Flux", "Nike", 2200, "Cross", 2, "Winter", "Red", "Male", 16));
        allShoeModel.add(new ShoeModel(10, "Zx Flux", "Nike", 2200, "Cross", 2, "Winter", "Red", "Male", 16));
        allShoeModel.add(new ShoeModel(10, "Zx Flux", "Nike", 2200, "Cross", 2, "Winter", "Red", "Male", 16));
        allShoeModel.add(new ShoeModel(10, "Zx Flux", "Nike", 2200, "Cross", 2, "Winter", "Red", "Male", 16));
        allShoeModel.add(new ShoeModel(10, "Zx Flux", "Nike", 2200, "Cross", 2, "Winter", "Red", "Male", 16));
        allShoeModel.add(new ShoeModel(10, "Zx Flux", "Adidas", 2500, "Cross", 2, "Winter", "Red", "Male", 15));
        allShoeModel.add(new ShoeModel(10, "Zx Flux", "Nike", 2200, "Cross", 2, "Winter", "Red", "Male", 16));
        allShoeModel.add(new ShoeModel(10, "Zx Flux", "Nike", 2200, "Cross", 2, "Winter", "Red", "Male", 16));
        allShoeModel.add(new ShoeModel(10, "Zx Flux", "Nike", 2200, "Cross", 2, "Winter", "Red", "Male", 16));
        allShoeModel.add(new ShoeModel(10, "Zx Flux", "Nike", 2200, "Cross", 2, "Winter", "Red", "Male", 16));
        allShoeModel.add(new ShoeModel(10, "Zx Flux", "Nike", 2200, "Cross", 2, "Winter", "Red", "Male", 16));
        allShoeModel.add(new ShoeModel(10, "Zx Flux", "Nike", 2200, "Cross", 2, "Winter", "Red", "Male", 16));
        allShoeModel.add(new ShoeModel(10, "Zx Flux", "Nike", 2200, "Cross", 2, "Winter", "Red", "Male", 16));
        allShoeModel.add(new ShoeModel(10, "Zx Flux", "Nike", 2200, "Cross", 2, "Winter", "Red", "Male", 16));
        allShoeModel.add(new ShoeModel(10, "Zx Flux", "Nike", 2200, "Cross", 2, "Winter", "Red", "Male", 16));
        allShoeModel.add(new ShoeModel(10, "Zx Flux", "Nike", 2200, "Cross", 2, "Winter", "Red", "Male", 16));
        allShoeModel.add(new ShoeModel(10, "Zx Flux", "Nike", 2200, "Cross", 2, "Winter", "Red", "Male", 16));
        allShoeModel.add(new ShoeModel(10, "Zx Flux", "Nike", 2200, "Cross", 2, "Winter", "Red", "Male", 16));
        allShoeModel.add(new ShoeModel(10, "Zx Flux", "Nike", 2200, "Cross", 2, "Winter", "Red", "Male", 16));
        allShoeModel.add(new ShoeModel(10, "Zx Flux", "Nike", 2200, "Cross", 2, "Winter", "Red", "Male", 16));
        allShoeModel.add(new ShoeModel(10, "Zx Flux", "Nike", 2200, "Cross", 2, "Winter", "Red", "Male", 16));
        allShoeModel.add(new ShoeModel(10, "Zx Flux", "Nike", 2200, "Cross", 2, "Winter", "Red", "Male", 16));
        allShoeModel.add(new ShoeModel(10, "Zx Flux", "Nike", 2200, "Cross", 2, "Winter", "Red", "Male", 16));
        allShoeModel.add(new ShoeModel(10, "Zx Flux", "Nike", 2200, "Cross", 2, "Winter", "Red", "Male", 16));
        allShoeModel.add(new ShoeModel(10, "Zx Flux", "Nike", 2200, "Cross", 2, "Winter", "Red", "Male", 16));
        allShoeModel.add(new ShoeModel(10, "Zx Flux", "Nike", 2200, "Cross", 2, "Winter", "Red", "Male", 16));
        allShoeModel.add(new ShoeModel(10, "Zx Flux", "Nike", 2200, "Cross", 2, "Winter", "Red", "Male", 16));
        allShoeModel.add(new ShoeModel(10, "Zx Flux", "Nike", 2200, "Cross", 2, "Winter", "Red", "Male", 16));
        allShoeModel.add(new ShoeModel(10, "Zx Flux", "Nike", 2200, "Cross", 2, "Winter", "Red", "Male", 16));
        allShoeModel.add(new ShoeModel(10, "Zx Flux", "Nike", 2200, "Cross", 2, "Winter", "Red", "Male", 16));
        allShoeModel.add(new ShoeModel(10, "Zx Flux", "Nike", 2200, "Cross", 2, "Winter", "Red", "Male", 16));
        allShoeModel.add(new ShoeModel(10, "Zx Flux", "Nike", 2200, "Cross", 2, "Winter", "Red", "Male", 16));
        allShoeModel.add(new ShoeModel(10, "Zx Flux", "Nike", 2200, "Cross", 2, "Winter", "Red", "Male", 16));
        allShoeModel.add(new ShoeModel(10, "Zx Flux", "Nike", 2200, "Cross", 2, "Winter", "Red", "Male", 16));
        allShoeModel.add(new ShoeModel(10, "Zx Flux", "Nike", 2200, "Cross", 2, "Winter", "Red", "Male", 16));
        allShoeModel.add(new ShoeModel(10, "Zx Flux", "Nike", 2200, "Cross", 2, "Winter", "Red", "Male", 16));
        PaginationController paginationController = new PaginationController(allShoeModel.size(), 20, page);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("allTypes", allTypes);
        modelAndView.addObject("allBrands", allBrands);
        modelAndView.addObject("allSizes", allSizes);
        modelAndView.addObject("allColors", allColors);
        modelAndView.addObject("allShoeModel", allShoeModel);
        modelAndView.addObject("minPrice", 10);
        modelAndView.addObject("maxPrice", 150);
        modelAndView.addObject("pagination", paginationController.makePagingLinks("index", ""));
        modelAndView.setViewName("mainPage");
        return modelAndView;
    }

    @GetMapping("/basket")
    public ModelAndView basket(){
        List<ShoeModel> allShoeModel = new LinkedList<>();
        allShoeModel.add(new ShoeModel(10, "Zx Flux", "Adidas", 2500, "Cross", 2, "Winter", "Red", "Male", 15));
        List<Shoe> wishedShoes = new LinkedList<>();
        wishedShoes.add(new Shoe(1, 10, 38, "IN_STOCK"));
        wishedShoes.add(new Shoe(1, 10, 40, "IN_STOCK"));
        wishedShoes.add(new Shoe(1, 10, 48, "IN_STOCK"));
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("basketPage");
        modelAndView.addObject("models", allShoeModel);
        modelAndView.addObject("wishedShoes", wishedShoes);
        return modelAndView;
    }

    @GetMapping(path = "/profile")
    public ModelAndView profile(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("profilePage");
        User user = new User(0, "Ivan", "Tsarevich", "+38099900888", "dasda@gmail.com", "dasdsa", "user");
        List<Order> orders = new LinkedList<>();
        List<Integer> shoeid = new LinkedList<>();
        shoeid.add(1);
        shoeid.add(2);
        shoeid.add(3);
        orders.add(new Order(0, 0, 250, java.time.LocalDate.now(), shoeid));
        orders.add(new Order(2, 0, 360, java.time.LocalDate.now(), shoeid));
        modelAndView.addObject("user", user);
        modelAndView.addObject("orders", orders);
        return modelAndView;
    }

    @GetMapping("/model")
    public ModelAndView model(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("shoeModelPage");
        List<Integer> sizes = new LinkedList<>();
        sizes.add(35);
        sizes.add(40);
        sizes.add(42);

        modelAndView.addObject("model", new ShoeModel(10, "Zx Flux", "Adidas", 2500, "Cross", 2, "Winter", "Red", "Male", 15));
        modelAndView.addObject("modelSizes", sizes);
        return modelAndView;
    }
}

