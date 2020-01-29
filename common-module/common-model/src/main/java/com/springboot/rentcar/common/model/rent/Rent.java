package com.springboot.rentcar.common.model.rent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "rent")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rent implements Serializable {
    private static final long serialVersionUID = -6040540370708331165L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rentId;
    private Integer customerId;
    private Integer vehicleId;
    @CreationTimestamp
    private LocalDateTime rentFrom;
    private LocalDateTime rentTill;
    private Integer currentMeter;
    private String returnLocation;

    public Rent(Integer customerId, Integer vehicleId, LocalDateTime rentTill, Integer currentMeter, String returnLocation) {
        this.customerId = customerId;
        this.vehicleId = vehicleId;
        this.rentTill = rentTill;
        this.currentMeter = currentMeter;
        this.returnLocation = returnLocation;
    }
}