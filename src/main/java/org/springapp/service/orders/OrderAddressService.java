
package org.springapp.service.orders;


import org.springapp.entity.OrderAddress;

public interface OrderAddressService {
    public OrderAddress saveOrUpdate(OrderAddress orderAddress);
    public OrderAddress getOrderAddressByOrderId(Integer orderId);
}
