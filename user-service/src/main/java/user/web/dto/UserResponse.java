// web/dto/UserResponse.java
package user.web.dto;

public record UserResponse(
        Long userId,
        String email,
        String username,
        String nickname
) {}
