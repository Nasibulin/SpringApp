package org.springapp.repository;

import org.springapp.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    List<Category> findByParentIdEquals(int i);
    List<Category> findAll();
    List<Category> findByParentIdEqualsAndIdLessThan(int i, int j);
    List<Category> findByIdEquals(Integer id);
}
