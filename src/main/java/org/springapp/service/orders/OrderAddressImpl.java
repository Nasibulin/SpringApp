
package org.springapp.service.orders;

import org.springapp.entity.OrderAddress;
import org.springapp.repository.OrderAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
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
