package com.test.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.web.bean.BoardBean;
import com.test.web.dao.BoardDao;

@Controller
public class BoardController {

	@Autowired
	private BoardDao boardDao;
	
	@RequestMapping("/rest/board")
	@ResponseBody
	public Map<String, Object> selectBoard(BoardBean mBean) {

		Map<String, Object> resMap = new HashMap<String, Object>();

		try{
			BoardBean bBean = boardDao.selectBoard(mBean);

			resMap.put("result", "ok");
			resMap.put("boardBean", bBean);
		} catch(Exception e) {
			e.printStackTrace();
			resMap.put("result", "fail");
		}
		return resMap;

	}
	
	
	@RequestMapping("/rest/selectBoardList")
	@ResponseBody
	public Map<String, Object> selectBoardList() {

		Map<String, Object> resMap = new HashMap<String, Object>();

		try{
			List<BoardBean> list = boardDao.selectBoardList();

			resMap.put("result", "ok");
			resMap.put("boardList", list);
		} catch(Exception e) {
			e.printStackTrace();
			resMap.put("result", "fail");
		}
		return resMap;

	}
	
	@RequestMapping("/rest/insertBoard")
	@ResponseBody
	public Map<String, Object> insertBoard(BoardBean bBean) {

		Map<String, Object> resMap = new HashMap<String, Object>();

		try{
			int resVal = boardDao.insertBoard(bBean);

			if(resVal > 0)
				resMap.put("result", "ok");

		} 
		catch(DuplicateKeyException dke) {
			resMap.put("resultMsg", "이미 존재하는 USER_ID입니다.");
		}
		catch(Exception e) {
			e.printStackTrace();
			resMap.put("result", e.getMessage());
		}
		return resMap;

	}
	
	@RequestMapping("/rest/updateBoard")
	@ResponseBody
	public Map<String, Object> updateBoard(BoardBean bBean) {

		Map<String, Object> resMap = new HashMap<String, Object>();

		try{
			int resVal = boardDao.updateBoard(bBean);

			if(resVal > 0){
				resMap.put("result", "ok");
				resMap.put("resultMsg", "업데이트에 성공하였습ㄴ디ㅏ.");
			}
			else
				resMap.put("resultMsg", "존재하지 않는 USER_ID입니다.");
		} 
		catch(Exception e) {
			e.printStackTrace();
			resMap.put("result", e.getMessage());
		}
		return resMap;

	}
	
	@RequestMapping("/rest/deleteBoard")
	@ResponseBody
	public Map<String, Object> deleteBoard(BoardBean mBean) {

		Map<String, Object> resMap = new HashMap<String, Object>();

		try{
			int resVal = boardDao.deleteBoard(mBean);

			if(resVal > 0){
				resMap.put("result", "ok");
				resMap.put("resultMsg", "삭제에 성공하였습니다.");
			}
			else
				resMap.put("resultMsg", "존재하지 않는 USER_ID입니다.");
		} 
		catch(Exception e) {
			e.printStackTrace();
			resMap.put("result", e.getMessage());
		}
		return resMap;

	}
}
