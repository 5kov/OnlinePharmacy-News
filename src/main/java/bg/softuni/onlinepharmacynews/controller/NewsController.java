package bg.softuni.onlinepharmacynews.controller;

import bg.softuni.onlinepharmacynews.model.dto.AddNewsDTO;
import bg.softuni.onlinepharmacynews.model.dto.NewsDTO;
import bg.softuni.onlinepharmacynews.service.impl.NewsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/news")
public class NewsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(NewsController.class);

    private final NewsServiceImpl newsServiceImpl;

    public NewsController(NewsServiceImpl newsServiceImpl) {
        this.newsServiceImpl = newsServiceImpl;
    }

    @GetMapping
    public ResponseEntity<List<NewsDTO>> getAllNews(@AuthenticationPrincipal UserDetails userDetails) {
        if(userDetails != null) {
            System.out.println("User: " + userDetails.getUsername());
            System.out.println("Roles: " + userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(", ")));
        } else {
            System.out.println("NO CURRENT USER!");
        }
        return ResponseEntity.ok(newsServiceImpl.getAllNews());
    }


    @PostMapping
    public ResponseEntity<NewsDTO> createNews(@RequestBody AddNewsDTO addNewsDTO) {
        LOGGER.info("Creating new news {}" , addNewsDTO);
        newsServiceImpl.createNews(addNewsDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<NewsDTO> deleteNews(@PathVariable("id") Long id) {
        newsServiceImpl.deleteNews(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsDTO> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(newsServiceImpl.getNewsById(id));
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<List<NewsDTO>> findByTitleContaining(@PathVariable("title") String title) throws UnsupportedEncodingException {
        return ResponseEntity.ok(newsServiceImpl.getNewsByTitleContaining(title));
    }

    @GetMapping("/titleCheck/{title}")
    public ResponseEntity<List<NewsDTO>> findByTitle(@PathVariable("title") String title) throws UnsupportedEncodingException {

        if (newsServiceImpl.getNewsByTitle(title).size() > 0) {
            return ResponseEntity.ok(newsServiceImpl.getNewsByTitle(title));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
