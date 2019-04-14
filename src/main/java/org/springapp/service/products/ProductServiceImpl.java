package org.springapp.service.products;

import org.springapp.entity.Category;
import org.springapp.entity.Product;
import org.springapp.repository.ProductRepository;
import org.springapp.service.products.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public List<Product> findByCategory(Category category) {
        return repository.findByCategory(category);
    }

    @Override
    public List<Product> findByCategoryInAndDescriptionContainingIgnoreCase(List<Category> list, String description) {
        return repository.findByCategoryInAndDescriptionContainingIgnoreCase(list, description);
    }

}
