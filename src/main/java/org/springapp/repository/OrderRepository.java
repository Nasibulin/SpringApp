package org.springapp.repository;


import org.springapp.entity.Order;
import org.springapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    Set<Order> findAllByUserOrderByIdAsc(User user);
    Order getOrderById(Integer id);
    Order getOrderByIdAndUser(Integer id, User user);
}
