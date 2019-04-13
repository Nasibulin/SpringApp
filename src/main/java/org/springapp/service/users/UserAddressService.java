package org.springapp.service.users;

import org.springapp.entity.User;
import org.springapp.entity.UserAddress;
import org.springapp.repository.UserAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAddressService {

    @Autowired
    private UserAddressRepository userAddressRepository;

    public UserAddress save(UserAddress userAddress) {
        return userAddressRepository.save(userAddress);
    }

    public UserAddress getAddressByUser(User user) {
        return userAddressRepository.findByUser(user);
    }

}
