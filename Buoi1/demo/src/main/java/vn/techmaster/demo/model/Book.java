package vn.techmaster.demo.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Book {
     int id;
     String title;
     String author;
     int year;


}
