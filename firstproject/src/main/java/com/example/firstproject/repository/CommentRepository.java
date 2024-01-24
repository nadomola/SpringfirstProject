package com.example.firstproject.repository;

import com.example.firstproject.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {  //comment 엔티티를 관리하니까 대상 엔티티는 coment, 대표키값의 타입에는 id 의 타입인 Long
    // 특정 게시글의 모든 댓글 조회
    @Query(value = "SELECT * "+
            "FROM comment "+
            "WHERE article_id = :articleId",
            nativeQuery = true) // value 속성에 실행하려는 쿼리 작성
    List<Comment> findByArticleId(Long articleId);
    // 특정 닉넥임의 모든 댓글 조회
    List<Comment>findByNickname(String nickname);
}
