package com.yjhcompany.home.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yjhcompany.home.dao.Mapper;

@Controller
public class HomeController {

	@Autowired
	private SqlSession sqlSession;
	
	@RequestMapping(value = "/join")
		public String join() {
		
		return "join";
	}
	@RequestMapping(value = "/joinOk")
	public String joinOk(HttpServletRequest request,Model model) {
	
		String mid = request.getParameter("mid");
		String mpw = request.getParameter("mpw");
		String mname = request.getParameter("mname");
		String memail = request.getParameter("memail");
		
		Mapper dao = sqlSession.getMapper(Mapper.class);
		dao.joinDao(mid, mpw, mname, memail);
		model.addAttribute("mname",mname);
		
		return "joinOk";
	}
}
