// web/RoutineController.java
package routine.web;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import routine.entity.Routine;
import routine.repo.RoutineRepository;
import routine.web.dto.CreateRoutineRequest;
import routine.web.dto.RoutineResponse;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/routines")
public class RoutineController {
    private final RoutineRepository repo;

    public RoutineController(RoutineRepository repo) { this.repo = repo; }

    @GetMapping("/user/{userId}")
    public List<RoutineResponse> listByUser(@PathVariable Long userId) {
        return repo.findByUserIdOrderByRoutineDateDesc(userId).stream()
                .map(this::map)
                .toList();
    }

    @GetMapping("/user/{userId}/date/{date}")
    public RoutineResponse getByUserDate(@PathVariable Long userId, @PathVariable String date) {
        Routine r = repo.findByUserIdAndRoutineDate(userId, LocalDate.parse(date)).orElseThrow();
        return map(r);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RoutineResponse create(@RequestBody @Valid CreateRoutineRequest req) {
        LocalDate d = LocalDate.parse(req.routineDate());
        repo.findByUserIdAndRoutineDate(req.userId(), d).ifPresent(x -> {
            throw new IllegalArgumentException("Routine already exists for this date");
        });
        Routine r = new Routine();
        r.setUserId(req.userId()); r.setRoutineDate(d);
        r.setSleepHours(req.sleepHours());
        r.setExerciseType(req.exerciseType());
        r.setExerciseMinutes(req.exerciseMinutes());
        r.setMeals(req.meals());
        r.setWaterMl(req.waterMl());
        r.setNote(req.note());
        r = repo.save(r);
        return map(r);
    }

    private RoutineResponse map(Routine r){
        return new RoutineResponse(
            r.getId(), r.getUserId(), r.getRoutineDate().toString(),
            r.getSleepHours(), r.getExerciseType(), r.getExerciseMinutes(),
            r.getMeals(), r.getWaterMl(), r.getNote()
        );
    }
}
