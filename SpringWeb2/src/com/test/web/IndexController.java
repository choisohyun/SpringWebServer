package com.test.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.web.bean.BoardBean;
import com.test.web.bean.MemberBean;
import com.test.web.dao.BoardDao;
import com.test.web.dao.MemberDao;

@Controller
public class IndexController {
	
	@Autowired
	private MemberDao memberDao;
	@Autowired 
	private BoardDao boardDao;
	
	@RequestMapping("/hello")
	public String helloWorld(String id, String pw) {
		
		System.out.println("id : " + id + ", pw : " + pw);
		
		return "helloWorld";
	}
	
	@RequestMapping("/test/hello")
	public String test2(MemberBean tBean) {
		
		System.out.println("id: "+tBean.getUserId()+", pw: "+tBean.getUserPw()+", addr: "+tBean.getAddr());
		
		MemberBean memberBean = memberDao.selectMember(tBean);
		System.out.println("USER_ID : " + memberBean.getUserId());
		System.out.println("USER_PW : " + memberBean.getUserPw());
		System.out.println("NAME : " + memberBean.getName());
		System.out.println("HP : " + memberBean.getHp());
		
		return "helloWorld";
		
	}
	
	@RequestMapping("/board")
	public String BoardBean(BoardBean boardBean) {
		BoardBean boardBean1 = boardDao.selectBoard(boardBean);
		System.out.println("ID : " + boardBean1.getId());
		System.out.println("TITLe : " + boardBean1.getTitle());
		System.out.println("CONTENT : " + boardBean1.getContent());
		System.out.println("REG_DATE : " + boardBean1.getRegDate());
		System.out.println("USER_ID : " + boardBean1.getUserId());

		return "helloWorld";
		
	}
}
