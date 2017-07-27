package com.test.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.web.bean.MemberBean;
import com.test.web.dao.MemberDao;

@Controller
public class MemberController {

	@Autowired
	private MemberDao memberDao;

	//DB-->Bean-->JSON
	@RequestMapping("/rest/loginProc")
	@ResponseBody
	public Map<String, Object> selectMember(MemberBean bean) {

		Map<String, Object> resMap = new HashMap<String, Object>();

		try{
			MemberBean resBean = memberDao.loginProc(bean);

			if(resBean == null){
				resMap.put("result", "fail");
				resMap.put("resultMsg", "해당하는 유저가 없습니다. \n아이디와 비밀번호를 확인해 주세요.");
			} else{
				resMap.put("result", "ok");
				resMap.put("resultMsg", "로그인 성공");
				resMap.put("memberBean", resBean);
			}
		} catch(Exception e) {
			e.printStackTrace();
			resMap.put("result", "fail");
		}
		return resMap;

	}
}
