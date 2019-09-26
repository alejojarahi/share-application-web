package com.appsharedev.ShareApplication.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "USERS")
public class User {

// ID
    // Column id of users and unique in the table
    @Column(name = "ID_USER")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

// PASSWORD
    // Column first name of user is not null
    @Column(name = "PASSWORD", nullable = false)
    /* Validation */
    @NotBlank(message = "The password is a mandatory field")
    @Size(min=8, message = "The password should have 8 characters minimum")
    // This format is regular expression so that password has numbers and letters
    //@Pattern(regexp = "^[a-zA-Z]\\w{3,14}$", message = "The password should have numbers and letters")
    private String password;

//DOCUMENT
    // Column document of user and unique in the table, is not null
    @Column(name = "DOCUMENT_USER", nullable = false, unique = true)
    /* Validation */
    @NotNull(message = "The document is a mandatory field")
    @Min(value = 1, message = "The document is a number positive")
    private Integer document;

//FIRSTNAME
    // Column first name of user is not null
    @Column(name = "FIRST_NAME", nullable = false)
    /* Validation */
    @NotBlank(message = "The first name is a mandatory field")
    @Size(min=2, max=20, message = "The first name should have of 2 to 20 characters")
    private String firstname;

//LASTNAME
    // Column last name of user is not null
    @Column(name = "LAST_NAME", nullable = false)
    /* Validation */
    @NotBlank(message = "The last name is a mandatory field")
    @Size(min=2, max=20, message = "The last name should have of 2 to 20 characters")
    private String lastname;

//EMAIL
    // Column email of user is not null
    @Column(name = "EMAIL_USER", nullable = false, unique = true)
    /* Validation */
    @NotBlank(message = "The email is a mandatory field")
    @Email(message = "The email field haven't the correct format")
    // This format is regular expression so that email has format of the unal
    //@Pattern(regexp = "^[a-z0-9._%+-]+@unal.edu.co$", message = "The password should have numbers and letters")
    private String email;

//ROLES
    // Colum rol of user is not null
    @NotNull
    // to users can have many roles and roles to have many roles (Relation many to many)
    @ManyToMany
    // create table interception USER_ROLES with columns USER_ID and ROL_ID which foreign  keys
    @JoinTable(name = "USERS_ROLES", joinColumns = @JoinColumn(name = "USER_ID"), inverseJoinColumns = @JoinColumn(name = "ROL_ID"))
    private Set<Rol> roles = new HashSet<>(); // is a set of objects type Rol

    // Constructor of entity User
    public User(String password, Integer document, String firstname, String lastname, String email) {
        this.password = password;
        this.document = document;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }

    // Default constructor for users
    public User() {
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public Integer getId() {
        return id;
    }

    public Integer getDocument() {
        return document;
    }

    public void setDocument(Integer document) {
        this.document = document;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", document='" + document +
                ", first name='" + firstname +
                ", last name='" + lastname +
                ", email='" + email +
                '}';
    }
}
