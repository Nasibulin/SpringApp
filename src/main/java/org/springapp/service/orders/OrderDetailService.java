package org.springapp.service.orders;


import org.springapp.entity.Order;
import org.springapp.entity.OrderDetail;
import org.springapp.entity.Product;

import java.util.Set;

public interface OrderDetailService {

    Set<OrderDetail> findAllByOrder(Order order);

    void saveOrderDetail(OrderDetail orderDetail);

    void updateOrderDetail(Integer id, Product product, Integer quantity);

    void deleteOrderDetail(Integer id);

}
