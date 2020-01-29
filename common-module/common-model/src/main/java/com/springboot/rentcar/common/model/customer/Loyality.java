package com.springboot.rentcar.common.model.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "loyality_point")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Loyality implements Serializable {
    private static final long serialVersionUID = -6040540370708331163L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transactionId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;
    private Integer point;
    private LocalDateTime expireDate;
}
