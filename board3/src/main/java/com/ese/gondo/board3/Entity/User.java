package com.ese.gondo.board3.Entity;

import com.ese.gondo.board3.Dto.RequestUserDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Getter
@NoArgsConstructor
@Entity
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(unique = true)
    private String userId;
    @NotBlank
    private String userPw;
    @NotBlank
    private String userName;
    @NotBlank
    private String userPhone;
    @NotBlank
    private String userBirth;

    @Builder
    public User(String userId, String userPw, String userName, String userPhone, String userBirth){
        this.userId = userId;
        this.userPw = userPw;
        this.userName = userName;
        this.userPhone = userPhone;
        this.userBirth = userBirth;
    }

    public void update(RequestUserDto requestUserDto){
        this.userId = requestUserDto.getUserId();
        this.userPw = requestUserDto.getUserPw();
        this.userName = requestUserDto.getUserName();
        this.userPhone = requestUserDto.getUserPhone();
        this.userBirth = requestUserDto.getUserBirth();
    }
}
