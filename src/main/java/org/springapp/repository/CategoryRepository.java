package org.springapp.repository;

import org.springapp.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    List<Category> findAll();
    List<Category> findByParentIdEqualsAndIdLessThan(Integer i, Integer j);
    List<Category> findByIdEquals(Integer id);
    List<Category> findByParentIdEquals(Integer id);
    Page<Category> findByParentIdEquals(Integer i, Pageable pageable);
}
