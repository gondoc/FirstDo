package com.ese.gondo.board3.Entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User AS u WHERE u.userId= :userId")
    User findByUserId(@Param("userId") String userId);
}
