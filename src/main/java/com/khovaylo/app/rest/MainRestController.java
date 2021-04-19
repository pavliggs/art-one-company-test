package com.khovaylo.app.rest;

import com.khovaylo.app.dto.AuthenticationUserDto;
import com.khovaylo.app.model.Payment;
import com.khovaylo.app.model.User;
import com.khovaylo.app.security.jwt.JwtProvider;
import com.khovaylo.app.service.PaymentService;
import com.khovaylo.app.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Pavel Khovaylo
 */
@RestController
@RequestMapping("/api/auth")
public class MainRestController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final PaymentService paymentService;
    private final JwtProvider jwtProvider;

    public MainRestController(AuthenticationManager authenticationManager,
                              UserService userService,
                              PaymentService paymentService,
                              JwtProvider jwtProvider) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.paymentService = paymentService;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@NotNull @RequestBody AuthenticationUserDto request) {
        try {
            String login = request.getLogin();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, request.getPassword()));
            String token = jwtProvider.createToken(login);
            Map<Object, Object> response = new HashMap<>();
            response.put("login", login);
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException ex) {
            return new ResponseEntity<>("Invalid email/password combination", HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);
    }

    @PostMapping("/payment")
    public void payment(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        User user = userService.get(jwtProvider.getUsername(token));
        Payment payment = new Payment();
        payment.setUser(user);
        paymentService.create(payment);
        userService.refresh(user);
    }
}
