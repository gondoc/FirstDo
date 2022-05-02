package com.eseict.gondo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserVO {
	private int user_idx;
	private String user_id;
	private String user_pw;
	private String user_name;
	private String usercol1;
	private String usercol2;
	private String usercol3;
}
