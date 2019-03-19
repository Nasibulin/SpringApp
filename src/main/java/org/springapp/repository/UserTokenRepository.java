package org.springapp.repository;

import org.springapp.entity.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTokenRepository extends JpaRepository<UserToken, String> {

    UserToken findByTokenEquals(String token);

}
