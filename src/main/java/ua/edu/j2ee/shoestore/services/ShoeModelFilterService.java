package ua.edu.j2ee.shoestore.services;

import ua.edu.j2ee.shoestore.model.ShoeModel;

import java.util.List;
import java.util.Set;

public interface ShoeModelFilterService {

    List<ShoeModel> getModelsInStockByFilters(Set<String> wishedBrands,
                                              double minPrice,
                                              double maxPrice,
                                              Set<String> wishedTypes,
                                              Set<String> wishedSeasons,
                                              Set<String> wishedColors,
                                              Set<String> wishedGenders,
                                              Set<Integer> wishedSizes);

    List<ShoeModel> getModelsByFilters(Set<String> wishedBrands,
                                       double minPrice,
                                       double maxPrice,
                                       Set<String> wishedTypes,
                                       Set<String> wishedSeasons,
                                       Set<String> wishedColors,
                                       Set<String> wishedGenders,
                                       Set<Integer> wishedSizes);
}
