// web/dto/CreateUserRequest.java
package user.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateUserRequest(
        @Email @NotBlank String email,
        @NotBlank @Size(max=50) String username,
        @NotBlank @Size(max=50) String nickname,
        @NotBlank String password
) {}
