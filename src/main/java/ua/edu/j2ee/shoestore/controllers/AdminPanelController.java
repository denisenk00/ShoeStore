package ua.edu.j2ee.shoestore.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.edu.j2ee.shoestore.dao.implementations.OrderDaoImpl;
import ua.edu.j2ee.shoestore.dao.interfaces.Dao;
import ua.edu.j2ee.shoestore.dao.interfaces.ShoeDao;
import ua.edu.j2ee.shoestore.dao.interfaces.ShoeModelDao;
import ua.edu.j2ee.shoestore.dao.interfaces.UserDao;
import ua.edu.j2ee.shoestore.model.*;
import ua.edu.j2ee.shoestore.services.PaginationService;
import ua.edu.j2ee.shoestore.services.ShoeModelFilterService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminPanelController {

    private static final Logger LOG = Logger.getLogger(OrderDaoImpl.class);

    private UserDao userDao;
    private ShoeModelFilterService modelFilterService;
    private ShoeModelDao modelDao;
    private ShoeDao shoeDao;
    private Dao<Supplier> supplierDao;

    @Autowired
    public AdminPanelController(UserDao userDao, ShoeModelFilterService modelFilterService, ShoeModelDao modelDao,
                                ShoeDao shoeDao, Dao<Supplier> supplierDao) {
        this.userDao = userDao;
        this.modelFilterService = modelFilterService;
        this.modelDao = modelDao;
        this.shoeDao = shoeDao;
        this.supplierDao = supplierDao;
    }

    @GetMapping("/")
    public ModelAndView adminPanelPage(@AuthenticationPrincipal User user){
        LOG.warn("AdminPanelController, adminPanelPage: user " + user.getUsername() + " go to admin panel");
        return new ModelAndView("adminPanel");
    }

    @GetMapping("/users")
    public ModelAndView usersPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("adminUsersPage");
        modelAndView.addObject("users", userDao.getAll());
        return modelAndView;
    }

    @PostMapping("/users/changeRole")
    @ResponseBody
    public ResponseEntity changeRole(@RequestParam(name = "id") int id, @RequestParam(name = "role") String role, @AuthenticationPrincipal User user){
        LOG.warn("AdminPanelController, changeRole: user " + user.getUsername() + " try to change the role of the user with id = " + id + ", new role = " + role);
        User userForChange = userDao.get(id);
        userForChange.setRole(role);
        userDao.update(userForChange);
        return ResponseEntity.ok().body("{\"msg\":\"success\"}");
    }

    @GetMapping("/models")
    public ModelAndView modelsPage(@AuthenticationPrincipal User user,
                                   @RequestParam(name="page", required = false, defaultValue = "1") int currentPage){
        LOG.info("AdminPanelController, modelsPage: user" + user.getUsername() + " go to product management panel");
        ProductCart userProductCart = user.getProductCart();
        List<ShoeModel> allModels = modelFilterService.getModelsByFilters(userProductCart.getWishedBrands(),
                userProductCart.getWishedMinPrice(),
                userProductCart.getWishedMaxPrice(),
                userProductCart.getWishedTypes(),
                userProductCart.getWishedSeasons(),
                userProductCart.getWishedColors(),
                userProductCart.getWishedGenders(),
                userProductCart.getWishedSizes(),
                null);
        PaginationService paginationService = new PaginationService(allModels.size(), 25, currentPage, allModels);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("adminModelsPage");
        modelAndView.addObject("wishedTypes", userProductCart.getWishedTypes());
        modelAndView.addObject("wishedBrands", userProductCart.getWishedBrands());
        modelAndView.addObject("wishedColors", userProductCart.getWishedColors());
        modelAndView.addObject("wishedMinPrice", userProductCart.getWishedMinPrice());
        modelAndView.addObject("wishedMaxPrice", userProductCart.getWishedMaxPrice());
        modelAndView.addObject("wishedGenders", userProductCart.getWishedGenders());
        modelAndView.addObject("wishedSeasons", userProductCart.getWishedSeasons());
        modelAndView.addObject("allTypes", modelDao.getExistingTypes());
        modelAndView.addObject("allBrands", modelDao.getExistingBrands());
        modelAndView.addObject("allColors", modelDao.getExistingColors());
        modelAndView.addObject("minPrice", modelDao.getExistingMinPrice());
        modelAndView.addObject("maxPrice", modelDao.getExistingMaxPrice());
        modelAndView.addObject("allShoeModel", paginationService.makeBatchOfItems());
        modelAndView.addObject("pagination", paginationService.makePagingLinks("/shoestore/admin/models", ""));
        return modelAndView;
    }

    @GetMapping("/models/model")
    public ModelAndView updateModelPage(@RequestParam(name="id") int id, @AuthenticationPrincipal User user){
        LOG.info("AdminPanelController, updateModelPage: user " + user.getUsername() + " go to page for updating model with id: " + id);
        ShoeModel shoeModel = modelDao.get(id);
        List<Shoe> shoes = shoeDao.getAllByModel(id);
        Set<String> statuses = new HashSet<>();
        statuses.add("IN_STOCK");
        statuses.add("BOOKED");
        statuses.add("EXPECTED");
        statuses.add("NOT_AVAILABLE");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editModelPage");
        modelAndView.addObject("statuses", statuses);
        modelAndView.addObject("model", shoeModel);
        modelAndView.addObject("shoes", shoes);
        return modelAndView;
    }

    @GetMapping("/suppliers")
    public ModelAndView suppliersPage(@RequestParam(name="page", defaultValue = "1") int pageNumber, @AuthenticationPrincipal User user){
        LOG.info("AdminPanelController, suppliersPage: user " + user.getUsername() + " go to suppliers management page");
        List<Supplier> suppliers = supplierDao.getAll();
        PaginationService paginationService = new PaginationService(suppliers.size(), 25, pageNumber, suppliers);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("adminSuppliersPage");
        modelAndView.addObject("suppliers", paginationService.makeBatchOfItems());
        modelAndView.addObject("pagination", paginationService.makePagingLinks("/shoestore/admin/suppliers", ""));
        return modelAndView;
    }

    @GetMapping("/suppliers/update")
    public ModelAndView updateSupplierPage(@RequestParam(name="id") int id, @AuthenticationPrincipal User user){
        LOG.info("AdminPanelController, updateSupplierPage: user " + user.getUsername() + " go page for updating supplier");
        Supplier supplier = supplierDao.get(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editSupplierPage");
        modelAndView.addObject("supplier", supplier);
        return modelAndView;
    }
}
