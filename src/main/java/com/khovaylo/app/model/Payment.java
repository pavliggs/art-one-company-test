package com.khovaylo.app.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Pavel Khovaylo
 */
@Getter
@Setter
@Entity
@Table(name = "payments")
public class Payment extends AbstractEntity {

    @Column(name = "withdraw_value", nullable = false)
    private BigDecimal withdrawValue;

    @Column(name = "payment_time", nullable = false)
    private LocalDateTime paymentTime;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;
}
