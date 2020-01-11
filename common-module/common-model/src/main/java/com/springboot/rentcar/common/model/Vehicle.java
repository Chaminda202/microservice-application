package com.springboot.rentcar.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "vehicle")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle implements Serializable {
    private static final long serialVersionUID = -6040540370708331162L;
    @Id
    @GeneratedValue
    private Integer id;
    private String make;
    private String model;
    private String type;
    private Integer year;
}
