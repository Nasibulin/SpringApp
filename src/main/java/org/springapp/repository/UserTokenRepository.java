package org.springapp.repository;

import org.springapp.entity.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTokenRepository extends JpaRepository<UserToken, String> {

    UserToken findOne(String token);
}
