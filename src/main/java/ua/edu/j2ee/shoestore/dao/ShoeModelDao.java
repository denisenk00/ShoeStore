package ua.edu.j2ee.shoestore.dao;

import ua.edu.j2ee.shoestore.model.ShoeModel;

import java.util.List;
import java.util.Set;

public interface ShoeModelDao extends Dao<ShoeModel> {

    List<ShoeModel> getAllByStatus(String status);

    Set<String> getExistingBrands();

    double getExistingMinPrice();

    double getExistingMaxPrice();

    Set<String> getExistingTypes();

    Set<String> getExistingColors();

    Set<Integer> getExistingSizes();

}
