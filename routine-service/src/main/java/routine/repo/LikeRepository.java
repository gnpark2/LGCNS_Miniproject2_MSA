// repo/LikeRepository.java
package routine.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import routine.entity.Like;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByRoutineIdAndUserId(Long routineId, Long userId);
    long countByRoutineId(Long routineId);
}
