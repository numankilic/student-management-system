package com.example.studentmanagementsystem.controller;


import com.example.studentmanagementsystem.entity.Course;
import com.example.studentmanagementsystem.service.CourseService;
import com.example.studentmanagementsystem.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CourseController {

    private CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/courses")
    public String listCourses(Model model){
        model.addAttribute("courses", courseService.getAllCourses());
        return "courses";
    }

    @GetMapping("/courses/new")
    public String createCourseForm(Model model){
        Course course = new Course();
        model.addAttribute("course", course);
        return "create_course";
    }

    @PostMapping("/courses")
    public String saveCourse(@ModelAttribute("course") Course course){
        courseService.saveCourse(course);
        return "redirect:/courses";
    }

    @GetMapping("/courses/edit/{id}")
    public String editCourse(@PathVariable Long id, Model model){
        model.addAttribute("course", courseService.getCourseById(id));
        return "edit_student";
    }

    @PostMapping("/courses/{id}")
    public String updateCourse(@PathVariable Long id,
                               @ModelAttribute("course") Course course,
                               Model model){
        // get course from database by id
        Course existingCourse = courseService.getCourseById(id);
        existingCourse.setId(id);
        existingCourse.setCourseCode(course.getCourseCode());
        existingCourse.setCourseName(course.getCourseName());

        // save updated course object
        courseService.updateCourse(existingCourse);
        return "redirect:/courses";
    }

    @GetMapping("/courses/{id}")
    public String deleteCourse(@PathVariable Long id){
        courseService.deleteCourseById(id);
        return "redirect:/courses";
    }

}
