package ua.edu.j2ee.shoestore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.edu.j2ee.shoestore.dao.Dao;
import ua.edu.j2ee.shoestore.model.Supplier;
import ua.edu.j2ee.shoestore.services.PaginationService;

import java.util.List;

@Controller
@PreAuthorize("hasRole('ADMIN')")
public class SupplierController {

    private Dao<Supplier> supplierDao;

    @Autowired
    public SupplierController(Dao<Supplier> supplierDao) {
        this.supplierDao = supplierDao;
    }

    @GetMapping("/admin/allSuppliers")
    public ModelAndView allSuppliers(@RequestParam(name="page", defaultValue = "1") int pageNumber){
        List<Supplier> suppliers = supplierDao.getAll();
        PaginationService paginationService = new PaginationService(suppliers.size(), 25, pageNumber, suppliers);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("suppliersPage");
        modelAndView.addObject("suppliers", paginationService.makeBatchOfItems());
        modelAndView.addObject("pagination", paginationService.makePagingLinks("admin/allSuppliers", ""));
        return modelAndView;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getAllSuppliers")
    @ResponseBody
    public List<Supplier> getAllSuppliersJson(){
        List<Supplier> allSuppliers = supplierDao.getAll();
        return allSuppliers;
    }

    @PostMapping("/addSupplier")
    public String addSupplier(@RequestParam(name="company") String company, @RequestParam(name="city") String city,
                              @RequestParam(name="country") String country, @RequestParam(name="address") String address,
                              @RequestParam(name="phone") String phone, @RequestParam(name="postalCode") String postalCode){
        Supplier supplier = new Supplier(0, company, city, country, address, phone, postalCode);
        supplierDao.save(supplier);
        return "redirect:/admin/allSuppliers";
    }

    @GetMapping("/updateSupplier")
    public ModelAndView updateSupplier(@RequestParam(name="id") int id){
        Supplier supplier = supplierDao.get(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editSupplierPage");
        modelAndView.addObject("supplier", supplier);
        return modelAndView;
    }

    @PostMapping("/updateSupplier")
    public void updateSupplier(@ModelAttribute("supplier") Supplier supplier){
        supplierDao.update(supplier);
    }

}
