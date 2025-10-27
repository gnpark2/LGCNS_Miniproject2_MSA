package routine.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import routine.entity.Routine;
import routine.repo.RoutineRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/routines")
@RequiredArgsConstructor
public class RoutineController {

    private final RoutineRepository routineRepository;

    // GET /routines/user/{userId}
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Routine>> findByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(routineRepository.findByUserIdOrderByRoutineDateDesc(userId));
    }

    // POST /routines
    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateRoutineRequest req) {
        // 중복 날짜 방지
        if (routineRepository.findByUserIdAndRoutineDate(req.userId(), req.routineDate()).isPresent()) {
            return ResponseEntity.status(409).body("Routine already exists for date");
        }
        Routine r = Routine.builder()
                .userId(req.userId())
                .routineDate(req.routineDate())
                .sleepHours(req.sleepHours() == null ? null : new BigDecimal(req.sleepHours().toString()))
                .exerciseType(req.exerciseType())
                .exerciseMinutes(req.exerciseMinutes())
                .meals(req.meals())
                .waterMl(req.waterMl())
                .note(req.note())
                .build();
        return ResponseEntity.ok(routineRepository.save(r));
    }

    // 간단 요청 DTO
    public record CreateRoutineRequest(
            Long userId,
            LocalDate routineDate,
            BigDecimal sleepHours,
            String exerciseType,
            Integer exerciseMinutes,
            String meals,
            Integer waterMl,
            String note
    ) {}
}
