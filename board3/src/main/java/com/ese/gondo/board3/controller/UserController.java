package com.ese.gondo.board3.controller;

import com.ese.gondo.board3.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

//    @GetMapping("/user/user")
//    public Long insertUser(@RequestBody RequestUserDto requestUserDto){
//        return userService.insertUser(requestUserDto);
//    }

    // 회원가입 페이지 접근
    @GetMapping("/signUp")
    public String insertUser() {
        return "signUp";
    }

//    // 로그인 페이지 접근
    // 생각해보니 board.html에서 로그인 가능함
//    @GetMapping("/user/signIn")
//    public String viewUser(){
//        return "signIn";
//    }

    // 회원수정 페이지 접근
    @GetMapping("/edit")
    public String editUser() {
        return "edit";
    }

    // 회원탈퇴 페이지 접근
    // 회원수정 페이지 내 링크형태로 존재 및 confirm 및 안내 후 처리
    @GetMapping("/delete")
    public String deleteUser() {
        return "account";
    }

    // 추후 작성 예정
    // id 찾는 페이지 접근
    @GetMapping("/findId")
    public String findId() {
        return "findId";
    }

    // 추후 작성 예정
    // pw 찾는 페이지 접근
    @GetMapping("/findPw")
    public String findPw() {
        return "findPw";
    }
}

