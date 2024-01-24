package com.encore.board.author.post.Service;

import com.encore.board.author.Domain.Author;
import com.encore.board.author.Repository.AuthorRepository;
import com.encore.board.author.post.Domain.Post;
import com.encore.board.author.post.Dto.PostDetailResDto;
import com.encore.board.author.post.Dto.PostListResDto;
import com.encore.board.author.post.Dto.PostSaveReqDto;
import com.encore.board.author.post.Repository.PostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final AuthorRepository authorRepository;
    @Autowired
    public PostService(PostRepository postRepository, AuthorRepository authorRepository){
        this.postRepository = postRepository;
        this.authorRepository = authorRepository;
    }

    public void save(PostSaveReqDto postSaveReqDto) {
        Author author = authorRepository.findByEmail(postSaveReqDto.getEmail()).orElse(null);
        Post post = Post.builder()
                .tilte(postSaveReqDto.getTitle())
                .contents(postSaveReqDto.getContents())
                .author(author)
                .build();
        postRepository.save(post);
    }

    public List<PostListResDto> findAll(){
        List<Post> posts= postRepository.findAll();
        List<PostListResDto> postListResDtoList= new ArrayList<>();
        for( Post p: posts){
            PostListResDto postListResDto = new PostListResDto();
            postListResDto.setId(p.getId());
            postListResDto.setTitle(p.getTitle());

            postListResDto.setAuthor_email(post.getAuthor()==null?"익명":post.getAuthor().getEmail());
            postListResDtoList.add(postListResDto);
        }
        return postListResDtoList;
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
        postDetailsResDto.setCreated_Time(postDetails.getCreated_time());
//        authorDetailResDto.setRole(authorDetails.getRole().toString());
        return postDetailsResDto;
    }

    public void delete(long id){
//        authorRepository.delete(this.findById(id));/
        postRepository.deleteById(id);
    }

    @Autowired
    public PostService(PostRepository postRepositry) { this.postRepository=postRepositry}
    public void update(long id, PostUpdateReqDto postUpdateReqDto){
        Post post = this.findById(id);
        post.updatePost(postUpdateReqDto.getTitle(),postUpdateReqDto.getContents());
        postRepository.save(post);
    }
}