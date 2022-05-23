package com.ese.gondo.board3.Dto;

import com.ese.gondo.board3.Entity.User;
import lombok.Getter;

@Getter
public class ResponseUserDto {

    private long id;
    private String userId;
    private String userPw;
    private String userName;
    private String userPhone;
    private String userBirth;

    public ResponseUserDto(User entity) {
        this.id = entity.getId();
        this.userId = entity.getUserId();
        this.userPw = entity.getUserPw();
        this.userName = entity.getUserName();
        this.userPhone = entity.getUserPhone();
        this.userBirth = entity.getUserBirth();
    }
}