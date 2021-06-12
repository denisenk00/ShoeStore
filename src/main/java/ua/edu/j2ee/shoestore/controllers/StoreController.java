package ua.edu.j2ee.shoestore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.edu.j2ee.shoestore.dao.OrderDao;
import ua.edu.j2ee.shoestore.dao.ShoeModelDao;
import ua.edu.j2ee.shoestore.model.ProductCart;
import ua.edu.j2ee.shoestore.model.ShoeModel;
import ua.edu.j2ee.shoestore.model.User;
import ua.edu.j2ee.shoestore.services.PaginationService;
import ua.edu.j2ee.shoestore.services.ShoeModelFilterService;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/store")
public class StoreController {

    private ShoeModelFilterService modelFilterService;
    private ShoeModelDao modelDao;
    private OrderDao orderDao;

    @Autowired
    public StoreController(ShoeModelFilterService modelFilterService, ShoeModelDao modelDao, OrderDao orderDao) {
        this.modelFilterService = modelFilterService;
        this.modelDao = modelDao;
        this.orderDao = orderDao;
    }

    @GetMapping("/")
    public ModelAndView mainPage(@RequestParam(name="page", required = false, defaultValue = "1") int currentPage,
                                 @AuthenticationPrincipal User user){
        ProductCart userProductCart = user.getProductCart();
        List<ShoeModel> models = modelFilterService.getModelsByFilters(userProductCart.getWishedBrands(),
                                                                              userProductCart.getWishedMinPrice(),
                                                                              userProductCart.getWishedMaxPrice(),
                                                                              userProductCart.getWishedTypes(),
                                                                              userProductCart.getWishedSeasons(),
                                                                              userProductCart.getWishedColors(),
                                                                              userProductCart.getWishedGenders(),
                                                                              userProductCart.getWishedSizes(),
                                                                              "IN_STOCK");
        PaginationService paginationService = new PaginationService(models.size(), 25, currentPage, models);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("mainPage");
        modelAndView.addObject("wishedTypes", userProductCart.getWishedTypes());
        modelAndView.addObject("wishedBrands", userProductCart.getWishedBrands());
        modelAndView.addObject("wishedSizes", userProductCart.getWishedSizes());
        modelAndView.addObject("wishedColors", userProductCart.getWishedColors());
        modelAndView.addObject("wishedMinPrice", userProductCart.getWishedMinPrice());
        modelAndView.addObject("wishedMaxPrice", userProductCart.getWishedMaxPrice());
        modelAndView.addObject("wishedGenders", userProductCart.getWishedGenders());
        modelAndView.addObject("wishedSeasons", userProductCart.getWishedSeasons());
        modelAndView.addObject("allTypes", modelDao.getExistingTypes());
        modelAndView.addObject("allBrands", modelDao.getExistingBrands());
        modelAndView.addObject("allSizes", modelDao.getExistingSizes());
        modelAndView.addObject("allColors", modelDao.getExistingColors());
        modelAndView.addObject("minPrice", modelDao.getExistingMinPrice());
        modelAndView.addObject("maxPrice", modelDao.getExistingMaxPrice());
        modelAndView.addObject("allShoeModel", paginationService.makeBatchOfItems());
        modelAndView.addObject("pagination", paginationService.makePagingLinks("", ""));
        return modelAndView;
    }

    @PostMapping("/updateFilters")
    public void updateFilters(@AuthenticationPrincipal User user,
                              @RequestParam("seasons") Set<String> seasons,
                              @RequestParam("types") Set<String> types,
                              @RequestParam("brands") Set<String> brands,
                              @RequestParam("colors") Set<String> colors,
                              @RequestParam("sizes") Set<Integer> sizes,
                              @RequestParam("genders") Set<String> genders,
                              @RequestParam("minPrice") double minPrice,
                              @RequestParam("maxPrice") double maxPrice){
        user.getProductCart().updateFilters(brands, types, seasons, colors, genders, sizes, minPrice, maxPrice);
    }

    @GetMapping("/getModelsByFilter")
    @ResponseBody
    public List<ShoeModel> getModels(@AuthenticationPrincipal User user, @RequestParam(name = "page", defaultValue = "1") int page){
        ProductCart userProductCart = user.getProductCart();
        List<ShoeModel> allModels = modelFilterService.getModelsByFilters(userProductCart.getWishedBrands(),
                userProductCart.getWishedMinPrice(),
                userProductCart.getWishedMaxPrice(),
                userProductCart.getWishedTypes(),
                userProductCart.getWishedSeasons(),
                userProductCart.getWishedColors(),
                userProductCart.getWishedGenders(),
                userProductCart.getWishedSizes(),
                "IN_STOCK");
        PaginationService paginationService = new PaginationService(allModels.size(), 25, page, allModels);
        return paginationService.makeBatchOfItems();
    }

    @GetMapping("/getPaginationByFilter")
    @ResponseBody
    public String getPagination(@AuthenticationPrincipal User user, @RequestParam(name = "page", defaultValue = "1") int page,
                                @RequestParam(name = "pageLocation") String pageLocation){
        String status = null;
        if(pageLocation.equals("")) status = "IN_STOCK";
        ProductCart userProductCart = user.getProductCart();
        List<ShoeModel> allModels = modelFilterService.getModelsByFilters(userProductCart.getWishedBrands(),
                userProductCart.getWishedMinPrice(),
                userProductCart.getWishedMaxPrice(),
                userProductCart.getWishedTypes(),
                userProductCart.getWishedSeasons(),
                userProductCart.getWishedColors(),
                userProductCart.getWishedGenders(),
                userProductCart.getWishedSizes(),
                status);
        PaginationService paginationService = new PaginationService(allModels.size(), 25, page, allModels);
        return paginationService.makePagingLinks(pageLocation, "");
    }

    @GetMapping("/profile")
    public ModelAndView profile(@AuthenticationPrincipal User user){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("profilePage");
        modelAndView.addObject("user", user);
        modelAndView.addObject("orders", orderDao.getOrdersByUser(user.getId()));
        return modelAndView;
    }

    @GetMapping("/basket")
    public ModelAndView basket(@AuthenticationPrincipal User user){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("basketPage");
        modelAndView.addObject("wishedShoes", user.getProductCart().getShoeCart());
        modelAndView.addObject("models", modelDao.getAllByStatus("IN_STOCK"));
        return modelAndView;
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/adminPanel")
    public ModelAndView adminPanel(){
        return new ModelAndView("adminPanel");
    }

    @GetMapping("/removeFilters")
    public String removeFiltersAndGo(@AuthenticationPrincipal User user, @RequestParam(name = "goTo") String location){
        user.setProductCart(new ProductCart());
        if(location.equals("mainPage")) return "redirect:/store/";
        if(location.equals("adminPanel")) return "redirect:/store/adminPanel";
        else return null;
    }
}
