package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class firstController {
    @GetMapping("/hi")   //@GetMapping : 페이지를 반환해달라는 url 요청 접수 부분
    //url 연결 요청 - @GatMapping 쓰는 순간 자동으로 임포트
    // 웹 브라우저에서 localhost:8080/hi로 접속하면 greetings.mustache 파일 찾아 반환해달라는 뜻
    // 괄호안엔 url 주소
    public String niceToMeetYou(Model model) { //model객체 받아오기
        model.addAttribute("username","nadomola");
        //모델 객체가 나도몰라 값을 username 에 연결해 웹 브라우저로 보냄
        return "greetings"; //greetings.mustache 파일 반환
                            // 서버가 알아서 templates 에서 greetings.mustache 찾아 웹 브라우저로 전송
    }
    @GetMapping ("/bye")
    public String seeYouNext(Model model){
        model.addAttribute("nickname","nadomola");
        return "goodbye";
    }

}

