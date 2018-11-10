package com.postscriptum.ThePubLib.repos;

import com.postscriptum.ThePubLib.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
