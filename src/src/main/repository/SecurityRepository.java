package main.repository;

import main.security.UserSecurity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SecurityRepository extends JpaRepository<UserSecurity, Long> {
    Optional<UserSecurity> findByLogin(String login);
}

