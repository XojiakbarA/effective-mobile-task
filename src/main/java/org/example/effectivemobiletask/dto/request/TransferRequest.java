package org.example.effectivemobiletask.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TransferRequest {
    @NotNull(message = "username must not be null")
    @NotBlank(message = "username must not be empty")
    private String username;

    @NotNull(message = "amount must not be null")
    @Min(value = 100, message = "amount min value must be 100")
    private Double amount;
}
