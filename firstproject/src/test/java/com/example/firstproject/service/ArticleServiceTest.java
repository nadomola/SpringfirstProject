package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ArticleServiceTest {
    @Autowired
    ArticleService articleService;
    @Test
    void index() {
        // 1. 예상 데이터
        Article a = new Article(1L,"가가가가","1111");
        Article b = new Article(2L,"나나나나","2222");
        Article c = new Article(3L,"다다다다","3333");
        List<Article> expected = new ArrayList<Article>(Arrays.asList(a,b,c));
        // 2. 실제 데이터
        List<Article> articles = articleService.index();
        // 3. 비교 및 검증
        assertEquals(expected.toString(), articles.toString());
    }

    @Test
    //게시글 성공하는 경우와 실패하는 경우로 나눠서 테스트 케이스 작성하기
    void show_성공_존재하는_id_입력() {
        // 1. 예상 데이터
        Long id = 1L;
        Article expected = new Article(id,"가가가가","1111");
        // 2. 실제 데이터
        Article article = articleService.show(id);
        // 3. 비교 및 검증
        assertEquals(expected.toString(),article.toString());
    }
    @Test
    void show_실패_존재하지_않는_id_입력(){
        // 1. 예상 데이터
        Long id = -1L;
        Article expected = null; // 예상 데이터(null)저장
        // 2. 실제 데이터
        Article article = articleService.show(id);
        // 3. 비교 및 검증
        assertEquals(expected,article);

    }

    @Test
    @Transactional //테스트가 끝난후 변경된 데이터를 처음으로 되돌리도록
    void create_성공_title과_content만_있는_dto_입력() {
        // 1. 예상 데이터
        String title = "DDDD";
        String content = "4444";
        ArticleForm dto = new ArticleForm(null,title,content);
        Article expected = new Article(4L, title,content);
        // 2. 실제 데이터
        Article article = articleService.create(dto);
        // 3. 비교 및 검증
        assertEquals(expected.toString(),article.toString());

    }
    @Test
    @Transactional //c,u,d를 하는 테스트를 할 때는 반드시 해당 테스트를 트랜잭션으로 묶어 테스트가 종료한 후 원래대로 돌아갈 수 있게 롤백 처리
    void create_실패_id가_포함된_dto_입력(){
        // 1. 예상 데이터
        Long id = 4L;
        String title = "DDDD";
        String content = "4444";
        ArticleForm dto = new ArticleForm(id, title, content);
        Article expected = null;
        // 2. 실제 데이터
        Article article = articleService.create(dto);
        // 3. 비교 및 검증
        assertEquals(expected, article); // 비교

    }
}