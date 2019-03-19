package org.springapp.service.order;


import org.springapp.entity.Order;
import org.springapp.entity.OrderDetail;

import java.util.Set;

public interface OrderDetailService {

    Set<OrderDetail> findAllByOrder (Order order);

}
