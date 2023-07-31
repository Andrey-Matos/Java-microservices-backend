package br.com.compassuol.sp.challenge.msorders.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CustomMessage(@JsonProperty("id") Long id)
        implements Serializable {
}