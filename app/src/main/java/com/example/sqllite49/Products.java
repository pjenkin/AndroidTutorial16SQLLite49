package com.example.sqllite49;

// for database

public class Products {
    private int _id;                // NB name: primary key with underscore in Java
    private String _productName;    // NB underscore prefix again

    public Products() {
        // empty constructor as well
    }

    // Alt+Insert for easy constructor, and getter and setter
    public Products(String _productName) {
        this._productName = _productName;
    }

    public int get_id() {
        return _id;
    }

    public String get_productName() {
        return _productName;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_productName(String _productName) {
        this._productName = _productName;
    }
}
