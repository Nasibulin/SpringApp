
package org.springapp.service.orders;

import org.springapp.entity.OrderAddress;
import org.springapp.repository.OrderAddressRepository;
import org.springapp.service.orders.OrderAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderAddressImpl implements OrderAddressService {

    @Autowired
    OrderAddressRepository orderAddressRepository;
    
    
    @Override
    public OrderAddress saveOrUpdate(OrderAddress orderAddress) {
        return orderAddressRepository.save(orderAddress);
    }

    
    @Override
    public OrderAddress getOrderAddressByOrderId(Integer orderId) {
        return orderAddressRepository.findOneByOrderId(orderId);
    }
    
    
}
