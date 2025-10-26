// web/dto/ToggleLikeRequest.java
package routine.web.dto;

import jakarta.validation.constraints.NotNull;
public record ToggleLikeRequest(
    @NotNull Long routineId,
    @NotNull Long userId
) {}
