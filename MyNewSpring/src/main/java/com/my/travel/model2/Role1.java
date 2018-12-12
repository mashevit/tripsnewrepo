package com.my.travel.model2;


import javax.persistence.*;


@Entity
@Table(name = "role")
public class Role1 {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private int id;
    @Column(name = "role")
    private String role;
}