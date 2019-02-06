package org.springapp.service;

import org.springapp.entity.Note;

import java.util.List;

public interface CategoryService {
    Note getNoteById(Integer id);
    void saveNote(Note note);
    void updateNote(Integer id, String message, boolean done);
    void deleteNote(Integer id);
    List<Note> findAllByOrderByDateAsc();
    List<Note> findAllByOrderByDateDesc();
    //List<Note> findAll();
}
