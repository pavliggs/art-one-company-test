package com.khovaylo.app.repository;

import com.khovaylo.app.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Pavel Khovaylo
 */
@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
