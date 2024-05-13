package vn.techmaster.demo.model;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component // Đây là bean
@ToString

@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Student {
    String name;
    public Student(){
        name = "nguyen van A";
        System.out.println("Studen created");
    }

}
