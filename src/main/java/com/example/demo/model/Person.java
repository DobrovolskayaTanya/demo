package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Data
@AllArgsConstructor
public class Person implements Serializable {

    private String firstName;
    private String lastName;
    private String street;
    private String city;
    private String zip;
    private String email;
    private Date birthday;
    private String phone;

}