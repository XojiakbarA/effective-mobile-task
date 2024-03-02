package org.example.effectivemobiletask.dto.request;

import lombok.Data;

@Data
public class FilterRequest {
    private String field;
    private String value;
}
