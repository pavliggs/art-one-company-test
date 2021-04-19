package com.khovaylo.app.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author Pavel Khovaylo
 */
@Getter
@Setter
@Entity
@Table(name = "users")
@DynamicUpdate
public class User extends AbstractEntity {

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Column(nullable = false)
    private BigDecimal cashBalance;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<Payment> payments;
}
