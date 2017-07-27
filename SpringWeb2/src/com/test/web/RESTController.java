package com.test.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.web.bean.MemberBean;
import com.test.web.dao.MemberDao;

@Controller
public class RESTController {

	@Autowired
	private MemberDao memberDao;
	//DB-->Bean-->JSON
	@RequestMapping("/rest/selectMember")
	@ResponseBody
	public Map<String, Object> selectMember(MemberBean Bean) {

		Map<String, Object> resMap = new HashMap<String, Object>();

		try{
			MemberBean mBean = memberDao.selectMember(Bean);

			resMap.put("result", "ok");
			resMap.put("memberBean", mBean);
		} catch(Exception e) {
			e.printStackTrace();
			resMap.put("result", "fail");
		}
		return resMap;

	}


	//DB-->Bean-->JSON
	@RequestMapping("/rest/selectMemberList")
	@ResponseBody
	public Map<String, Object> selectMemberList() {

		Map<String, Object> resMap = new HashMap<String, Object>();

		try{
			List<MemberBean> list = memberDao.selectMemberList();

			resMap.put("result", "ok");
			resMap.put("memberList", list);
		} catch(Exception e) {
			e.printStackTrace();
			resMap.put("result", "fail");
		}
		return resMap;

	}
	
	
	//
	
	
	@RequestMapping("/rest/insertMember")
	@ResponseBody
	public Map<String, Object> insertMember(MemberBean mBean) {

		Map<String, Object> resMap = new HashMap<String, Object>();

		try{
			int resVal = memberDao.insertMember(mBean);

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
	
	@RequestMapping("/rest/updateMember")
	@ResponseBody
	public Map<String, Object> updateMember(MemberBean mBean) {

		Map<String, Object> resMap = new HashMap<String, Object>();

		try{
			int resVal = memberDao.updateMember(mBean);

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
	
	@RequestMapping("/rest/deleteMember")
	@ResponseBody
	public Map<String, Object> deleteMember(MemberBean mBean) {

		Map<String, Object> resMap = new HashMap<String, Object>();

		try{
			int resVal = memberDao.deleteMember(mBean);

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
