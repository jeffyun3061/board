package com.encore.board.author.Service;


import com.encore.board.author.Domain.Author;
import com.encore.board.author.Domain.Role;
import com.encore.board.author.Dto.AuthorDetailResDto;
import com.encore.board.author.Dto.AuthorListResDto;
import com.encore.board.author.Dto.AuthorSaveReqDto;
import com.encore.board.author.Dto.AuthorUpdateReqDto;
import com.encore.board.author.Repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;
    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public void save(AuthorSaveReqDto authorSaveReqDto) {
        //일반 생성자 방식
        //Author author = new Author(authorSaveReqDto.getName(), authorSaveReqDto.getEmail(), authorSaveReqDto.getPassword(), role);
        Role role = authorSaveReqDto.getRole() == "ADMIN" ? Role.ADMIN : Role.USER;
        //빌더패턴
        // .build() : 최종적으로 완성시키는 단계
        Author author = Author.builder()
                .email(authorSaveReqDto.getEmail())
                .name(authorSaveReqDto.getName())
                .password(authorSaveReqDto.getPassword())
                .role(role)
                .build();

        //cascade.persist 테스트
        //부모 테이블을 통해 자식 테이블에 객체를 동시에 생성
//        List<Post> posts = new ArrayList<>();
//        Post post = Post.builder()
//                .tilte("안녕하세요. " + author.getName()+"입니다.")
//                .contents("반갑습니다. cascade테스트 중입니다.")
//                .build();
//        posts.add(post);
//        author.setPosts(posts);
        authorRepository.save(author);
    }

    public List<AuthorListResDto> findAll() {
        List<Author> Authors = authorRepository.findAll();
        List<AuthorListResDto> AuthorListResDtos = new ArrayList<>();
        for(Author author : Authors){
            AuthorListResDto authorListResDto = new AuthorListResDto();
            authorListResDto.setId(author.getId());
            authorListResDto.setName(author.getName());
            authorListResDto.setEmail(author.getEmail());
            AuthorListResDtos.add(authorListResDto);
        }
        return AuthorListResDtos;
//        return authorRepository.findAll().stream().map(author -> new AuthorListResDto(author.getId(), author.getName(), author.getEmail())).toList();
    }

    public Author findById(Long id) throws EntityNotFoundException {
        Author author = authorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("검색하신 ID의 Member가 없습니다."));
        return author;
    }

    public AuthorDetailResDto findAuthorDetail(Long id) throws EntityNotFoundException {
        Author author = this.findById(id);
        String role = null;
        if(author.getRole() == null || author.getRole().equals(Role.USER)){
            role = "일반유저";
        }else{
            role = "관리자";
        }
        AuthorDetailResDto authorDetailResDto = new AuthorDetailResDto();
        authorDetailResDto.setId(author.getId());
        authorDetailResDto.setName(author.getName());
        authorDetailResDto.setEmail(author.getEmail());
        authorDetailResDto.setPassword(author.getPassword());
        authorDetailResDto.setRole(role);
        authorDetailResDto.setCreatedTime(author.getCreatedTime());
        return authorDetailResDto;
    }

    public void update(Long id, AuthorUpdateReqDto authorUpdateReqDto) throws EntityNotFoundException {
        Author author = this.findById(id);
        author.updateAuthor(authorUpdateReqDto.getName(), authorUpdateReqDto.getPassword());
        //명시적으로 save하지 않더라도, jpa의 영속성컨텍스트를 통해,
        //객체에 변경이 감지(dirtycheking)되면, 트랜잭션이 완료되는 시점에 save동작.
        //authorRepository.save(author);
    }

    public void delete(Long id) throws EntityNotFoundException {
        Author author = this.findById(id);
        authorRepository.delete(author);
//        authorRepository.deleteById(id);
    }
}