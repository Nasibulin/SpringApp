package org.springapp.service;

import org.springapp.entity.Category;
import org.springapp.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    private CategoryRepository repository;
    @PersistenceContext
    private EntityManager em;
    @Autowired
    public void setProductRepository(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Category getCategoryById(Integer id) {
        return repository.getOne(id);
    }

    @Override
    public List<Category> findByParentIdEquals(Integer id) {
        return repository.findByParentIdEquals(id);
    }
    @Override
    public Page<Category> findByParentIdEquals(Integer i, Pageable pageable){

        return repository.findByParentIdEquals(i, pageable);

    }


    @Override
    public List<Category> findAll() {
        return repository.findAll();
    }

    @Override
    public Category findByIdEquals(Integer id) {
        return repository.findByIdEquals(id);
    }

    @Override
    public List<Category> findByParentIdEqualsAndIdLessThan(Integer i, Integer j) {
      return repository.findByParentIdEqualsAndIdLessThan(i, j);
    }

    @Override
    public List<Category> findCatPathById(Integer id) {
        return repository.findCatPathById(id);
    }

    @Override
    public List<Category> findCatnameByLevel(Integer level) {
        return repository.findCatnameByLevel(level);
    }

}
