package org.springapp.service.orders;


import org.springapp.entity.Order;
import org.springapp.entity.OrderDetail;
import org.springapp.entity.User;

import java.util.Set;

public interface OrderService {

    Set<Order> findAllByUserOrderByIdAsc(User user);

    Order getByOrderId(Integer id);

    Order getOrderByIdAndUser(Integer id, User user);

    void saveOrder(Order order);

    void updateOrder(Integer id, Set<OrderDetail> orderDetails);

    void deleteOrder(Integer id);

}
