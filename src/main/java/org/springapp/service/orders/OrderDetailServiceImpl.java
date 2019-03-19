package org.springapp.service.orders;

import org.springapp.entity.Order;
import org.springapp.entity.OrderDetail;
import org.springapp.entity.Product;
import org.springapp.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private OrderDetailRepository repository;

    @Override
    public Set<OrderDetail> findAllByOrder(Order order) {
        return repository.findAllByOrder(order);
    }

    @Override
    public void saveOrderDetail(OrderDetail orderDetail) {
        repository.save(orderDetail);
    }

    @Override
    public void updateOrderDetail(Integer id, Product product, Integer quantity) {
        OrderDetail updated = repository.getOne(id);
        updated.setProduct(product);
        updated.setQuantity(quantity);
        repository.save(updated);
    }

    @Override
    public void deleteOrderDetail(Integer id) {
        repository.deleteById(id);
    }
}
