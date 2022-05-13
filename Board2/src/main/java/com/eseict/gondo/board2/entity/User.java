package com.eseict.gondo.board2.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_idx;
    private String user_id;
    private String user_pw;
    private String user_phone;
    private String user_name;
    private String user_birth;
    private String user_col1;
    private String user_col2;
    private String user_col3;
}
