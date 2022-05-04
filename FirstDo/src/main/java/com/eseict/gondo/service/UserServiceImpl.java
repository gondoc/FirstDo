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
	public UserVO loginUser(UserVO userVO) {
		log.info("UserServiceImpl-loginUser 호출 : userVO {} ", userVO);
		UserVO dbUserVO = null;
		if (userVO.getUser_id() != null && userVO.getUser_pw() != null) {
			try {
				log.info("UserServiceImpl-loginUser 값 존재 확인 및 로그인 시도");
				dbUserVO = userDAO.loginUser(userVO);
				return dbUserVO;
			} catch (Exception e) {
				log.info("UserServiceImpl-loginUser 로그인 실패");
				return new UserVO();
			}
		} else {
			return new UserVO();
		}
	}

	@Override
	// 회원가입
	public int insertUser(UserVO userVO) {
		log.info("UserServiceImpl-insertUser 호출 userVO{}", userVO);
		int insertUserFlag = 0; // 0 실패, 1 성공
		if (userVO != null) {
			try {
				log.info("UserServiceImpl-insertUser userVO");
				userDAO.insertUser(userVO);
				log.info("UserServiceImpl-insertUser 회원가입 성공 insertUserFlag {}", insertUserFlag);
				return insertUserFlag = 1;
			} catch (Exception e) {
				log.info("UserServiceImpl-insertUser 회원가입 시도 중 오류 발생");
				log.info("UserServiceImpl-insertUser insertUserFlag {}", insertUserFlag);
				return insertUserFlag;
			}
		} else {
			log.info("UserServiceImpl-insertUser userVO = null 오류 발생");
			return insertUserFlag;
		}
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
	public int updateUser(UserVO userVO) {
		log.info("UserServiceImpl-updateUser 호출 userVO{}", userVO);
		UserVO dbUserVO = null;
		int updateUserFlag = 0; // 0 실패, 1 성공, 2 일치
		if (userVO != null) {
			dbUserVO = userDAO.selectByUserId(userVO.getUser_id());
			log.info("UserServiceImpl-updateUser 사용자 수정 전 정보 확인 userVO{}", userVO);
			log.info("UserServiceImpl-updateUser 사용자 수정 희망 정보 확인 dbUserVO{}", dbUserVO);
			// 회원정보 수정
//			userVO.setUser_pw(bCryptPasswordEncoder.encode(userVO.getUser_pw())); // 비번 암호화
			if (userVO != dbUserVO) {
				userDAO.updateUser(userVO);
				updateUserFlag = 1;
				dbUserVO = userDAO.selectByUserId(userVO.getUser_id());
				log.info("UserServiceImpl-updateUser 회원정보수정완료 : " + dbUserVO);
			} else {
				log.info("UserServiceImpl-updateUser 회원정보수정완료 : " + dbUserVO);
				updateUserFlag = 2;
			}
			// 수정된 정보를 다시 얻는다.
		}
		return updateUserFlag;
	}

	@Override
	public int deleteUser(int user_idx) {
		int updateUserFlag = 0; // 0 실패, 1 성공, 2 일치
		
		if (user_idx != 0) {
			
		}
		return updateUserFlag;
	}

}
