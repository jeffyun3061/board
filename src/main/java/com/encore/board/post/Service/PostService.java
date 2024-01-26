package com.encore.board.post.Service;

import com.encore.board.author.Domain.Author;
import com.encore.board.author.Repository.AuthorRepository;
import com.encore.board.post.Domain.Post;
import com.encore.board.post.Dto.PostDetailResDto;
import com.encore.board.post.Dto.PostListResDto;
import com.encore.board.post.Dto.PostSaveReqDto;
import com.encore.board.post.Dto.PostUpdateReqDto;
import com.encore.board.post.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final AuthorRepository authorRepository;


    @Autowired
    public PostService(PostRepository postRepositry, AuthorRepository authorRepository) {
        this.postRepository=postRepositry;
        this.authorRepository = authorRepository;
    }

    public void save(PostSaveReqDto postSaveReqDto) {
        Author author = authorRepository.findByEmail(postSaveReqDto.getEmail()).orElse(null);
        LocalDateTime localDateTime = null;
        String appointment = null;
        if (postSaveReqDto.getAppointment().equals("Y")
        && !postSaveReqDto.getAppointmentTime().isEmpty()) {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            localDateTime = LocalDateTime.parse(postSaveReqDto.getAppointmentTime(), dateTimeFormatter);
            LocalDateTime now = LocalDateTime.now();
            if (localDateTime.isBefore(now)) {
                throw new IllegalArgumentException("시간정보 잘못입력");

            }
        appointment = "Y";
        }
        Post post = Post.builder()
                .title(postSaveReqDto.getTitle())
                .contents(postSaveReqDto.getContents())
                .author(author)
                .appointment(appointment)
                .appointmentTime(localDateTime)
                .build();
//        더티체킹 테스트
        postRepository.save(post);
    }


//    public List<PostListResDto> findAll(Pageable pageable) {
//        Page<Post> posts = postRepository.findAll(pageable);
//        Page<PostListResDto> postListResDtoList = new ArrayList<>()
//        return postListResDtoList;
//    }
    public Page<PostListResDto> findAllPaging(Pageable pageable) {
//        Page객체 안에서 Map 지원.
        Page<Post> posts = postRepository.findAll(pageable); // select * from post
        Page<PostListResDto> postListResDtos
                = posts.map(p -> new PostListResDto(p.getId(), p.getTitle(), p.getAuthor()==null? "익명유저" : p.getAuthor().getEmail()));
        return postListResDtos;
    }

    public Page<PostListResDto> findByAppointment(Pageable pageable) {
//        Page객체 안에서 Map 지원.
        Page<Post> posts = postRepository.findByAppointment(null, pageable); // select * from post
        Page<PostListResDto> postListResDtos
                = posts.map(p -> new PostListResDto(p.getId(), p.getTitle(), p.getAuthor()==null? "익명유저" : p.getAuthor().getEmail()));
        return postListResDtos;
    }
    public Post findById(Long id) {
        Post post= postRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return post;
    }

    public PostDetailResDto findAuthorDetail(Long id) throws EntityNotFoundException {
        Post postDetails= postRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        PostDetailResDto postDetailsResDto = new PostDetailResDto();
        postDetailsResDto.setId(postDetails.getId());
        postDetailsResDto.setTitle(postDetails.getTitle());
        postDetailsResDto.setContents(postDetails.getContents());
        postDetailsResDto.setCreatedTime(postDetails.getCreatedTime());
//        authorDetailResDto.setRole(authorDetails.getRole().toString());
        return postDetailsResDto;
    }

    public void delete(long id){
//        authorRepository.delete(this.findById(id));/
        postRepository.deleteById(id);
    }

    public void update(long id, PostUpdateReqDto postUpdateReqDto){
        Post post = this.findById(id);
        post.updatePost(postUpdateReqDto.getTitle(),postUpdateReqDto.getContents());
        postRepository.save(post);
    }
}