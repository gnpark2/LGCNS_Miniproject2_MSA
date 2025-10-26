// web/dto/CreateCommentRequest.java
package routine.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateCommentRequest(
    @NotNull Long routineId,
    @NotNull Long userId,
    @NotBlank String content
) {}
