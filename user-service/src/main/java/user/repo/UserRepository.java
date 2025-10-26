package user.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import user.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByNickname(String nickname);
}
