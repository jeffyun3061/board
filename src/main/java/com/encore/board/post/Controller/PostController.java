package com.encore.board.author.post.Controller;

import com.encore.board.author.post.Dto.PostDetailResDto;
import com.encore.board.author.post.Dto.PostListResDto;
import com.encore.board.author.post.Dto.PostSaveReqDto;
import com.encore.board.author.post.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class PostController {
    private final PostService postService;
    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }


    @GetMapping("/post/create")
    public String postCreate(){
        return "post/post-create";
    }

    @PostMapping("/post/create")
    public String postSave(PostSaveReqDto postSaveReqDto){
        postService.save(postSaveReqDto);
        return "redirect:/post/list";
    }


    @GetMapping("/post/list")
    public String postList(Model model){
        List<PostListResDto> postListResDtos = postService.findAll();
        model.addAttribute("postList", postListResDtos);
        return "post/post-list";
    }

    @GetMapping("/post/detail/{id}")
    public String postDetail(@PathVariable Long id, Model model){
        PostDetailResDto postDetailResDto = postService.findpostdetail(id);
        model.addAttribute("post", postDetailResDto);
        return "post/post-detail";
    }

    @PostMapping("/post/{id}/update")
    public String postUpdate(@PathVariable Long id, PostDetailResDto postUpdateReqDto){
        postService.update(id, postUpdateReqDto);
        return "redirect:/post/detail/"+ id;
    }

    @GetMapping("/post/delete/{id}")
    public String postDelete(@PathVariable Long id){
        postService.delete(id);
        return "redirect:/post/list";
    }
}