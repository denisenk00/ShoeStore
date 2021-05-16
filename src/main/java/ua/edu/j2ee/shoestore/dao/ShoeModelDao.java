package ua.edu.j2ee.shoestore.dao;

import ua.edu.j2ee.shoestore.model.ShoeModel;
import ua.edu.j2ee.shoestore.model.ShoeStatus;

import java.util.List;

public interface ShoeModelDao extends Dao<ShoeModel> {

    List<ShoeModel> getAllByStatus(ShoeStatus status);

}
