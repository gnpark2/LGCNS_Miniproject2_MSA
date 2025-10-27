package routine.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "routines",
       uniqueConstraints = @UniqueConstraint(name="uk_routines_user_date", columnNames={"user_id","routine_date"}))
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Routine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "routine_id")
    private Long routineId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "routine_date", nullable = false)
    private LocalDate routineDate;

    // DECIMAL(3,1) ↔ BigDecimal 사용 (precision/scale 지정 가능)
    @Column(name = "sleep_hours", precision = 3, scale = 1)
    private BigDecimal sleepHours;

    @Column(name = "exercise_type", length = 30)
    private String exerciseType;

    @Column(name = "exercise_minutes")
    private Integer exerciseMinutes;

    @Column(name = "meals", length = 1000)
    private String meals;

    @Column(name = "water_ml")
    private Integer waterMl;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String note;
}
