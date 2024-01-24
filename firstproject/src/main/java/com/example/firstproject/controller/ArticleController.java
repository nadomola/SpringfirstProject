package com.example.firstproject.controller;

import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article; //Article 클래스 임포트 확인
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Optional;

@Slf4j  //로깅 기능 위한 어노테이션
@Controller
public class ArticleController {
    @Autowired // 스프링부트가 미리 생성해놓은 리파지터리 객체 주입 (DI)
    private ArticleRepository articleRepository; // db에서 데이터를 가져오는 주체는 리파지터리

    @GetMapping("/articles/new") //url 요청 접수
    public String newArticleForm(){ //메서드 작성

        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){
        log.info(form.toString()); //로깅 코드 추가
//        System.out.println(form.toString());  // 기존 println문 주석 처리
        //1. DTO를 엔티티로 변환
        Article article = form.toEntity();
        log.info(article.toString()); //로깅 코드 추가
//        System.out.println(article.toString()); //기존 pritnln문ㅁ 주석 처리
        //2. 리파지터리로 엔티티를 db에 저장
        Article saved = articleRepository.save(article); // article 엔티티를 저장해 saved 객체에 반환
        log.info(saved.toString());  //로깅 코드 추가
//        System.out.println(saved.toString()); //기존 println문 주석처리
        return "redirect:/articles/"+saved.getId();
    }
    @GetMapping("articles/{id}")  //데이터 조회 요청 접수
    //url 요청 받아 수행할 메서드
    public String show(@PathVariable Long id, Model model){ //매개변수로 id 받아오기
        log.info("id = " + id); // 아아디 잘 받았는지 확인하는 로그 찍기
        //1. id 조회해 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null); //해당 아이디값이 없으면 null값 반환
        //2. 모델에 데이터 등록하기
        model.addAttribute("article",articleEntity);
        //3. 뷰 페이지 반환하기
        return "articles/show";
    }
    @GetMapping("/articles")
    public String index(Model model){
        //1. DB에서 모든  article 데이터 가져오기
        ArrayList<Article> articleEntityList= articleRepository.findAll();
        //2. 가져온 Article 묶음을 모델에 등록하기
        model.addAttribute("articleList",articleEntityList);
        //3. (사용자에게 보여줄 ) 뷰 페이지 설정하기
        return "articles/index";
    }
    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model){ //모델 객체 받아오기
        //수정할 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null); //db에서 수정할 데이터 가져오기
        //모델에 데이터 등록하기
        model.addAttribute("article",articleEntity); // articleEntity를 article로 등록
        //뷰 페이지 설정하기
        return "articles/edit";
    }

    @PostMapping("/articles/update") //데이터 수정 요청 처리하는 메서드(oist방식으로 요청 받았으니 postMapping
    public String update(ArticleForm form){ //매개변수로 dto받아오기
        log.info(form.toString());
        //1 dto를 엔티티로 변환하기
        Article articleEntity =form.toEntity();
        log.info(articleEntity.toString());
        //2. 엔티티를 db에 저장하기
        //2-1. db에서 기존 데이터 가져오기
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
        //2-2. 기존 데이터값 갱신하기
        if(target != null){
            articleRepository.save(articleEntity); //db에 엔티티를 저장 (갱신)
        }
        //3. 수정 결과 페이지로 리다이렉트하기
        return "redirect:/articles/"+articleEntity.getId();
    }
    @GetMapping("/articles/{id}/delete") //delete 메서드 지원 안 하니까 get
    public String delete(@PathVariable Long id, RedirectAttributes rttr){ //id를 매개변수로 가져오기
        log.info("삭제요청이 들어왔습니다.");
        //1. 삭제할 대상 가져오기
        Article target =articleRepository.findById(id).orElse(null);
        log.info(target.toString()); //target에 데이터가 있는지 없는지 확인하는 로그
        //2. 대상 엔티티 삭제하기
        if (target != null){ // 삭제할 대상이 있는지 확인
            articleRepository.delete(target); //delete() 메서드로 대상 삭제
            rttr.addFlashAttribute("msg","삭제됐습니다!");
        }
        //3. 결과 페이지로 리다이렉트하기
        return "redirect:/articles";
    }


}
