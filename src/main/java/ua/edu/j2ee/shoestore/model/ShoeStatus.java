package ua.edu.j2ee.shoestore.model;

public enum ShoeStatus {
    IN_STOCK,
    BOOKED,
    SOLD,
    AWAITING;

    @Override
    public String toString() {
        return name().replace('_', ' ');
    }
}
