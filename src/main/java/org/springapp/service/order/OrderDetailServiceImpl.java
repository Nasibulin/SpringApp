package org.springapp.service.order;

import org.springapp.entity.Order;
import org.springapp.entity.OrderDetail;
import org.springapp.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;


public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private OrderDetailRepository repository;

    @Override
    public Set<OrderDetail> findAllByOrder(Order order) {
        return repository.findAllByOrder(order);
    }
}
