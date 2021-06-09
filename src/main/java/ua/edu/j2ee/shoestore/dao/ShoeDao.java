package ua.edu.j2ee.shoestore.dao;

import ua.edu.j2ee.shoestore.model.Shoe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

public interface ShoeDao extends Dao<Shoe> {

    String IN_STOCK = "In stock";
    String SOLD = "Sold";
    String BOOKED = "Booked";
    String RETURN = "Return";
    String UNKNOWN = "Unknown";

    List<Shoe> getAllByModel(int modelId);

    Shoe getAnyExistingByParams(int modelId, int size);

}
