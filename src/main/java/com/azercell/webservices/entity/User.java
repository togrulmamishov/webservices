package com.azercell.webservices.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.swing.*;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Integer id;

    @Size(min = 3, max = 30, message = "Size should be between 3-30")
    private String name;

    @Past
    private Date birthDate;
}
