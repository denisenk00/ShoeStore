package ua.edu.j2ee.shoestore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.edu.j2ee.shoestore.dao.Dao;
import ua.edu.j2ee.shoestore.dao.SupplierDaoImpl;
import ua.edu.j2ee.shoestore.model.ShoeModel;
import ua.edu.j2ee.shoestore.model.Supplier;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SupplierFilterService {

    @Autowired
    SupplierDaoImpl supplierDao;

    public List<Supplier> getSuppliersByFilters(Set<String> companies,
                                                Set<String> cities,
                                                Set<String> addresses,
                                                Set<String> phones,
                                                Set<String> postalCodes) {
        List<Supplier> allSuppliers;
        allSuppliers = supplierDao.getAll();
        List<Supplier> filteredSuppliers;

        filteredSuppliers = allSuppliers.stream()
                .filter(supplier -> (companies.isEmpty() || companies.contains(supplier.getCompany()))
                        && (cities.isEmpty() || cities.contains(supplier.getCity()))
                        && (addresses.isEmpty() || addresses.contains(supplier.getAddress()))
                        && (phones.isEmpty() || phones.contains(supplier.getPhone()))
                        && (postalCodes.isEmpty() || postalCodes.contains(supplier.getPostalCode())))
                .collect(Collectors.toList());

        return filteredSuppliers;
    }
}