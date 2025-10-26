// entity/Comment.java
package routine.entity;

import jakarta.persistence.*;
import lombok.Getter; import lombok.Setter;
import java.time.OffsetDateTime;

@Entity
@Table(name="comments", indexes = {
        @Index(name="idx_comments_routine_created", columnList="routine_id,created_at")
})
@Getter @Setter
public class Comment {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="comment_id")
    private Long id;

    @Column(name="routine_id", nullable=false)
    private Long routineId;

    @Column(name="user_id", nullable=false)
    private Long userId;

    @Column(name="content", columnDefinition="TEXT", nullable=false)
    private String content;
}
