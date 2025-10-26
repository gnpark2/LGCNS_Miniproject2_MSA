package user.web;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import user.entity.User;
import user.repo.UserRepository;
import user.web.dto.CreateUserRequest;
import user.web.dto.UpdateNicknameRequest;
import user.web.dto.UserResponse;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserRepository repo;

    public UserController(UserRepository repo) { this.repo = repo; }

    @GetMapping
    public List<UserResponse> list() {
        return repo.findAll().stream()
                .map(u -> new UserResponse(u.getId(), u.getEmail(), u.getUsername(), u.getNickname()))
                .toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse create(@RequestBody @Valid CreateUserRequest req) {
        if (repo.findByEmail(req.email()).isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }
        if (repo.existsByNickname(req.nickname())) {
            throw new IllegalArgumentException("Nickname already exists");
        }
        User u = new User();
        u.setEmail(req.email());
        u.setUsername(req.username());
        u.setNickname(req.nickname());
        // 데모: 평문 금지. 실제는 BCrypt 사용.
        u.setPasswordHash("PLAINTEXT_FOR_DEMO:" + req.password());
        u = repo.save(u);
        return new UserResponse(u.getId(), u.getEmail(), u.getUsername(), u.getNickname());
    }

    @PatchMapping("/{id}/nickname")
    public UserResponse updateNickname(@PathVariable Long id, @RequestBody @Valid UpdateNicknameRequest req) {
        User u = repo.findById(id).orElseThrow();
        if (repo.existsByNickname(req.nickname())) {
            throw new IllegalArgumentException("Nickname already exists");
        }
        u.setNickname(req.nickname());
        u = repo.save(u);
        return new UserResponse(u.getId(), u.getEmail(), u.getUsername(), u.getNickname());
    }
}
