package ua.edu.j2ee.shoestore.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.edu.j2ee.shoestore.dao.implementations.OrderDaoImpl;
import ua.edu.j2ee.shoestore.dao.interfaces.Dao;
import ua.edu.j2ee.shoestore.dao.interfaces.ShoeDao;
import ua.edu.j2ee.shoestore.dao.interfaces.ShoeModelDao;
import ua.edu.j2ee.shoestore.exceptions.DaoRuntimeException;
import ua.edu.j2ee.shoestore.exceptions.RestServiceException;
import ua.edu.j2ee.shoestore.model.Shoe;
import ua.edu.j2ee.shoestore.model.ShoeModel;
import ua.edu.j2ee.shoestore.model.Supplier;
import ua.edu.j2ee.shoestore.services.ShoeModelFilterService;
import ua.edu.j2ee.shoestore.services.SupplierFilterService;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/rest", produces = {"application/json", "application/xml"})
public class StoreRestController {

    private static final Logger LOG = Logger.getLogger(OrderDaoImpl.class);

    private ShoeModelDao modelDao;
    private ShoeModelFilterService modelFilterService;
    private ShoeDao shoeDao;
    private Dao<Supplier> supplierDao;
    private SupplierFilterService supplierFilterService;

    @Autowired
    public StoreRestController(ShoeModelDao modelDao, ShoeModelFilterService modelFilterService, ShoeDao shoeDao,
                               Dao<Supplier> supplierDao, SupplierFilterService supplierFilterService) {
        this.modelDao = modelDao;
        this.modelFilterService = modelFilterService;
        this.shoeDao = shoeDao;
        this.supplierDao = supplierDao;
        this.supplierFilterService = supplierFilterService;
    }

    @GetMapping("/models/getAll")
    public ResponseEntity getAllModels(@RequestParam(name = "format", required = false, defaultValue = "json") String format,
                                       @RequestParam(name = "status", required = false) String status){
        HttpHeaders httpHeaders = httpHeadersByFormat(format);
        List<ShoeModel> shoeModels;
        try{
            if(status == null){
                shoeModels = modelDao.getAll();
            } else{
                shoeModels = modelDao.getAllByStatus(status);
            }
        }catch (DaoRuntimeException e){
            LOG.error("StoreRestController, getAllModels: can't get all models by status: " + status, e);
            throw new RestServiceException(e.getMessage(), format);
        }

        return ResponseEntity.ok().headers(httpHeaders).body(shoeModels);
    }

    @GetMapping("/models/search")
    public ResponseEntity searchModels(@RequestParam(value = "seasons", required = false) Set<String> seasons,
                                       @RequestParam(value = "types", required = false) Set<String> types,
                                       @RequestParam(value = "brands", required = false) Set<String> brands,
                                       @RequestParam(value = "colors", required = false) Set<String> colors,
                                       @RequestParam(value = "sizes", required = false) Set<Integer> sizes,
                                       @RequestParam(value = "genders", required = false) Set<String> genders,
                                       @RequestParam(value = "minPrice", required = false, defaultValue = "0") double minPrice,
                                       @RequestParam(value = "maxPrice", required = false, defaultValue = "0") double maxPrice,
                                       @RequestParam(value = "status", required = false) String status,
                                       @RequestParam(required = false, name = "format", defaultValue = "json") String format){
        HttpHeaders httpHeaders = httpHeadersByFormat(format);
        List<ShoeModel> shoeModels;
        try{
            shoeModels = modelFilterService.getModelsByFilters(brands, minPrice, maxPrice, types, seasons, colors, genders, sizes, status);
        }catch (DaoRuntimeException e){
            LOG.error("StoreRestController, searchModels: can't get models by filters: \n" +
                    "seasons: " + seasons.toString() + "\ntypes: " + types.toString() + "\nbrands: " + brands.toString()
                    + "\ncolors: " + colors.toString() + "\nsizes: " + sizes.toString() + "\ngenders: " + genders.toString()
                    + "\nminPrice: " + minPrice + "\nmaxPrice: " + maxPrice + "\nstatus: " + status, e);
            throw new RestServiceException(e.getMessage(), format);
        }
        return ResponseEntity.ok().headers(httpHeaders).body(shoeModels);
    }

    @GetMapping("/models/getById")
    public ResponseEntity getModelById(@RequestParam(name = "modelId") int modelId,
                                       @RequestParam(required = false, name = "format", defaultValue = "json") String format){
        HttpHeaders httpHeaders = httpHeadersByFormat(format);
        ShoeModel model;
        try{
            model = modelDao.get(modelId);
        }catch (DaoRuntimeException e){
            LOG.error("StoreRestController, getModelById: can't get model by id: " + modelId);
            throw new RestServiceException(e.getMessage(), format);
        }
        return ResponseEntity.ok().headers(httpHeaders).body(model);
    }

    @PostMapping("/models/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity addModel(@RequestParam(name="name") String name, @RequestParam(name="brand") String brand,
                                   @RequestParam(name="price") double price, @RequestParam(name="type") String type,
                                   @RequestParam(name="season") String season, @RequestParam(name = "supplierId") int supplierId,
                                   @RequestParam(name="color") String color, @RequestParam(name="gender") String gender){
        HttpHeaders httpHeaders = httpHeadersByFormat("json");
        ShoeModel shoeModel = new ShoeModel(name, brand, price, type, season, color, gender, supplierId);
        try{
            modelDao.save(shoeModel);
        }catch (DaoRuntimeException e){
            LOG.error("StoreRestController, addModel: can't post new model with parameters: name = " + name +
                    ", brand = " + brand + ", price = " + price + ", type = " + type + ", season = " + season +
                    ", supplierId = " + supplierId + ", color = " + color + ", gender = " + gender, e);
            throw new RestServiceException(e.getMessage(), "json");
        }
        return ResponseEntity.ok().headers(httpHeaders).body("{\"msg\":\"success\"}");
    }

    @PostMapping("/models/update")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity updateModel(@RequestParam(name="id") int id, @RequestParam(name="price") double price){
        HttpHeaders httpHeaders = httpHeadersByFormat("json");
        ShoeModel shoeModel = modelDao.get(id);
        shoeModel.setPrice(price);
        try{
            modelDao.update(shoeModel);
        }catch (DaoRuntimeException e){
            LOG.error("StoreRestController, updateModel: can't update model with parameters: id = " + id + ", price = " + price, e);
            throw new RestServiceException(e.getMessage(), "json");
        }
        return ResponseEntity.ok().headers(httpHeaders).body("{\"msg\":\"success\"}");
    }

    @GetMapping("/shoes/getAll")
    public ResponseEntity getAllShoes(@RequestParam(name = "format", required = false, defaultValue = "json") String format){
        HttpHeaders httpHeaders = httpHeadersByFormat(format);
        List<Shoe> shoes;
        try{
            shoes = shoeDao.getAll();
        }catch (DaoRuntimeException e){
            LOG.error("StoreRestController, getAllShoes: can't get all shoes", e);
            throw new RestServiceException(e.getMessage(), format);
        }
        return ResponseEntity.ok().headers(httpHeaders).body(shoes);
    }

    @GetMapping("/shoes/getByModelId")
    public ResponseEntity getShoesByModel(@RequestParam(name="modelId") int modelId,
                                          @RequestParam(name = "format", required = false, defaultValue = "json") String format){
        HttpHeaders httpHeaders = httpHeadersByFormat(format);
        List<Shoe> shoes;
        try{
            shoes = shoeDao.getAllByModel(modelId);
        }catch (DaoRuntimeException e){
            LOG.error("StoreRestController, getShoesByModel: can't get all shoes with modelId = " + modelId, e);
            throw new RestServiceException(e.getMessage(), format);
        }
        return ResponseEntity.ok().headers(httpHeaders).body(shoes);
    }

    @PostMapping("/shoes/update")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity updateShoe(@RequestParam(name="id") int id, @RequestParam(name="status") String status){
        HttpHeaders httpHeaders = httpHeadersByFormat("json");
        Shoe shoe = shoeDao.get(id);
        shoe.setStatus(status);
        try {
            shoeDao.update(shoe);
        }catch (DaoRuntimeException e){
            LOG.error("StoreRestController, updateShoe: can't update shoe with parameters: id = " + id + ", status = " + status, e);
            throw new RestServiceException(e.getMessage(), "json");
        }
        return ResponseEntity.ok().headers(httpHeaders).body("{\"msg\":\"success\"}");
    }

    @PostMapping("/shoes/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity addShoe(@RequestParam(name = "size") int size, @RequestParam(name = "modelId") int modelId,
                                  @RequestParam(name = "status") String status){
        HttpHeaders httpHeaders = httpHeadersByFormat("json");
        try{
            shoeDao.save(new Shoe(modelId, size, status));
        }catch (DaoRuntimeException e){
            LOG.error("StoreRestController, addShoe: can't add shoe with parameters: modelId = " + modelId + ", size = " + size +
                    ", status = " + status, e);
            throw new RestServiceException(e.getMessage(), "json");
        }
        return ResponseEntity.ok().headers(httpHeaders).body("{\"msg\":\"success\"}");
    }

    @GetMapping("/suppliers/getAll")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity getAllSuppliers(@RequestParam(required = false, name = "format", defaultValue = "json") String format){
        HttpHeaders httpHeaders = httpHeadersByFormat(format);
        List<Supplier> allSuppliers;
        try{
            allSuppliers = supplierDao.getAll();
        }catch (DaoRuntimeException e){
            LOG.error("StoreRestController, getAllSuppliers: can't get all suppliers", e);
            throw new RestServiceException(e.getMessage(), format);
        }
        return ResponseEntity.ok().headers(httpHeaders).body(allSuppliers);
    }

    @GetMapping("/suppliers/search")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity searchSuppliers(@RequestParam(name = "companies", required = false) Set<String> companies,
                                          @RequestParam(name = "cities", required = false) Set<String> cities,
                                          @RequestParam(name = "addresses", required = false) Set<String> addresses,
                                          @RequestParam(name = "phones", required = false) Set<String> phones,
                                          @RequestParam(name = "postalCodes", required = false) Set<String> postalCodes,
                                          @RequestParam(required = false, name = "format", defaultValue = "json") String format){
        HttpHeaders httpHeaders = httpHeadersByFormat(format);
        List<Supplier> allSuppliers;
        try{
            allSuppliers = supplierFilterService.getSuppliersByFilters(companies, cities, addresses, phones, postalCodes);
        }catch (DaoRuntimeException e){
            LOG.error("StoreRestController, searchSuppliers: can't search suppliers by filters:\n" +
                    "companies = " + companies.toString() + "\ncities = " + cities.toString() + "\naddresses = " + addresses.toString() +
                    "\nphones = " + phones.toString() + "\npostalCodes = " + postalCodes.toString());
            throw new RestServiceException(e.getMessage(), format);
        }
        return ResponseEntity.ok().headers(httpHeaders).body(allSuppliers);
    }

    @PostMapping("/suppliers/update")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity updateSupplier(@RequestParam(name = "id") int id, @RequestParam(name = "company") String company,
                                         @RequestParam(name = "city") String city, @RequestParam(name = "country") String country,
                                         @RequestParam(name = "address") String address, @RequestParam(name = "phone") String phone,
                                         @RequestParam(name = "postalCode") String postalCode){
        HttpHeaders httpHeaders = httpHeadersByFormat("json");
        Supplier supplier = new Supplier(id, company, city, country, address, phone, postalCode);
        try{
            supplierDao.update(supplier);
        }catch (DaoRuntimeException e){
            LOG.error("StoreRestController, updateSupplier: can't update supplier with parameters: id = " + id + ", company = "
                    + company + ", city = " + city + ", country = " + country + ", address = " + address + ", phone = " + phone
                    + ", postalCode = " + postalCode, e);
            throw new RestServiceException(e.getMessage(), "json");
        }
        return ResponseEntity.ok().headers(httpHeaders).body("{\"msg\":\"success\"}");
    }

    @PostMapping("/suppliers/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity addSupplier(@RequestParam(name="company") String company, @RequestParam(name="city") String city,
                                      @RequestParam(name="country") String country, @RequestParam(name="address") String address,
                                      @RequestParam(name="phone") String phone, @RequestParam(name="postalCode") String postalCode){
        HttpHeaders httpHeaders = httpHeadersByFormat("json");
        Supplier supplier = new Supplier(company, city, country, address, phone, postalCode);
        try{
            supplierDao.save(supplier);
        }catch (DaoRuntimeException e){
            LOG.error("StoreRestController, addSupplier: can't post new supplier with data: company = " + company +
                    ", city = " + city + ", country = " + country + ", address = " + address + ", phone = " + phone +
                    ", postalCode = " + postalCode, e);
            throw new RestServiceException(e.getMessage(), "json");
        }
        return ResponseEntity.ok().headers(httpHeaders).body("{\"msg\":\"success\"}");
    }

    private HttpHeaders httpHeadersByFormat(String format){
        HttpHeaders httpHeaders = new HttpHeaders();
        if(format.equalsIgnoreCase("json")){
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        }else if(format.equalsIgnoreCase("xml")){
            httpHeaders.setContentType(MediaType.APPLICATION_XML);
        }else {
            LOG.error("StoreRestController, httpHeadersByFormat: this service doesn't support format: "  + format);
            throw new RestServiceException("Неизвестный формат - " + format + ", этот сервис его не поддерживает", format);
        }
        return httpHeaders;
    }
}
