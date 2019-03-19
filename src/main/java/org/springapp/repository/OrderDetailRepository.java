package org.springapp.repository;


import org.springapp.entity.Order;
import org.springapp.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
    Set<OrderDetail> findAllByOrder(Order order);
}
