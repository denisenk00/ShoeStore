package ua.edu.j2ee.shoestore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.edu.j2ee.shoestore.dao.Dao;
import ua.edu.j2ee.shoestore.dao.ShoeDao;
import ua.edu.j2ee.shoestore.dao.ShoeModelDao;
import ua.edu.j2ee.shoestore.model.Shoe;
import ua.edu.j2ee.shoestore.model.ShoeModel;
import ua.edu.j2ee.shoestore.model.Supplier;
import ua.edu.j2ee.shoestore.services.ShoeModelFilterService;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/rest", produces = {"application/json", "application/xml"})
public class StoreRestController {

    private ShoeModelDao modelDao;
    private ShoeModelFilterService modelFilterService;
    private ShoeDao shoeDao;
    private Dao<Supplier> supplierDao;

    @Autowired
    public StoreRestController(ShoeModelDao modelDao, ShoeModelFilterService modelFilterService, ShoeDao shoeDao, Dao<Supplier> supplierDao) {
        this.modelDao = modelDao;
        this.modelFilterService = modelFilterService;
        this.shoeDao = shoeDao;
        this.supplierDao = supplierDao;
    }

    @GetMapping("/models/getAll")
    public ResponseEntity getAllModels(@RequestParam(name = "format", required = false, defaultValue = "json") String format,
                                       @RequestParam(name = "status", required = false) String status){
        HttpHeaders httpHeaders = httpHeadersByFormat(format);
        List<ShoeModel> shoeModels;
        if(status == null){
            shoeModels = modelDao.getAll();
        } else{
            shoeModels = modelDao.getAllByStatus(status);
        }
        return ResponseEntity.ok().headers(httpHeaders).body(shoeModels);
    }

    @GetMapping("/models/search")
    public ResponseEntity searchModels(@RequestParam("seasons") Set<String> seasons, @RequestParam("types") Set<String> types,
                                       @RequestParam("brands") Set<String> brands, @RequestParam("colors") Set<String> colors,
                                       @RequestParam("sizes") Set<Integer> sizes, @RequestParam("genders") Set<String> genders,
                                       @RequestParam("minPrice") double minPrice, @RequestParam("maxPrice") double maxPrice,
                                       @RequestParam(value = "status", required = false) String status,
                                       @RequestParam(required = false, name = "format", defaultValue = "json") String format){
        HttpHeaders httpHeaders = httpHeadersByFormat(format);
        List<ShoeModel> shoeModels = modelFilterService.getModelsByFilters(brands, minPrice, maxPrice, types, seasons, colors, genders, sizes, status);
        return ResponseEntity.ok().headers(httpHeaders).body(shoeModels);
    }

    @GetMapping("/models/getById")
    public ResponseEntity getModelById(@RequestParam(name = "modelId") int modelId,
                                       @RequestParam(required = false, name = "format", defaultValue = "json") String format){
        HttpHeaders httpHeaders = httpHeadersByFormat(format);
        ShoeModel model = modelDao.get(modelId);
        return ResponseEntity.ok().headers(httpHeaders).body(model);
    }

    @PostMapping("/models/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity addModel(@RequestParam(name="name") String name, @RequestParam(name="brand") String brand,
                                   @RequestParam(name="price") double price, @RequestParam(name="type") String type,
                                   @RequestParam(name="season") String season, @RequestParam(name = "supplierId") int supplierId,
                                   @RequestParam(name="color") String color, @RequestParam(name="gender") String gender,
                                   @RequestParam(required = false, name = "format", defaultValue = "json") String format){
        HttpHeaders httpHeaders = httpHeadersByFormat(format);
        ShoeModel shoeModel = new ShoeModel(name, brand, price, type, season, color, gender, supplierId);
        modelDao.save(shoeModel);
        return ResponseEntity.ok().headers(httpHeaders).body("{\"msg\":\"success\"}");
    }

    @PostMapping("/models/update")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity updateModel(@RequestParam(name="id") int id, @RequestParam(name="price") double price,
                                      @RequestParam(required = false, name = "format", defaultValue = "json") String format){
        HttpHeaders httpHeaders = httpHeadersByFormat(format);
        ShoeModel shoeModel = modelDao.get(id);
        shoeModel.setPrice(price);
        modelDao.update(shoeModel);
        return ResponseEntity.ok().headers(httpHeaders).body("{\"msg\":\"success\"}");
    }

    @GetMapping("/shoes/getAll")
    public ResponseEntity getAllShoes(@RequestParam(name = "format", required = false, defaultValue = "json") String format){
        HttpHeaders httpHeaders = httpHeadersByFormat(format);
        List<Shoe> shoes = shoeDao.getAll();
        return ResponseEntity.ok().headers(httpHeaders).body(shoes);
    }

    @GetMapping("/shoes/getByModelId")
    public ResponseEntity getShoesByModel(@RequestParam(name="modelId") int modelId,
                                          @RequestParam(name = "format", required = false, defaultValue = "json") String format){
        HttpHeaders httpHeaders = httpHeadersByFormat(format);
        List<Shoe> shoes = shoeDao.getAllByModel(modelId);
        return ResponseEntity.ok().headers(httpHeaders).body(shoes);
    }

    @PostMapping("/shoes/update")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity updateShoe(@RequestParam(name="id") int id, @RequestParam(name="status") String status,
                                     @RequestParam(name = "format", required = false, defaultValue = "json") String format){
        HttpHeaders httpHeaders = httpHeadersByFormat(format);
        Shoe shoe = shoeDao.get(id);
        shoe.setStatus(status);
        shoeDao.update(shoe);
        return ResponseEntity.ok().headers(httpHeaders).body("{\"msg\":\"success\"}");
    }

    @PostMapping("/shoes/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity addShoe(@RequestParam(name = "size") int size, @RequestParam(name = "modelId") int modelId,
                                  @RequestParam(name = "status") String status,
                                  @RequestParam(name = "format", required = false, defaultValue = "json") String format){
        HttpHeaders httpHeaders = httpHeadersByFormat(format);
        shoeDao.save(new Shoe(modelId, size, status));
        return ResponseEntity.ok().headers(httpHeaders).body("{\"msg\":\"success\"}");
    }

    @GetMapping("/suppliers/getAll")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity getAllSuppliers(@RequestParam(required = false, name = "format", defaultValue = "json") String format){
        HttpHeaders httpHeaders = httpHeadersByFormat(format);
        List<Supplier> allSuppliers = supplierDao.getAll();
        return ResponseEntity.ok().headers(httpHeaders).body(allSuppliers);
    }

    @GetMapping("/suppliers/search")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity searchSuppliers(@RequestParam(required = false, name = "format", defaultValue = "json") String format){
        HttpHeaders httpHeaders = httpHeadersByFormat(format);
        List<Supplier> allSuppliers = null;
        return ResponseEntity.ok().headers(httpHeaders).body(allSuppliers);
    }

    @PostMapping("/suppliers/update")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity updateSupplier(@RequestParam(name = "id") int id, @RequestParam(name = "company") String company,
                                         @RequestParam(name = "city") String city, @RequestParam(name = "country") String country,
                                         @RequestParam(name = "address") String address, @RequestParam(name = "phone") String phone,
                                         @RequestParam(name = "postalCode") String postalCode,
                                         @RequestParam(required = false, name = "format", defaultValue = "json") String format){
        HttpHeaders httpHeaders = httpHeadersByFormat(format);
        Supplier supplier = new Supplier(id, company, city, country, address, phone, postalCode);
        supplierDao.update(supplier);
        return ResponseEntity.ok().headers(httpHeaders).body("{\"msg\":\"success\"}");
    }

    @PostMapping("/suppliers/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity addSupplier(@RequestParam(name="company") String company, @RequestParam(name="city") String city,
                                      @RequestParam(name="country") String country, @RequestParam(name="address") String address,
                                      @RequestParam(name="phone") String phone, @RequestParam(name="postalCode") String postalCode,
                                      @RequestParam(required = false, name = "format", defaultValue = "json") String format){
        HttpHeaders httpHeaders = httpHeadersByFormat(format);
        Supplier supplier = new Supplier(company, city, country, address, phone, postalCode);
        supplierDao.save(supplier);
        return ResponseEntity.ok().headers(httpHeaders).body("{\"msg\":\"success\"}");
    }

    private HttpHeaders httpHeadersByFormat(String format){
        HttpHeaders httpHeaders = new HttpHeaders();
        if(format.equalsIgnoreCase("json")){
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        }else if(format.equalsIgnoreCase("xml")){
            httpHeaders.setContentType(MediaType.APPLICATION_XML);
        }else {
            throw new RuntimeException("Invalid format - This service doesn't exist in that format");
        }
        return httpHeaders;
    }
}
