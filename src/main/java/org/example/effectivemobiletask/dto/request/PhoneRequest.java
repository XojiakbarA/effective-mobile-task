package org.example.effectivemobiletask.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class PhoneRequest {
    @NotNull(message = "phoneNumber must not be null")
    @NotBlank(message = "phoneNumber must not be empty")
    @Pattern(regexp = "[\\d]{10}", message = "phoneNumber must be 10 digits")
    private String phoneNumber;
}
