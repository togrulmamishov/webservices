package com.azercell.webservices.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(exclude = "user")
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    private Integer id;
    private String description;

    @JsonIgnore
    private Integer userId;
}
