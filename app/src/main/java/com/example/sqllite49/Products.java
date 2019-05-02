package com.example.sqllite49;

// for products table in database

public class Products {
    private int _id;                // NB name: primary key with underscore in Java
    private String productname;    // NB underscore prefix again (keep lower case for safety)

    public Products() {
        // empty constructor as well
    }

    // Alt+Insert for easy constructor, and getter and setter
    public Products(String productname) {
        this.productname = productname;
    }

    public int get_id() {
        return _id;
    }

    public String getProductname() {
        return productname;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }
}
