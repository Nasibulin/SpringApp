package org.springapp.service.order;

import org.springapp.entity.Order;
import org.springapp.entity.OrderDetail;
import org.springapp.entity.User;
import org.springapp.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository repository;

    @Override
    public Set<Order> findAllByUser(User user) {
        return repository.findAllByUser(user);
    }

    @Override
    public Order getByOrderId(Integer id) {
        return repository.getOrderById(id);
    }

    @Override
    public void saveOrder(Order order) {
        repository.save(order);
    }

    @Override
    public void updateOrder(Integer id, Set<OrderDetail> orderDetails) {
        Order updated = repository.getOrderById(id);
        updated.setOrderDetailsSet(orderDetails);
        repository.save(updated);
    }

    @Override
    public void deleteOrder(Integer id) {
        repository.deleteById(id);
    }
}
