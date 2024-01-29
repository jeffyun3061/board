package com.encore.board.post.Controller;


import com.encore.board.post.Dto.PostDetailResDto;
import com.encore.board.post.Dto.PostListResDto;
import com.encore.board.post.Dto.PostSaveReqDto;
import com.encore.board.post.Dto.PostUpdateReqDto;
import com.encore.board.post.Service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@Slf4j
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
    public String postSave(Model model, PostSaveReqDto postSaveReqDto){
        try {
            postService.save(postSaveReqDto);
            return "redirect:/post/list";
        }catch (IllegalArgumentException e){
            model.addAttribute("errorMessage", e.getMessage());
            log.error(e.getMessage());
            return  "post/post-create";
        }
    }


    @GetMapping("/post/list")
    // localhost:8080/post/list?size=xx&sort=xx,desc
    public String postList(Model model,@PageableDefault(size = 5, sort = "createdTime",direction = Sort.Direction.DESC) Pageable pageable){
        Page<PostListResDto> postListResDtos = postService.findByAppointment(pageable);
        model.addAttribute("postList", postListResDtos);
        return "post/post-list";
    }

//    @GetMapping("json/post/list")
//    // localhost:8080/post/list?size=xx&sort=xx,desc
//    @ResponseBody
//    public Page <PostListResDto> postList(Pageable pageable){
//        Page<PostListResDto> postListResDtos = postService.findAll();
//        return "post";
//    }

    @GetMapping("/post/detail/{id}")
    public String postDetail(@PathVariable Long id, Model model){
        PostDetailResDto postDetailResDto = postService.findAuthorDetail(id);
        model.addAttribute("post", postDetailResDto);
        return "post/post-detail";
    }

    @PostMapping("/post/update/{id}")
    public String postUpdate(@PathVariable Long id, PostUpdateReqDto postUpdateReqDto){
        postService.update(id, postUpdateReqDto);
        return "redirect:/post/detail/"+ id;
    }

    @GetMapping("/post/delete/{id}")
    public String postDelete(@PathVariable Long id){
        postService.delete(id);

        return "redirect:/post/list";

    }
}