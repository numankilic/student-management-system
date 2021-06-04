package com.example.studentmanagementsystem.service;

import com.example.studentmanagementsystem.entity.Course;

import java.util.List;

public interface CourseService {

    List<Course> getAllCourses();
    Course saveCourse(Course course);
    Course getCourseById(Long id);
    Course updateCourse(Course course);
    void deleteCourseById(Long id);
}
