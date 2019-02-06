package org.springapp.service;

import org.springapp.entity.Category;
import org.springapp.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    private CategoryRepository repository;

    @Autowired
    public void setProductRepository(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Category getCategoryById(Integer id) {
        return repository.getOne(id);
    }

//    @Override
//    public void saveNote(Note note) {
//        repository.save(note);
//    }
//
//    @Override
//    public void updateNote(Integer id, String message, boolean done) {
//        Note updated = repository.getOne(id);
//        updated.setDone(done);
//        updated.setMessage(message);
//        repository.save(updated);
//    }
//
//    @Override
//    public void deleteNote(Integer id) {
//        repository.deleteById(id);
//    }

    @Override
    public List<Category> findByParentIdEquals(int i) {
        return repository.findByParentIdEquals(i);
    }

    @Override
    public List<Category> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Category> findByParentIdEqualsAndIdLessThan(int i, int j) {
      return repository.findByParentIdEqualsAndIdLessThan(i, j);
    }


}
