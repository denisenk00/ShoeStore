package ua.edu.j2ee.shoestore.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.edu.j2ee.shoestore.dao.ShoeModelDao;
import ua.edu.j2ee.shoestore.model.ShoeModel;
import ua.edu.j2ee.shoestore.services.ShoeModelFilterService;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/rest/model")
public class ShoeModelRestController {

    private ShoeModelDao modelDao;
    private ShoeModelFilterService modelFilterService;

    @Autowired
    public ShoeModelRestController(ShoeModelDao modelDao, ShoeModelFilterService modelFilterService) {
        this.modelDao = modelDao;
        this.modelFilterService = modelFilterService;
    }

    @GetMapping(value = "/getAll", produces = {"application/json", "application/xml"})
    public ResponseEntity getAllModels(@RequestParam(name = "format", required = false, defaultValue = "json") String format,
                                       @RequestParam(name = "status", required = false) String status){
        HttpHeaders httpHeaders = new HttpHeaders();
        if(format.equals("json")){
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        }else if(format.equals("xml")){
            httpHeaders.setContentType(MediaType.APPLICATION_XML);
        }else {
            throw new RuntimeException("Invalid format - This service doesn't exist in that format");
        }
        List<ShoeModel> shoeModels;
        if(status == null){
            shoeModels = modelDao.getAll();
        } else{
            shoeModels = modelDao.getAllByStatus(status);
        }
        return ResponseEntity.ok().headers(httpHeaders).body(shoeModels);
    }


    @GetMapping(value = "/search", produces = {"application/json", "application/xml"})
    public ResponseEntity searchModels(@RequestParam("seasons") Set<String> seasons, @RequestParam("types") Set<String> types,
                                       @RequestParam("brands") Set<String> brands, @RequestParam("colors") Set<String> colors,
                                       @RequestParam("sizes") Set<Integer> sizes, @RequestParam("genders") Set<String> genders,
                                       @RequestParam("minPrice") double minPrice, @RequestParam("maxPrice") double maxPrice,
                                       @RequestParam(value = "status", required = false) String status,
                                       @RequestParam(required = false, name = "format", defaultValue = "json") String format){
        HttpHeaders httpHeaders = new HttpHeaders();
        if(format.equals("json")){
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        }else if(format.equals("xml")){
            httpHeaders.setContentType(MediaType.APPLICATION_XML);
        }else {
            throw new RuntimeException("Invalid format - This service doesn't exist in that format");
        }
        List<ShoeModel> shoeModels = modelFilterService.getModelsByFilters(brands, minPrice, maxPrice, types, seasons, colors, genders, sizes, status);
        return ResponseEntity.ok().headers(httpHeaders).body(shoeModels);
    }

    @GetMapping(value = "/", produces = {"application/json", "application/xml"})
    public ResponseEntity getModel(@RequestParam(name = "modelId") int modelId,
                                   @RequestParam(required = false, name = "format", defaultValue = "json") String format){
        HttpHeaders httpHeaders = new HttpHeaders();
        if(format.equals("json")){
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        }else if(format.equals("xml")){
            httpHeaders.setContentType(MediaType.APPLICATION_XML);
        }else {
            throw new RuntimeException("Invalid format - This service doesn't exist in that format");
        }
        ShoeModel model = modelDao.get(modelId);
        return ResponseEntity.ok().headers(httpHeaders).body(model);
    }
}
