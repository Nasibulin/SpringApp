package org.springapp.service;

import org.springapp.entity.Category;

import java.util.List;

public interface ProductService {
    Category getCategoryById(Integer id);

    List<Category> findByParentIdEquals(int i);
    List<Category> findAll();
    List<Category> findByParentIdEqualsAndIdLessThan(int i, int j);

}
