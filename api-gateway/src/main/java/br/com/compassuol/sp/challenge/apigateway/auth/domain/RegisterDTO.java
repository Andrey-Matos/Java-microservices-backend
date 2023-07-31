package br.com.compassuol.sp.challenge.apigateway.auth.domain;

public record RegisterDTO(String login, String password, UserRole role) {
}