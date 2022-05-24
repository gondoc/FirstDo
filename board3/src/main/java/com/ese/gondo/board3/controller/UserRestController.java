package com.ese.gondo.board3.controller;

import com.ese.gondo.board3.Dto.RequestUserDto;
import com.ese.gondo.board3.Dto.ResponseUserDto;
import com.ese.gondo.board3.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;

    @PostMapping("/user/signUp")
    public Long singUp(@RequestBody RequestUserDto requestUserDto){
        return userService.insertUser(requestUserDto);
    }

    // 상세보기
    // 현재 미사용
    @GetMapping("/user/edit")
    public ResponseUserDto findById(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String userId = (String)session.getAttribute("sessionUserId");
        log.info("Long Id {} ", userId);
        return userService.viewUser(userId);
    }

    // 삭제
    // id 값으로 보드를 찾아 삭제 진행 Long id PK 리턴
    @DeleteMapping("/user/delete/")
    public Long delete(@PathVariable final Long id){
        log.info("Long id {}", id);
        return userService.deleteUser(id);
    }

    @PatchMapping("/user/edit/")
    public Long update(@PathVariable final Long id, @RequestBody RequestUserDto requestUserDto){
        log.info("Long id {}, RequestDto requestDto {} " , id, requestUserDto);
        return userService.updateUser(requestUserDto, id);
    }
}
