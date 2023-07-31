package br.com.compassuol.sp.challenge.msproducts.models;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CustomMessage(@JsonProperty("name") String name,
                            @JsonProperty("description") String description,
                            @JsonProperty("price") BigDecimal price,
                            @JsonProperty("status") String status)
        implements Serializable {
}