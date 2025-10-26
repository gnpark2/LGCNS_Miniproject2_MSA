// repo/RoutineRepository.java
package routine.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import routine.entity.Routine;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RoutineRepository extends JpaRepository<Routine, Long> {
    List<Routine> findByUserIdOrderByRoutineDateDesc(Long userId);
    Optional<Routine> findByUserIdAndRoutineDate(Long userId, LocalDate date);
}
