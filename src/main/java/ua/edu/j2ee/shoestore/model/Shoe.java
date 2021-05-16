package ua.edu.j2ee.shoestore.model;

public class Shoe {

    private int id;
    private int modelId;
    private int size;
    private String status;

    public Shoe() {

    }

    public Shoe(int id, int modelId, int size, String status) {
        this.id = id;
        this.modelId = modelId;
        this.size = size;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getModelId() {
        return modelId;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
