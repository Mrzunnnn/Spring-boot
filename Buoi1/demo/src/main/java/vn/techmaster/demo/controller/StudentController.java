package vn.techmaster.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import vn.techmaster.demo.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentController {
    private final List<Student> students;
    public StudentController() {
        students = new ArrayList<>();
        students.add(new Student(1, "Nguyen Dinh Dung", "Hung Yen", "12343434",23));
        students.add(new Student(2, "Nguyen Dinh B", "Ha Noi", "134232574",23));
        students.add(new Student(3, "Nguyen Dinh C", "Ha Noi", "13822574",24));
        students.add(new Student(4, "Nguyen Dinh D", "Ha Nam", "15454474",23));
        students.add(new Student(5, "Nguyen Dinh F", "Ha Dong", "6578574",23));
    }
    @GetMapping("/students")  //http://localhost:8080/books

    public List<Student> getStudents(){
        return students;
    }
}
