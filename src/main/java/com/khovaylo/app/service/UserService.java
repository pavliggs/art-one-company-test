package com.khovaylo.app.service;

import com.khovaylo.app.model.Payment;
import com.khovaylo.app.model.Status;
import com.khovaylo.app.model.User;
import com.khovaylo.app.repository.PaymentRepository;
import com.khovaylo.app.repository.UserRepository;
import com.khovaylo.app.security.jwt.JwtProvider;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Pavel Khovaylo
 */
@Service
@Transactional(isolation = Isolation.SERIALIZABLE)
public class UserService {

    private final UserRepository userRepository;
    private final PaymentRepository paymentRepository;

    public UserService(UserRepository userRepository, PaymentRepository paymentRepository) {
        this.userRepository = userRepository;
        this.paymentRepository = paymentRepository;
    }

    public User get(String login) {
        return userRepository.findUserByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("User doesn't exist"));
    }

    public List<User> get() {
        return userRepository.findAll();
    }

    public void create(User user) {
        user.setPassword(getEncoder().encode(user.getPassword()));
        user.setCashBalance(BigDecimal.valueOf(8.0));
        user.setStatus(Status.ACTIVE);
        userRepository.save(user);
    }

    public void refresh(User user) {
        BigDecimal changed = user.getCashBalance().subtract(BigDecimal.valueOf(1.1));
        user.setCashBalance(changed);
        userRepository.save(user);
    }

    private PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}
