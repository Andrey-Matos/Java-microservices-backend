package br.com.compassuol.sp.challenge.msorders.entity;

import java.math.BigDecimal;

public record OrderDto(long userId, String status, Integer[] products, String enderecoEntrega) {
}
