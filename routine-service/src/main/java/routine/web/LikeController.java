// web/LikeController.java
package routine.web;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import routine.entity.Like;
import routine.repo.LikeRepository;
import routine.web.dto.ToggleLikeRequest;

@RestController
@RequestMapping("/likes")
public class LikeController {
    private final LikeRepository repo;
    public LikeController(LikeRepository repo){ this.repo = repo; }

    @PostMapping("/toggle")
    public long toggle(@RequestBody @Valid ToggleLikeRequest req){
        var ex = repo.findByRoutineIdAndUserId(req.routineId(), req.userId());
        if (ex.isPresent()){
            repo.delete(ex.get());   // unlike
        } else {
            Like l = new Like();
            l.setRoutineId(req.routineId());
            l.setUserId(req.userId());
            repo.save(l);
        }
        return repo.countByRoutineId(req.routineId()); // 현재 좋아요 개수 반환
    }
}
