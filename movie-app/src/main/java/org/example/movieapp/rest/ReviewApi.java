package org.example.movieapp.rest;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.movieapp.entity.Review;
import org.example.movieapp.model.request.CreatReviewRequest;
import org.example.movieapp.model.request.UpdateReviewRequest;
import org.example.movieapp.servive.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/reviews")
@RequiredArgsConstructor
public class ReviewApi {
    final ReviewService reviewService;
    //tạo review -POST

    @PostMapping
    public ResponseEntity<?> creatReview(@RequestBody CreatReviewRequest request) {
        Review review = reviewService.createReview(request);
        return ResponseEntity.ok(review);
    }


    //cập nhật review - PUT

    @PutMapping("/{id}")
    public ResponseEntity<?> updateReview(@PathVariable Integer id,@RequestBody UpdateReviewRequest request) {
        Review review = reviewService.updateReview(id,request);
        return ResponseEntity.ok(review);
    }


    //xoá review - DELETE

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReview(@PathVariable Integer id) {
        reviewService.deleteReview(id);
        return ResponseEntity.ok().build();
    }

}
