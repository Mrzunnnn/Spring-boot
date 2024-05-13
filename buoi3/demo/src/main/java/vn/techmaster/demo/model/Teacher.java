package vn.techmaster.demo.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component // Đây là Bean
@Getter
@Setter
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Teacher {
    String name;
    public Teacher(){
        name = "Nguyen van B";
        System.out.println("Teacher created");
    }
}
