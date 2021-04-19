package com.khovaylo.app.repository;

import com.khovaylo.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Pavel Khovaylo
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByLogin(String login);

//    @Modifying
//    @Query("UPDATE User u SET u.cashBalance = :cashBalance WHERE u.login = :login")
//    void update(@Param(value = "cashBalance") BigDecimal cashBalance, @Param(value = "login") String login);
}
