package vn.techmaster.demo.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class Classroom {
    // Cách 1 để gọi Bean
//    @Autowired
//
//    Teacher teacher;
//    @Autowired
//    Student student;


    Teacher teacher;
    Student student;

    // Cách 2 để gọi Bean -- Có thể dùng @AllArgContructor
//    public Classroom(Student student,Teacher teacher){
//        this.teacher = teacher;
//        this.student = student;
//    }
}
