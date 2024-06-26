package org.example.movieapp.rest;

import lombok.RequiredArgsConstructor;
import org.example.movieapp.model.request.CreateEpisodeRequest;
import org.example.movieapp.servive.EpisodeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class EpisodeApi {
    private final EpisodeService episodeService;
    @PostMapping("/admin/episodes/{id}/upload-video")
    ResponseEntity<?> uploadVideo(@PathVariable Integer id, @RequestParam MultipartFile file){
        return ResponseEntity.ok(episodeService.uploadVideo(id,file));
    }

    @PostMapping("/admin/episodes/create-episode")
    ResponseEntity<?> createEpisode(@RequestBody CreateEpisodeRequest request){
        return  ResponseEntity.ok(episodeService.createEpisode(request));
    }

    @PutMapping("/admin/episodes/{id}/update")
    ResponseEntity<?> updateEpisode(@PathVariable Integer id,@RequestBody CreateEpisodeRequest request){
        return  ResponseEntity.ok(episodeService.updateEpisode(id,request));
    }

    @DeleteMapping("/admin/episodes/{id}/delete")
    ResponseEntity<?> deleteEpisode(@PathVariable Integer id){
        return ResponseEntity.ok(episodeService.deleteEpisode(id));
    }

}
