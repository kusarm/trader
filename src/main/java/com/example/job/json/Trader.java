package com.example.job.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Trader {
    private int ID;
    private String name;
    private Double taxAmount;
    private Double taxRate;
}
