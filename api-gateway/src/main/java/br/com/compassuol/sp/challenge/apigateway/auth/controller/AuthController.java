package br.com.compassuol.sp.challenge.apigateway.auth.controller;


import br.com.compassuol.sp.challenge.apigateway.auth.core.TokenHandler;
import br.com.compassuol.sp.challenge.apigateway.auth.domain.AuthDTO;
import br.com.compassuol.sp.challenge.apigateway.auth.domain.LoginResponseDTO;
import br.com.compassuol.sp.challenge.apigateway.auth.domain.RegisterDTO;
import br.com.compassuol.sp.challenge.apigateway.auth.domain.UserEntity;
import br.com.compassuol.sp.challenge.apigateway.auth.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository repository;
    @Autowired
    private TokenHandler tokenService;


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthDTO credentials){
        var usernamePassword = new UsernamePasswordAuthenticationToken(credentials.login(), credentials.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((UserEntity) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO credentials){
        if(this.repository.findByLogin(credentials.login()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(credentials.password());
        UserEntity newUser = new UserEntity(credentials.login(), encryptedPassword, credentials.role());

        this.repository.save(newUser);

        return ResponseEntity.ok().build();
    }
}