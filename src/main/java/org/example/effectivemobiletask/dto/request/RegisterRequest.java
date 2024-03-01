package org.example.effectivemobiletask.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotNull(message = "fullName must not be null")
    @NotBlank(message = "fullName must not be empty")
    private String fullName;

    @NotNull(message = "username must not be null")
    @NotBlank(message = "username must not be empty")
    private String username;

    @NotNull(message = "initAmount must not be null")
    @Positive(message = "initAmount must be a positive")
    private Double initAmount;

    @NotNull(message = "phoneNumber must not be null")
    @NotBlank(message = "phoneNumber must not be empty")
    @Pattern(regexp = "[\\d]{10}", message = "phoneNumber must be 10 digits")
    private String phoneNumber;

    @NotNull(message = "email must not be null")
    @NotBlank(message = "email must not be empty")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", message = "email is not valid")
    private String email;

    @NotNull(message = "password must not be null")
    @NotBlank(message = "password must not be empty")
    private String password;
}
