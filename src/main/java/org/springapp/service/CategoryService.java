package org.springapp.service;

import org.springapp.entity.Category;

import java.util.List;

public interface CategoryService {
    Category getCategoryById(Integer id);

    List<Category> findByParentIdEquals(Integer i);
    List<Category> findAll();
    List<Category> findByIdEquals(Integer id);
    List<Category> findByParentIdEqualsAndIdLessThan(Integer i, Integer j);

}
