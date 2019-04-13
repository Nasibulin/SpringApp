
package org.springapp.repository;

import org.springapp.entity.OrderAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface OrderAddressRepository extends JpaRepository<OrderAddress, Integer> {
    OrderAddress findOneByOrderId(Integer orderId);
}
