package me.eunhyeon.springbootdeveloper.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.eunhyeon.springbootdeveloper.domain.Article;
import me.eunhyeon.springbootdeveloper.dto.AddArticleRequest;
import me.eunhyeon.springbootdeveloper.repository.BlogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
//MockMvc를 사용하기 위한 설정
@AutoConfigureMockMvc
class BlogApiControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper; //객체의 상태를 bytestream 으로 전송

    @Autowired
    protected WebApplicationContext context;

    @Autowired
    BlogRepository blogRepository;

    @BeforeEach
    public void mockMvcSetUp(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .build();

        blogRepository.deleteAll();
    }

    @DisplayName("블로그 글 작성 테스트")
    @Test
    public void addArticle() throws Exception{
        //given
        final String url = "/api/articles";
        final String title = "제목";
        final String content = "내용";
        final AddArticleRequest userRequest = new AddArticleRequest(title, content);

        //객체 json 형태로 직렬화
        final String requestJson = objectMapper.writeValueAsString(userRequest);

        //when
        //post 방식으로 url에 requestJson을 전송
        ResultActions result = mockMvc.perform(post(url)
                .content(requestJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        //then -> isOkay(): 200
        result.andExpect(status().isOk());
        List<Article> all = blogRepository.findAll();

        assertThat(all.size()).isEqualTo(1);
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);
    }

}