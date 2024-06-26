package org.example.movieapp.servive;

import lombok.RequiredArgsConstructor;
import org.example.movieapp.entity.Episode;
import org.example.movieapp.model.exception.ResourceNotFoundException;
import org.example.movieapp.model.request.CreateEpisodeRequest;
import org.example.movieapp.repository.EpisodeRespository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EpisodeService {
    private final EpisodeRespository episodeRespository;
    private final CloudinaryService cloudinaryService;

    public List<Episode> getEpisodes(Integer movieId, Boolean status) {
        return episodeRespository.findByMovie_IdAndStatusOrderByDisplayOrderAsc(movieId, true);
    }

    public List<Episode> getEpisodeListOfMovieByAdmin(Integer movieId) {
        return episodeRespository.findByMovie_IdOrderByDisplayOrderAsc(movieId);
    }

    public Optional<Episode> getEpisode(Integer movieId, Boolean status, String tap) {
        Integer coverTap = tap.equals("full") ? 1 : Integer.parseInt(tap);
        return episodeRespository
                .findByMovie_IdAndStatusAndDisplayOrder(movieId, true, 1);
    }

    public Episode uploadVideo(Integer id, MultipartFile file) {
        Episode episode = episodeRespository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("không tìm thấy tập phim phù hợp"));

        try {
            Map result = cloudinaryService.uploadFile(file);
            episode.setTrailerUrl((String) result.get("url"));
            return episodeRespository.save(episode);
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi upload video");
        }
    }

    public Episode createEpisode(CreateEpisodeRequest request) {

        Episode episode = Episode.builder()
                .name(request.getTitle())
                .displayOrder(request.getDisplayOrder())
                .status(request.getStatus())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .publishedAt(request.getStatus()?LocalDateTime.now():null)
                .duration(null)
                .trailerUrl(null)
                .build();

        return episodeRespository.save(episode);
    }

    public Episode updateEpisode(Integer id,CreateEpisodeRequest request){
        Episode episode = episodeRespository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Không tìm thấy tập phim phù hợp"));

        episode.setName(request.getTitle());
        episode.setDisplayOrder(request.getDisplayOrder());
        episode.setStatus(request.getStatus());
        return episodeRespository.save(episode);
    }

    public Episode deleteEpisode(Integer id) {
        Episode episode = episodeRespository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Không tìm thấy tập phim phù hợp"));

        episodeRespository.delete(episode);
        return null;
    }
}
