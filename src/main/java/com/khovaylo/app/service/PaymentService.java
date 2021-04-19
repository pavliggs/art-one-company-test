package com.khovaylo.app.service;

import com.khovaylo.app.model.Payment;
import com.khovaylo.app.repository.PaymentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Pavel Khovaylo
 */
@Service
@Transactional(isolation = Isolation.SERIALIZABLE)
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public void create(Payment payment) {
        payment.setWithdrawValue(BigDecimal.valueOf(1.1));
        payment.setPaymentTime(LocalDateTime.now());
        paymentRepository.save(payment);
    }
}
