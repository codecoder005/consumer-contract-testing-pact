package com.popcorn.model;

import lombok.*;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ComplexRequestObject {
    private Byte age;
    private Short salary;
    private Boolean married;
    private Character gender;
    private Integer count;
    private Float height;
    private Long serialNumber;
    private Double amount;
    private String name;
    private UUID uuid;
    private BigDecimal revenue;
    private Map<String, Object> additionalProperties;
}
