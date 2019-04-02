package io.tooko.uncrash.authorization.simple.dao;

import io.tooko.uncrash.authorization.simple.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {

    User findByUsername(String username);

}
