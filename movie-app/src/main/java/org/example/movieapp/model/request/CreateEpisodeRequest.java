package org.example.movieapp.model.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateEpisodeRequest {
    String title;
    Integer displayOrder;
    Boolean status;
}
