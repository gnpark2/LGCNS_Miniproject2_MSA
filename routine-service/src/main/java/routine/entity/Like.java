// entity/Like.java
package routine.entity;

import jakarta.persistence.*;
import lombok.Getter; import lombok.Setter;

@Entity
@Table(name="likes",
       uniqueConstraints = @UniqueConstraint(name="uk_likes_unique", columnNames={"routine_id","user_id"}))
@Getter @Setter
public class Like {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="like_id")
    private Long id;

    @Column(name="routine_id", nullable=false)
    private Long routineId;

    @Column(name="user_id", nullable=false)
    private Long userId;
}
