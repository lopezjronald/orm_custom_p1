package com.orm.model;

import com.orm.dao.UserDaoImpl;
import lombok.*;

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
