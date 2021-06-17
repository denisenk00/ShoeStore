package ua.edu.j2ee.shoestore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.edu.j2ee.shoestore.dao.interfaces.ShoeDao;
import ua.edu.j2ee.shoestore.dao.interfaces.ShoeModelDao;
import ua.edu.j2ee.shoestore.model.ShoeModel;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ShoeModelFilterService {

    @Autowired
    private ShoeModelDao shoeModelDao;

    @Autowired
    private ShoeDao shoeDao;

    public List<ShoeModel> getModelsByFilters(Set<String> wishedBrands,
                                                    double minPrice,
                                                    double maxPrice,
                                                    Set<String> wishedTypes,
                                                    Set<String> wishedSeasons,
                                                    Set<String> wishedColors,
                                                    Set<String> wishedGenders,
                                                    Set<Integer> wishedSizes,
                                                    String status) {
        List<ShoeModel> allModels;
        if(status == null) allModels = shoeModelDao.getAll();
        else allModels = shoeModelDao.getAllByStatus(status);

        List<ShoeModel> filteredModels;

        filteredModels = allModels.stream()
                .filter(model -> (maxPrice == 0 || model.getPrice() >= minPrice && model.getPrice() <= maxPrice)
                        && (wishedBrands.isEmpty() || wishedBrands.contains(model.getBrand()))
                        && (wishedTypes.isEmpty() || wishedTypes.contains(model.getType()))
                        && (wishedSeasons.isEmpty() || wishedSeasons.contains(model.getSeason()))
                        && (wishedColors.isEmpty() || wishedColors.contains(model.getColor()))
                        && (wishedGenders.isEmpty() || wishedGenders.contains(model.getGender()))
                        && (wishedSizes.isEmpty() || shoeDao.getAllByModel(model.getId()).stream()
                            .anyMatch(shoe -> wishedSizes.contains(shoe.getSize()))))
                .collect(Collectors.toList());

        return filteredModels;
    }
}
