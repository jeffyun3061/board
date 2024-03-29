package com.encore.board.post.Domain;

import com.encore.board.author.Domain.Author;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false, length = 3000)
    private String contents;

    private String appointment;
    private LocalDateTime appointmentTime;
//    author_id는 DB의 컬럼명, 별다른 옵션 없을시 author의 pk에 fk가 설정
//    post객체가
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
//      @JoinColumn(nullable=false, name="author_email", referenceColumnName="email")
    private Author author;

    @CreationTimestamp
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdTime;

    @UpdateTimestamp
    @Column(columnDefinition = "TIMESTAMP ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime updatedTime;

    public void updatePost(String title, String contents){
        this.title = title;
        this.contents = contents;
    }
    public void updateAppointment(String appointment){
        this.appointment = appointment;
    }
}