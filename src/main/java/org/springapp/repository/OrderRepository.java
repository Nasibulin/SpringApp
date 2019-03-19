package org.springapp.repository;


import org.springapp.entity.Order;
import org.springapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    Set<Order> findAllByUser(User user);

    Order getOrderById(Integer id);
}
