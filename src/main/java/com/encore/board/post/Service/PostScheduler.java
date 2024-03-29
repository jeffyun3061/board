package com.encore.board.post.Service;

import com.encore.board.post.Domain.Post;
import com.encore.board.post.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component       //spring bean 으로 만들어 주기
public class PostScheduler {

    private PostRepository postRepository;  //생성자 패턴

    @Autowired
    public PostScheduler(PostRepository postRepository) {
        this.postRepository = postRepository;

    }

    // 초 분 시간 일 월 요일 형태로 스케쥴링 설정
    // * : 매 초(분/시 등)을 의미
    // 특정숫자 : 특정숫자의 초(분/시 등)을 의미
    //  0/특정숫자 : 특정숫자마다
    // ex)0 0 * * * * : 매일 0분 0초에 스케쥴링 시작
    // ex)0 0/1 * * * * : 매일 1분마다 0초에 스케쥴링 시작
    // ex)0/1 * * * * : 매초마다
    // ex)0 0 11 * * * : 매일 11시에 스케쥴링
//    @Scheduled(cron = "0 0/1 * * * *")
//    public void postSchedule() {
//        System.out.println("===스케쥴러 시작===" + LocalDateTime.now());
//        Page<Post> posts = postRepository.findByAppointment("Y", Pageable.unpaged());
//        for (Post p : posts.getContent()) {
//            if(p.getAppointmentTime().isBefore(LocalDateTime.now())){
//                p.updateAppointment(null);
//                postRepository.save(p);
//            }
//        }
//        System.out.println("===스케쥴러 끝===");
//
//    }
}