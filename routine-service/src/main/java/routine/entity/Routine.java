// entity/Routine.java
package routine.entity;

import jakarta.persistence.*;
import lombok.Getter; import lombok.Setter;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Entity
@Table(name="routines",
       uniqueConstraints = @UniqueConstraint(name="uk_routines_user_date", columnNames={"user_id","routine_date"}))
@Getter @Setter
public class Routine {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="routine_id")
    private Long id;

    @Column(name="user_id", nullable=false)
    private Long userId;   // FK to users.user_id

    @Column(name="routine_date", nullable=false)
    private LocalDate routineDate;

    @Column(name="sleep_hours", precision=3, scale=1)
    private Double sleepHours;

    @Column(name="exercise_type", length=30)
    private String exerciseType;

    @Column(name="exercise_minutes")
    private Integer exerciseMinutes;

    @Column(name="meals", length=1000)
    private String meals;

    @Column(name="water_ml")
    private Integer waterMl;

    @Column(name="note", columnDefinition="TEXT")
    private String note;
}
