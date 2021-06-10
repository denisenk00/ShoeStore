package ua.edu.j2ee.shoestore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.edu.j2ee.shoestore.dao.ShoeDao;
import ua.edu.j2ee.shoestore.dao.ShoeModelDao;
import ua.edu.j2ee.shoestore.model.ProductCart;
import ua.edu.j2ee.shoestore.model.Shoe;
import ua.edu.j2ee.shoestore.model.ShoeModel;
import ua.edu.j2ee.shoestore.model.User;
import ua.edu.j2ee.shoestore.services.PaginationService;
import ua.edu.j2ee.shoestore.services.ShoeModelFilterService;

import java.util.List;
import java.util.Set;

@Controller
public class ShoeModelController {

    private ShoeModelDao modelDao;
    private ShoeModelFilterService modelFilterService;
    private ShoeDao shoeDao;

    @Autowired
    public ShoeModelController(ShoeModelDao modelDao, ShoeModelFilterService modelFilterService, ShoeDao shoeDao) {
        this.modelDao = modelDao;
        this.modelFilterService = modelFilterService;
        this.shoeDao = shoeDao;
    }

    @GetMapping("/model")
    public ModelAndView modelPage(@RequestParam(name="id") int id){
        ShoeModel model = modelDao.get(id);
        Set<Integer> sizes = modelDao.getExistingSizesByModelId(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("shoeModelPage");
        modelAndView.addObject("model", model);
        modelAndView.addObject("modelSizes", sizes);
        return modelAndView;
    }

    @GetMapping("/modelByShoe")
    public String modelPageByShoe(@RequestParam(name="shoeId") int shoeId){
        Shoe shoe = shoeDao.get(shoeId);
        return "redirect:/model?id=".concat(String.valueOf(shoe.getModelId()));
    }

    @GetMapping("/admin/allModels")
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView allModels(@AuthenticationPrincipal User user,
                                  @RequestParam(name="page", required = false, defaultValue = "1") int currentPage){
        ProductCart userProductCart = user.getProductCart();
        List<ShoeModel> allModels = modelFilterService.getModelsByFilters(userProductCart.getWishedBrands(),
                userProductCart.getWishedMinPrice(),
                userProductCart.getWishedMaxPrice(),
                userProductCart.getWishedTypes(),
                userProductCart.getWishedSeasons(),
                userProductCart.getWishedColors(),
                userProductCart.getWishedGenders(),
                userProductCart.getWishedSizes());
        PaginationService paginationService = new PaginationService(allModels.size(), 25, currentPage, allModels);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("allModels");
        modelAndView.addObject("allTypes", modelDao.getExistingTypes());
        modelAndView.addObject("allBrands", modelDao.getExistingBrands());
        modelAndView.addObject("allSizes", modelDao.getExistingSizes());
        modelAndView.addObject("allColors", modelDao.getExistingColors());
        modelAndView.addObject("minPrice", modelDao.getExistingMinPrice());
        modelAndView.addObject("maxPrice", modelDao.getExistingMaxPrice());
        modelAndView.addObject("allShoeModel", paginationService.makeBatchOfItems());
        modelAndView.addObject("pagination", paginationService.makePagingLinks("admin/models", ""));
        return modelAndView;
    }



    @PostMapping("/addModel")
    @PreAuthorize("hasRole('ADMIN')")
    public String addModel(@RequestParam(name="name") String name, @RequestParam(name="brand") String brand,
                           @RequestParam(name="price") double price, @RequestParam(name="type") String type,
                           @RequestParam(name="season") String season, @RequestParam(name = "supplierId") int supplierId,
                           @RequestParam(name="color") String color, @RequestParam(name="gender") String gender){
        ShoeModel shoeModel = new ShoeModel(0, name, brand, price, type, season, color, gender, supplierId);
        modelDao.save(shoeModel);
        return "redirect:/admin/allModels";
    }

    @PostMapping("/updateModel")
    @PreAuthorize("hasRole('ADMIN')")
    public void updateModel(@RequestParam(name="id") int id, @RequestParam(name="price") double price){
        ShoeModel shoeModel = modelDao.get(id);
        shoeModel.setPrice(price);
        modelDao.update(shoeModel);
    }

    @GetMapping("/admin/model")
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView editModelPanel(@RequestParam(name="id") int id){
        ShoeModel shoeModel = modelDao.get(id);
        List<Shoe> shoes = shoeDao.getAllByModel(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editModelPage");
        modelAndView.addObject("model", shoeModel);
        modelAndView.addObject("shoes", shoes);
        return modelAndView;
    }


}
