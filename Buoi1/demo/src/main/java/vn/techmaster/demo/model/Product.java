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
public class Product {
     String id;
     String name;
     String description;
     int price;
     String brand;
}
