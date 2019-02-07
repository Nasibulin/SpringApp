package org.springapp.service;

import org.springapp.entity.Product;

import java.util.List;

public interface ProductService {
    Product getProductById(Integer id);
    List<Product> findByIdEquals(Integer i);
    List<Product> findAll();
}
