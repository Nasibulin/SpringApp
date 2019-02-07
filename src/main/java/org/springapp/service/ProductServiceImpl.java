package org.springapp.service;

import org.springapp.entity.Product;
import org.springapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository repository;

    @Autowired
    public void setProductRepository(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product getProductById(Integer id) {
        return repository.getOne(id);
    }

    @Override
    public List<Product> findByIdEquals(Integer i) {
        return repository.findByIdEquals(i);
    }

    @Override
    public List<Product> findAll() {
        return repository.findAll();
    }


}
