package org.springapp.service;

import org.springapp.entity.Category;

import java.util.List;

public interface CategoryService {
    Category getCategoryById(Integer id);
//    void saveNote(Category category);
//    void updateNote(Integer id, String message, boolean done);
//    void deleteNote(Integer id);
    List<Category> findByParentIdEquals(int i);
    List<Category> findAll();
    List<Category> findByParentIdEqualsAndIdLessThan(int i, int j);
    //List<Note> findAll();
}
