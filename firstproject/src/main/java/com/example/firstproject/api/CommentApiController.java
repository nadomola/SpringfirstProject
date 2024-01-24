package com.example.firstproject.api;

import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController //restController 선언
public class CommentApiController {
    @Autowired
    private CommentService commentService; // 댓글 서비스 객체 주입
    // 1. 댓글 조회
    @GetMapping("/api/articles/{articleId}/comments") //댓글 조회 요청 접수
    public ResponseEntity<List<CommentDto>> comments(@PathVariable Long articleId){  //comments 메서드 생성
        // 서비스에 위임 (댓글 컨트롤러는 댓글 서비스에 조회 작업을 위임해 얻은 결과를 클라에 응답함
        List<CommentDto> dtos = commentService.comments(articleId); // 해당 게시글의 id 를 알아야 해당 댓글 가져올 수 있음

        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }
    // 2. 댓글 생성
    @PostMapping("api/articles/{articleId}/comments")  //댓글 생성 요청 접수
    //create()메서드 생성 ,
    public ResponseEntity<CommentDto> create(@PathVariable Long articleId,  //요청 url의 aritlceid 가져오기
            @RequestBody CommentDto dto){   //생성할 댓글 정보 받아 오는 매개변수 :
        //서비스에 위임
        CommentDto createdDto = commentService.create(articleId,dto);
        //결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(createdDto);
    }
    // 3. 댓글 수정
    // 4. 댓글 삭제

}
