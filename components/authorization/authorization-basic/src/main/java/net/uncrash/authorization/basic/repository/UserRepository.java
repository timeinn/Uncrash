package net.uncrash.authorization.basic.repository;

import net.uncrash.authorization.basic.domain.DefaultUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Sendya
 */
public interface UserRepository extends JpaRepository<DefaultUser, String> {
}
