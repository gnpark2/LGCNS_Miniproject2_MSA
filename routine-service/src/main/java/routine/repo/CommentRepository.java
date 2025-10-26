// repo/CommentRepository.java
package routine.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import routine.entity.Comment;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByRoutineIdOrderByIdAsc(Long routineId);
}
