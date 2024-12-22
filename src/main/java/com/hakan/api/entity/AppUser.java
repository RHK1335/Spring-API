package com.hakan.api.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "APP_USER_SEQ")
    @SequenceGenerator(name = "APP_USER_SEQ", sequenceName = "APP_USER_SEQ", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "E_MAIL")
    private String email;
}
