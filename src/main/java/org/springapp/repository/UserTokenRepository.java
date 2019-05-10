package org.springapp.repository;

import org.springapp.entity.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface UserTokenRepository extends JpaRepository<UserToken, String> {
    UserToken findByToken(String userToken);
}
