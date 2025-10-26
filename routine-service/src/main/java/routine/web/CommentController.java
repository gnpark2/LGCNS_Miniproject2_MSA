// web/CommentController.java
package routine.web;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import routine.entity.Comment;
import routine.repo.CommentRepository;
import routine.web.dto.CommentResponse;
import routine.web.dto.CreateCommentRequest;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {
    private final CommentRepository repo;
    public CommentController(CommentRepository repo){ this.repo = repo; }

    @GetMapping("/routine/{routineId}")
    public List<CommentResponse> list(@PathVariable Long routineId){
        return repo.findByRoutineIdOrderByIdAsc(routineId).stream()
                .map(c -> new CommentResponse(c.getId(), c.getRoutineId(), c.getUserId(), c.getContent()))
                .toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentResponse create(@RequestBody @Valid CreateCommentRequest req){
        Comment c = new Comment();
        c.setRoutineId(req.routineId());
        c.setUserId(req.userId());
        c.setContent(req.content());
        c = repo.save(c);
        return new CommentResponse(c.getId(), c.getRoutineId(), c.getUserId(), c.getContent());
    }
}
