package ua.edu.j2ee.shoestore.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.edu.j2ee.shoestore.dao.ShoeDao;
import ua.edu.j2ee.shoestore.model.Shoe;

import java.util.List;

@RestController
@RequestMapping("/rest/shoe")
public class ShoeRestController {

    private ShoeDao shoeDao;

    @Autowired
    public ShoeRestController(ShoeDao shoeDao) {
        this.shoeDao = shoeDao;
    }

    @GetMapping(value = "/getAll", produces = {"application/json", "application/xml"})
    public ResponseEntity getAllShoes(@RequestParam(name = "format", required = false, defaultValue = "json") String format){
        HttpHeaders httpHeaders = new HttpHeaders();
        if(format.equals("json")){
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        }else if(format.equals("xml")){
            httpHeaders.setContentType(MediaType.APPLICATION_XML);
        }else {
            throw new RuntimeException("Invalid format - This service doesn't exist in that format");
        }
        List<Shoe> shoes = shoeDao.getAll();
        return ResponseEntity.ok().headers(httpHeaders).body(shoes);
    }

    @GetMapping(value = "/getByModelId")
    public ResponseEntity getShoesByModel(@RequestParam(name="modelId") int modelId,
                                          @RequestParam(name = "format", required = false, defaultValue = "json") String format){
        HttpHeaders httpHeaders = new HttpHeaders();
        if(format.equals("json")){
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        }else if(format.equals("xml")){
            httpHeaders.setContentType(MediaType.APPLICATION_XML);
        }else {
            throw new RuntimeException("Invalid format - This service doesn't exist in that format");
        }
        List<Shoe> shoes = shoeDao.getAllByModel(modelId);
        return ResponseEntity.ok().headers(httpHeaders).body(shoes);
    }
}
