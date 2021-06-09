package ua.edu.j2ee.shoestore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.edu.j2ee.shoestore.dao.ShoeDao;
import ua.edu.j2ee.shoestore.model.Shoe;

@Controller
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/shoe")
public class ShoeController {

    private ShoeDao shoeDao;

    @Autowired
    public ShoeController(ShoeDao shoeDao) {
        this.shoeDao = shoeDao;
    }

    @PostMapping("/update")
    public void update(@RequestParam(name="id") int id, @RequestParam(name="status") String status){
        Shoe shoe = shoeDao.get(id);
        shoe.setStatus(status);
        shoeDao.update(shoe);
    }
}
