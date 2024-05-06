package vn.techmaster.demo.model;
import lombok.*;
import lombok.experimental.FieldDefaults;
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Student {
    int id;
    String name;
    String address;
    String numberphone;
    int age;

}
