//package com.encore.board.author.service;
//
//
//import com.encore.board.author.Domain.Author;
//import com.encore.board.author.Dto.AuthorDetailResDto;
//import com.encore.board.author.Dto.AuthorUpdateReqDto;
//import com.encore.board.author.Repository.AuthorRepository;
//import com.encore.board.author.Service.AuthorService;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//public class AuthorServiceTest {
//
//
//
//    @SpringBootTest
//    public class AuthorServicetest {
//
//        @Autowired
//        private AuthorService authorService;
//
////        가짜객체를 만드는 작업을 목킹이라 한다.
//        private AuthorRepository authorRepository;
//        @MockBean
//
//        @Test
//        void findAlltest() {
//
//            Author author = Author.builder()
//                    .name("test1")
//                    .email("test1@naver.com")
//                    .password("1234")
//                    .build();
//            Mockito.when(authorRepository.findById(author(id))).thenReturn(Optional.of(author));
//            AuthorDetailResDto authorDetailResDto = authorService.findAuthorDetail(authorId);
//            Assertins.assertEquals(author.getName(), authorDetailResDto.getName();
//        }
//
//        Assertions.asserEquls(author_db.getName(), author_new.getName());
//
////            Mock repository 기능 구현
//            List<Author> authors = new ArrayList<>();
//            authors.add(new Author());
//            authors.add(new Author());
//            Mockito.when(authorRepository.findAll()).thenReturn(authors);
//
////          검증
//        Assertions.assertEquals(2,authorService.findAll().size());
//
//
//
//        }
//    }
//
//
//
//
//}
