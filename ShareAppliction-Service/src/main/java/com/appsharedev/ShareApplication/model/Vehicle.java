package com.appsharedev.ShareApplication.model;

import javax.persistence.*;

@Entity
@Table(name = "VEHICLES")
public class Vehicle {

    // Column id of vehicles and unique in the table
    @Column(name = "ID_VEHICLE")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    // Column numplate of vehicle and unique in the table, is not null
    @Column(name = "NUMPLATE_VEHICLE", nullable = false, unique = true)
    private String number_plate;

}
