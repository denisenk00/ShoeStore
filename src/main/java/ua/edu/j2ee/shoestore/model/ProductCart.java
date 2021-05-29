package ua.edu.j2ee.shoestore.model;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class ProductCart {
    private List<Shoe> shoeList;
    private Set<String> wishedBrands;
    private Set<String> wishedTypes;
    private Set<String> wishedSeasons;
    private Set<String> wishedColors;
    private Set<String> wishedGenders;
    private Set<Integer> wishedSizes;
    private double wishedMaxPrice;
    private double wishedMinPrice;

    public ProductCart() {
        shoeList = new LinkedList<>();
        wishedBrands = new HashSet<>();
        wishedTypes = new HashSet<>();
        wishedSeasons = new HashSet<>();
        wishedColors = new HashSet<>();
        wishedGenders = new HashSet<>();
        wishedSizes = new HashSet<>();
    }

    public void updateFilters(Set<String> wishedBrands,
                              Set<String> wishedTypes,
                              Set<String> wishedSeasons,
                              Set<String> wishedColors,
                              Set<String> wishedGenders,
                              Set<Integer> wishedSizes,
                              double wishedMaxPrice,
                              double wishedMinPrice) {
        this.wishedBrands = wishedBrands;
        this.wishedTypes = wishedTypes;
        this.wishedSeasons = wishedSeasons;
        this.wishedColors = wishedColors;
        this.wishedGenders = wishedGenders;
        this.wishedSizes = wishedSizes;
        this.wishedMaxPrice = wishedMaxPrice;
        this.wishedMinPrice = wishedMinPrice;
    }

    public void addToCart(Shoe shoe) {
        shoeList.add(shoe);
    }

    public void removeFromCart(Shoe shoe) {
        shoeList.add(shoe);
    }
}
