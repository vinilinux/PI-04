package br.com.pi4.model;

public class Product {
    private String id_product, name, description, status, imageProductPath, imageDefault;

    private double rate, price;
    private int amount;

    public Product() {
    }

    public Product(String id_product, String name, double rate, String description, double price, int amount,
                   String status) {
        this.id_product = id_product;
        this.name = name;
        this.rate = rate;
        this.description = description;
        this.price = price;
        this.amount = amount;
        this.status = status;
    }

    public Product(String name, double rate, String description, double price, int amount, String status) {
        this.name = name;
        this.rate = rate;
        this.description = description;
        this.price = price;
        this.amount = amount;
        this.status = status;
    }

    public String getId_product() {
        return id_product;
    }

    public void setId_product(String id_product) {
        this.id_product = id_product;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImageProductPath() {
        return imageProductPath;
    }

    public void setImageProductPath(String imageProductPath) {
        this.imageProductPath = imageProductPath;
    }

    public String getImageDefault() {
        return imageDefault;
    }

    public void setImageDefault(String imageDefault) {
        this.imageDefault = imageDefault;
    }
}
