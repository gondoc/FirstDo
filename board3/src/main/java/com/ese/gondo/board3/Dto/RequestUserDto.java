package com.ese.gondo.board3.Dto;

import com.ese.gondo.board3.Entity.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RequestUserDto {

    private String userId;
    private String userPw;
    private String userName;
    private String userPhone;
    private String userBirth;

    public User toEntity(){
        return User.builder()
                .userId(userId)
                .userPw(userPw)
                .userName(userName)
                .userPhone(userPhone)
                .userBirth(userBirth)
                .build();
    }
}