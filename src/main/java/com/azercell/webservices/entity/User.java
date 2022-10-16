package com.azercell.webservices.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "All details about the user")
public class User {

    private Integer id;

    @Size(min = 3, max = 30, message = "Size should be between 3-30")
    @ApiModelProperty(notes = "Name should have at least 3 characters")
    private String name;

    @Past
    @ApiModelProperty(notes = "Birth date cannot be in the past")
    private Date birthDate;
}
