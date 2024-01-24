package com.example.firstproject.dto;

import com.example.firstproject.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

//이 파일이 폼 데이터를 받아올 그릇 , 즉 dto가 된다
@AllArgsConstructor
@ToString
public class ArticleForm {
    private Long id;
    // 입력 폼에서 제목과 내용을 전송할 예정이니 dto에도 필드 2개가 필요하다
    //필드 두개 선언하기
    private String title;
    private String content;


    // 데이터를 잘 받았는지 확인할 toString() 메서드 추가


    public Article toEntity() {
        return new Article(id,title, content);
    }
}
