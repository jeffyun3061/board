package com.encore.board.author.controller;

import com.encore.board.author.Domain.Author;
import com.encore.board.author.Dto.AuthorDetailResDto;
import com.encore.board.author.Service.AuthorService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;

////WebMvcTest를 이용해서 Controller계층을 테스트,
public class AuthorControllTest {

    @WebMvcTest(AthorController.class)

    @SpringBootTest
    @AutoConfigureMockMvc
    public class AuthorControllerTest{}

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthorService authorService;

    @Test
    @WithMockUser //security 의존성 추가 필요
    void authorDetailTest() throws Exception {
        AuthorDetailResDto authorDetailResDto = new AuthorDetailResDto();
        authorDetailResDto. setName("testename");
        authorDetailResDto. setEmail("test@naver.com");
        authorDetailResDto.setPassword("1234");
        Mockito.when(authorService.findAuthorDetail((1L))).thenReturn(authorDetailResDto);

        mockMvc.perform((MockMvcRequestBuilders.get("/author/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.name", authorDetailResDto.getName()));
    }

}


