package com.eseict.gondo.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BoardVO {
	private int board_idx;
	private String board_subject;
	private String board_content;
	private Date board_regDate;
	private int user_idx;
	private String boardcol1;
	private String boardcol2;
	private String boardcol3;
}
