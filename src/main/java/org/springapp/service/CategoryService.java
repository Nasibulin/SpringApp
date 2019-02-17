package org.springapp.service;

import org.springapp.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {
    Category getCategoryById(Integer id);

    List<Category> findByParentIdEquals(Integer i);
    List<Category> findAll();
    List<Category> findByIdEquals(Integer id);
    List<Category> findByParentIdEqualsAndIdLessThan(Integer i, Integer j);
    Page<Category> findByParentIdEquals(Integer i, Pageable pageable);
    List<Category> findCatnameById(Integer id);
    List<Category> findCatnameByLevel (Integer level);

}
