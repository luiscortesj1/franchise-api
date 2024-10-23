package com.example.franchiseapi.models;

public class ProductStockResponse {
    private String branchName;
    private String productName;
    private int stock;

    public ProductStockResponse(String branchName, String productName, int stock) {
        this.branchName = branchName;
        this.productName = productName;
        this.stock = stock;
    }

    // Getters and setters
    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
