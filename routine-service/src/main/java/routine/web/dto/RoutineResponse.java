// web/dto/RoutineResponse.java
package routine.web.dto;

public record RoutineResponse(
    Long routineId,
    Long userId,
    String routineDate,
    Double sleepHours,
    String exerciseType,
    Integer exerciseMinutes,
    String meals,
    Integer waterMl,
    String note
) {}
