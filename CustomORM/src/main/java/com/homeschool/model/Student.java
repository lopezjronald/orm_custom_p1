package com.homeschool.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    String studentFirstName;
    String studentLastName;
    Enum<Grade> studentGrade;
    List<Course> courseList;

}
