package com.ese.gondo.board3.service;

import com.ese.gondo.board3.Dto.RequestUserDto;
import com.ese.gondo.board3.Dto.ResponseUserDto;
import com.ese.gondo.board3.Entity.User;
import com.ese.gondo.board3.Entity.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // 회원가입
    @Transactional
    public Long insertUser(final RequestUserDto requestUserDto) {
        User entity = userRepository.save(requestUserDto.toEntity());
        return entity.getId();
    }

    // 회원 정보 조회
    @Transactional
    public ResponseUserDto viewUser(String userId) {
        // custom method
        // userRepository.findByUserId
        User entity = userRepository.findByUserId(userId);
        // findOne에서 getById로 변경됨.
//        User entity = userRepository.getById(id);
        return new ResponseUserDto(entity);
    }

    // 회원정보 수정
    @Transactional
    public Long updateUser(RequestUserDto requestUserDto, final Long id) {
        User entity = userRepository.findById(id).orElseThrow(() -> new NullPointerException());
        // 엔티티 클래스 내 메서드를 넣어도 되는가?
        // 확인요망.
        entity.update(requestUserDto);
        return id;
    }

    // 회원 탈퇴
    @Transactional
    public Long deleteUser(final Long id) {
        userRepository.deleteById(id);
        return id;
    }
}
