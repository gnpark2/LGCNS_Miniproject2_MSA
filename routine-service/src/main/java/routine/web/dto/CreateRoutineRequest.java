// web/dto/CreateRoutineRequest.java
package routine.web.dto;

import jakarta.validation.constraints.*;
public record CreateRoutineRequest(
    @NotNull Long userId,
    @NotBlank String routineDate,    // "YYYY-MM-DD"
    @DecimalMin("0.0") @DecimalMax("16.0") Double sleepHours,
    @Pattern(regexp="RUN|WALK|GYM|ETC") String exerciseType,
    @Min(0) @Max(600) Integer exerciseMinutes,
    @Size(max=1000) String meals,
    @Min(0) @Max(10000) Integer waterMl,
    String note
) {}
