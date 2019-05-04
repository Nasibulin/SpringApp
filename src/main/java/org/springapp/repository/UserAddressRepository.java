package org.springapp.repository;

import org.springapp.entity.User;
import org.springapp.entity.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserAddressRepository extends CrudRepository<UserAddress, String> {
    UserAddress findByUser(User user);

    UserAddress findByAddressId(Integer addressId);
}
