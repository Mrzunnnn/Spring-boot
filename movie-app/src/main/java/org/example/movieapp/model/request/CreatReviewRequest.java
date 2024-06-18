package org.example.movieapp.model.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreatReviewRequest {
    @NotEmpty(message = "Nội dung không được để trống")
    String content;

    @Length(min = 1,message = "Đánh giá phải trên 1 sao")
    @Length(max = 10,message = "Đánh giá phải dưới 10 sao")
    Integer rating;
    @NotEmpty(message = "Id phim không được để trống")
    Integer movieId;
}
