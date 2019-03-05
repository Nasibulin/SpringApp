package org.springapp.service.categories;

import org.springapp.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {
    List<Category> findByParentIdEquals(Integer i);
    List<Category> findAll();
    Category findByIdEquals(Integer id);
    Page<Category> findByParentIdEquals(Integer i, Pageable pageable);
    List<Category> findCatPathById(Integer id);
    List<Category> findCatnameByLevel (Integer level);
}
