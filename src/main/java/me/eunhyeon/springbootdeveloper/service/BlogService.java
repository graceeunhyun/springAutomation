package me.eunhyeon.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.eunhyeon.springbootdeveloper.domain.Article;
import me.eunhyeon.springbootdeveloper.dto.AddArticleRequest;
import me.eunhyeon.springbootdeveloper.repository.BlogRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BlogService {
   private final BlogRepository blogRepository;

    public Article addArticle(AddArticleRequest request) {
         return blogRepository.save(request.toEntity());
    }
}
