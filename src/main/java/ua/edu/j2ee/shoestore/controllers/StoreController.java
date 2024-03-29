package ua.edu.j2ee.shoestore.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.edu.j2ee.shoestore.dao.implementations.OrderDaoImpl;
import ua.edu.j2ee.shoestore.dao.interfaces.OrderDao;
import ua.edu.j2ee.shoestore.dao.interfaces.ShoeDao;
import ua.edu.j2ee.shoestore.dao.interfaces.ShoeModelDao;
import ua.edu.j2ee.shoestore.dao.interfaces.UserDao;
import ua.edu.j2ee.shoestore.model.ProductCart;
import ua.edu.j2ee.shoestore.model.Shoe;
import ua.edu.j2ee.shoestore.model.ShoeModel;
import ua.edu.j2ee.shoestore.model.User;
import ua.edu.j2ee.shoestore.services.OrderManagementService;
import ua.edu.j2ee.shoestore.services.PaginationService;
import ua.edu.j2ee.shoestore.services.ShoeModelFilterService;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/store")
public class StoreController {

    private static final Logger LOG = Logger.getLogger(OrderDaoImpl.class);

    private UserDao userDao;
    private OrderDao orderDao;
    private ShoeModelDao modelDao;
    private OrderManagementService orderService;
    private ShoeModelFilterService modelFilterService;
    private ShoeDao shoeDao;

    @Autowired
    public StoreController(UserDao userDao, OrderDao orderDao, ShoeModelDao modelDao, OrderManagementService orderService,
                           ShoeModelFilterService modelFilterService, ShoeDao shoeDao) {
        this.userDao = userDao;
        this.orderDao = orderDao;
        this.modelDao = modelDao;
        this.orderService = orderService;
        this.modelFilterService = modelFilterService;
        this.shoeDao = shoeDao;
    }

    @PostMapping("/users/update")
    @ResponseBody
    public ResponseEntity updateUser(@AuthenticationPrincipal User user, @RequestParam(name = "name") String name,
                                     @RequestParam(name="surname") String surname, @RequestParam(name="phone") String phone,
                                     @RequestParam(name="email") String email){
        LOG.info("StoreController, updateUser: user " + user.getUsername() + " try to change profile info.\n New info: " +
                "name = " + name + ", surname = " + surname + ", phone = " + phone + ", email = " + email);
        user.setName(name);
        user.setSurname(surname);
        user.setPhone(phone);
        user.setEmail(email);
        userDao.update(user);
        return ResponseEntity.ok().body("{\"msg\":\"success\"}");
    }

    @GetMapping("/users/profile")
    public ModelAndView profilePage(@AuthenticationPrincipal User user){
        LOG.info("StoreController, profilePage: user " + user.getUsername() + " go to profile page");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("profilePage");
        modelAndView.addObject("user", user);
        modelAndView.addObject("orders", orderDao.getOrdersByUser(user.getId()));
        return modelAndView;
    }

    @PostMapping("/users/addToPCart")
    @ResponseBody
    public ResponseEntity addProductToCart(@AuthenticationPrincipal User user, @RequestParam(name = "modelId") int modelId,
                                           @RequestParam(name="size") int size){
        LOG.info("StoreController, addToProductCart: user " + user.getUsername() + " try to add product with parameters modelId = " +
                modelId  + ", size = " + size + " to his product cart");
        ProductCart userCart = user.getProductCart();
        userCart.addToCart(modelId, size);
        return ResponseEntity.ok().body("{\"msg\":\"success\"}");
    }

    @PostMapping("/users/removeFromPCart")
    public String removeFromProductCart(@AuthenticationPrincipal User user, @RequestParam(name = "modelId") int modelId,
                                        @RequestParam(name="size") int size){
        LOG.info("StoreController, removeFromProductCart: user " + user.getUsername() + " try to remove product with parameters modelId = " +
                modelId  + ", size = " + size + " from his product cart");
        ProductCart userCart = user.getProductCart();
        userCart.removeFromCart(modelId, size);
        return "redirect:/store/users/productCart";
    }

    @GetMapping("/users/productCart")
    public ModelAndView productCartPage(@AuthenticationPrincipal User user){
        LOG.info("StoreController, productCartPage: user " + user.getUsername() + " go to product cart page");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("productCartPage");
        modelAndView.addObject("wishedShoes", user.getProductCart().getShoeCart());
        modelAndView.addObject("models", modelDao.getAllByStatus("IN_STOCK"));
        return modelAndView;
    }

    @PostMapping("/users/updateFilters")
    @ResponseBody
    public ResponseEntity updateFilters(@AuthenticationPrincipal User user,
                                @RequestParam("seasons") Set<String> seasons,
                                @RequestParam("types") Set<String> types,
                                @RequestParam("brands") Set<String> brands,
                                @RequestParam("colors") Set<String> colors,
                                @RequestParam("sizes") Set<Integer> sizes,
                                @RequestParam("genders") Set<String> genders,
                                @RequestParam(value = "minPrice", required = false) double minPrice,
                                @RequestParam(value = "maxPrice", required = false) double maxPrice){
        LOG.info("StoreController, updateFilters: user " + user.getUsername() + " try to update his filters.\nNew filters: " +
                "seasons = " + seasons.toString() + ", types = " + types.toString() + "\nbrands = " + brands.toString() +
                ", colors = " + colors.toString() + "\nsizes = " + sizes.toString() + ", genders = " + genders.toString() +
                "\nminPrice = " + minPrice + ", maxPrice = " + maxPrice);
        user.getProductCart().updateFilters(brands, types, seasons, colors, genders, sizes, maxPrice, minPrice);
        return ResponseEntity.ok().body("{\"msg\":\"success\"}");
    }

    @GetMapping("/users/removeFilters")
    public String removeFiltersAndGo(@AuthenticationPrincipal User user, @RequestParam(name = "goTo") String location){
        user.setProductCart(new ProductCart());
        if(location.equals("store")) return "redirect:/store/models";
        if(location.equals("adminPanel")) return "redirect:/admin/";
        if(location.equals("profile")) return "redirect:/store/users/profile";
        else return null;
    }

    @PostMapping("/orders/create")
    public String createOrder(@AuthenticationPrincipal User user){
        LOG.info("StoreController, createOrder: user " + user.getUsername() + " try to create new order");
        ProductCart userCart = user.getProductCart();
        orderService.createOrder(user.getId(), userCart);
        userCart.clearCart();
        return "redirect:/store/users/productCart";
    }

    @GetMapping("/models")
    public ModelAndView mainPage(@RequestParam(name="page", required = false, defaultValue = "1") int currentPage,
                                 @AuthenticationPrincipal User user){
        LOG.info("StoreController, mainPage: user " + user.getUsername() + " go to main page");
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
        modelAndView.setViewName("modelsPage");
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
        modelAndView.addObject("pagination", paginationService.makePagingLinks("/shoestore/store/models", ""));
        return modelAndView;
    }

    @GetMapping("/models/model")
    public ModelAndView modelPage(@RequestParam(name="id") int id, @AuthenticationPrincipal User user){
        LOG.info("StoreController, modelPage: user " + user.getUsername() + " go to model page with modelId = " + id);
        ShoeModel model = modelDao.get(id);
        Set<Integer> sizes = modelDao.getExistingSizesByModelId(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("shoeModelPage");
        modelAndView.addObject("model", model);
        modelAndView.addObject("modelSizes", sizes);
        return modelAndView;
    }

    @GetMapping("/models/modelByShoe")
    public String modelPageByShoe(@RequestParam(name="shoeId") int shoeId){
        Shoe shoe = shoeDao.get(shoeId);
        return "redirect:/store/models/model?id=".concat(String.valueOf(shoe.getModelId()));
    }

    @GetMapping(value = "/models/getModelsByFilter")
    @ResponseBody
    public ResponseEntity getModels(@AuthenticationPrincipal User user, @RequestParam(name = "page", defaultValue = "1") int page,
                                    @RequestParam(name="status", required = false) String status){
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
        return ResponseEntity.ok().body(paginationService.makeBatchOfItems());
    }

    @GetMapping(value = "/models/getPaginationByFilter")
    @ResponseBody
    public ResponseEntity getPagination(@AuthenticationPrincipal User user, @RequestParam(name = "page", defaultValue = "1") int page,
                                        @RequestParam(name = "pageLocation") String pageLocation) {
        String status = null;
        if (pageLocation.equals("/shoestore/store/models")) status = "IN_STOCK";
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
        return ResponseEntity.ok().body(paginationService.makePagingLinks(pageLocation, ""));
    }
}
