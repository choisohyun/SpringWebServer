package com.test.web.dao;

import java.util.List;

import com.test.web.bean.BoardBean;

public interface BoardDao {

	public BoardBean selectBoard(BoardBean mBean);

	public List<BoardBean> selectBoardList();

	//회원가입
	public int insertBoard(BoardBean bean);

	//회원수정
	public int updateBoard(BoardBean mBean);

	//회원삭제
	public int deleteBoard(BoardBean mBean);
}
