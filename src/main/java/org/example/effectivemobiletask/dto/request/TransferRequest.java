package org.example.effectivemobiletask.dto.request;

import lombok.Data;

@Data
public class TransferRequest {
    private String username;
    private Double amount;
}
