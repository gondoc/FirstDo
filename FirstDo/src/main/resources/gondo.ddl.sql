
/*
유저 로직
회원가입
로그인 기능
아이디 찾기
비밀번호 찾기
회원탈퇴
 */

/*
보드 관련 로직
게시글 쓰기
게시글 읽기
게시글 수정하기
게시글 삭제하기
*/


select * from board order by board_idx desc 


-- 유저 테이블 생성
CREATE TABLE IF NOT EXISTS `firstdb`.`user` (
  `user_idx` INT NOT NULL AUTO_INCREMENT,
  `user_id` VARCHAR(10) NOT NULL,
  `user_pw` VARCHAR(100) NOT NULL,
  `user_name` VARCHAR(10) NOT NULL,
  `usercol1` VARCHAR(45) NULL,
  `usercol2` VARCHAR(45) NULL,
  `usercol3` VARCHAR(45) NULL,
  PRIMARY KEY (`user_idx`));
  
select * from user;

-- 회원가입
insert into user (
user_id, user_pw, user_name
)
values
('gondo', '1234', 'gondo');

select * from user;

delete from user where user_idx=2;


-- 보드 테이블 생성
CREATE TABLE IF NOT EXISTS `firstdb`.`board` (
  `board_idx` INT NOT NULL AUTO_INCREMENT,
  `board_subject` VARCHAR(50) NOT NULL,
  `board_content` VARCHAR(100) NOT NULL,
  `board_regDate` VARCHAR(100) NOT NULL,
  `user_idx` INT NOT null,
  `boardcol1` VARCHAR(45) NULL,
  `boardcol2` VARCHAR(45) NULL,
  `boardcol3` VARCHAR(45) NULL,
  foreign key (`user_idx`) references user(`user_idx`),
  PRIMARY KEY (`board_idx`));
  
 select * from board;
 

select * from user where user_id='gondo';


UPDATE `user` SET
	user_pw = #{user_pw},
	user_name = #{user_name },
WHERE
	user_id = #{user_id }
	
	
select * from board where board_idx=1

select * from user;
			
		
		