package com.orm.model;

import com.orm.config.PostgreQueries;
import com.orm.dao.UserDaoImpl;
import lombok.*;

import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends UserDaoImpl  {

    private Integer id;
    private String firstName;
    private String lastName;


}
