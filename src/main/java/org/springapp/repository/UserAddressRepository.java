package org.springapp.repository;

import org.springapp.entity.UserAddress;
import org.springframework.data.repository.CrudRepository;

public interface UserAddressRepository extends CrudRepository<UserAddress, String> {
    UserAddress findByUserIdAndStatus(String userId, int status);
    
    UserAddress findByAdressIdAndStatus(Long adressId, int status);
}
