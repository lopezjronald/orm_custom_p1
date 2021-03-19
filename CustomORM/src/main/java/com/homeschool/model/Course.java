package com.homeschool.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.EnumSet;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    String courseName;
    String courseNumber;
    Subject subject;

}
