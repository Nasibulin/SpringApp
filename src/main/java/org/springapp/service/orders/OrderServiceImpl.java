package org.springapp.service.orders;

import org.springapp.entity.Order;
import org.springapp.entity.OrderDetail;
import org.springapp.entity.User;
import org.springapp.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository repository;

    @Override
    public Set<Order> findAllByUserOrderByIdAsc(User user) {
        return repository.findAllByUserOrderByIdAsc(user);
    }

    @Override
    public Order getByOrderId(Integer id) {
        return repository.getOrderById(id);
    }

    @Override
    public Order getOrderByIdAndUser(Integer id, User user) {
        return repository.getOrderByIdAndUser(id, user);
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
