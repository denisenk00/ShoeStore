package ua.edu.j2ee.shoestore.model;

import java.util.*;

public class ProductCart {
    private Map<Integer, Integer> shoeCart;
    private Set<String> wishedBrands;
    private Set<String> wishedTypes;
    private Set<String> wishedSeasons;
    private Set<String> wishedColors;
    private Set<String> wishedGenders;
    private Set<Integer> wishedSizes;
    private double wishedMaxPrice;
    private double wishedMinPrice;

    public ProductCart() {
        shoeCart = new HashMap<>();
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

    public void addToCart(int modelId, int size) {
        shoeCart.put(modelId, size);
    }

    public void removeFromCart(int modelId, int size) {
        shoeCart.remove(modelId, size);
    }

    public Map<Integer, Integer> getShoeCart() {
        return shoeCart;
    }

    public Set<String> getWishedBrands() {
        return wishedBrands;
    }

    public Set<String> getWishedTypes() {
        return wishedTypes;
    }

    public Set<String> getWishedSeasons() {
        return wishedSeasons;
    }

    public Set<String> getWishedColors() {
        return wishedColors;
    }

    public Set<String> getWishedGenders() {
        return wishedGenders;
    }

    public Set<Integer> getWishedSizes() {
        return wishedSizes;
    }

    public double getWishedMaxPrice() {
        return wishedMaxPrice;
    }

    public double getWishedMinPrice() {
        return wishedMinPrice;
    }
}
