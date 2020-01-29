package com.springboot.rentcar.common.model.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "customer")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer implements Serializable {
    private static final long serialVersionUID = -6040540370708331162L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "dl_number")
    private String dlNumber;
    @Column(name = "zip_code")
    private String zipCode;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    private List<Loyality> loyalities;

    public Customer(String firstName, String lastName, String dlNumber, String zipCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dlNumber = dlNumber;
        this.zipCode = zipCode;
    }
}
