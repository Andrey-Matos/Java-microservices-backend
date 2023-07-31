package br.com.compassuol.sp.challenge.apigateway.auth.repository;

import br.com.compassuol.sp.challenge.apigateway.auth.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
public interface UserRepository extends JpaRepository<UserEntity, String> {
    UserDetails findByLogin(String login);
}