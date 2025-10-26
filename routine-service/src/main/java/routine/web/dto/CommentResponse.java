// web/dto/CommentResponse.java
package routine.web.dto;

public record CommentResponse(
    Long commentId,
    Long routineId,
    Long userId,
    String content
) {}
