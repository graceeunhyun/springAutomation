package me.eunhyeon.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import me.eunhyeon.springbootdeveloper.domain.Article;
import me.eunhyeon.springbootdeveloper.dto.AddArticleRequest;
import me.eunhyeon.springbootdeveloper.service.BlogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class BlogApiController {
    private final BlogService blogService;

    @PostMapping("/api/articles")
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request) {
        return ResponseEntity.ok(blogService.addArticle(request));
    }
}
