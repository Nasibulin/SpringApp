package org.springapp.repository;

import org.springapp.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByIdEquals(int i);
    List<Product> findAll();
    List<Product> findByPartNumberEquals(String partnumber);
}
