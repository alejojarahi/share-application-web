package com.appsharedev.ShareApplication.model;

import com.appsharedev.ShareApplication.model.enums.RolName;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "ROLES")
public class Rol {
 // ID
    // Column id of roles and unique in the table
    @Column(name = "ID_ROL")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

 // ROL NAME
    // Column name of roles is not null
    @Column(name = "ROL_NAME", nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    @NotNull
    private RolName rolname;

    // Constructor for roles
    public Rol(@NotNull RolName rolname) {
        this.rolname = rolname;
    }

    // principal constructor
    public Rol() {
    }

    public Integer getId() {
        return id;
    }

    public RolName getRolname() {
        return rolname;
    }
}
