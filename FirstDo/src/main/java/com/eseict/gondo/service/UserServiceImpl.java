package com.eseict.gondo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.eseict.gondo.dao.UserDAO;
import com.eseict.gondo.vo.UserVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;
	
//	@Autowired(required = false)
//	private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

	@Override
	// 로그인 기능
	public String loginUser(String user_id, String user_pw) {
		log.info("UserServiceImpl-loginUser 호출 : user_id {}, user_pw {}", user_id, user_pw);
		UserVO dbUserVO = null;
		String userLoginFlag = "0"; // 0 실패, 1 성공
		if (user_id != null && user_pw != null) {
			try {
				log.info("UserServiceImpl-loginUser 값 존재 확인 및 로그인 시도");
				dbUserVO = userDAO.loginUser(user_id, user_pw);
				return userLoginFlag = "1";
			} catch (Exception e) {
				log.info("UserServiceImpl-loginUser 로그인 실패");
				return userLoginFlag = "0";
			}
		} else {
			return userLoginFlag = "0";
		}
	}

	@Override
	// 회원가입
	public String insertUser(UserVO userVO) {
		log.info("UserServiceImpl-insertUser 호출 userVO{}", userVO);
		if (userVO != null) {
			userDAO.insertUser(userVO);
			return "/home";
		}
		return "/main";
	}

	@Override
	public UserVO selectByIdx(int user_idx) {
		log.info("UserServiceImpl-selectByIdx 호출 user_idx {}", user_idx);
		UserVO dbUserVO = null;
		if (user_idx != 0) {
			dbUserVO = userDAO.selectByIdx(user_idx);
		}
		log.info("UserServiceImpl-selectByIdx 리턴 : " + dbUserVO);
		return dbUserVO;
	}

	@Override
	public UserVO selectByUserId(String user_id) {
		log.info("UserServiceImpl-selectByUserId 호출 user_id {}", user_id);
		UserVO dbUserVO = null;
		if (user_id != null) {
			dbUserVO = userDAO.selectByUserId(user_id);
		}
		log.info("UserServiceImpl-selectByUserId 리턴 dbUserVO {}", dbUserVO);
		return dbUserVO;
	}

	@Override
	// 회원정보수정
	public String updateUser(UserVO userVO) {
		log.info("UserServiceImpl-updateUser 호출 userVO{}", userVO);
		UserVO dbUserVO = null;
		if (userVO != null) {
			dbUserVO = userDAO.selectByUserId(userVO.getUser_id());
			log.info("UserServiceImpl-updateUser 사용자 수정 전 정보 확인 userVO{}", userVO);
			log.info("UserServiceImpl-updateUser 사용자 수정 희망 정보 확인 dbUserVO{}", dbUserVO);
			// 회원정보 수정
//			userVO.setUser_pw(bCryptPasswordEncoder.encode(userVO.getUser_pw())); // 비번 암호화
			userDAO.updateUser(userVO);
			// 수정된 정보를 다시 얻는다.
			dbUserVO = userDAO.selectByUserId(userVO.getUser_id());
			log.info("UserServiceImpl-updateUser 회원정보수정완료 : " + dbUserVO);
		}
		return null;
	}

	@Override
	public String deleteUser(int user_idx) {
		// TODO Auto-generated method stub
		return null;
	}

}
