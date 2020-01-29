package com.springboot.rentcar.common.model.vehicle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "vehicle")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle implements Serializable {
    private static final long serialVersionUID = -6040540370708331162L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String make;
    private String model;
    private String type;
    private Integer year;

    public Vehicle(String make, String model, String type, Integer year) {
        this.make = make;
        this.model = model;
        this.type = type;
        this.year = year;
    }
}
