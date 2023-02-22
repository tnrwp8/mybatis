package com.yjhcompany.home.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yjhcompany.home.dao.Mapper;
import com.yjhcompany.home.dto.MemberDto;

@Controller
public class HomeController {

	@Autowired
	private SqlSession sqlSession;
	
	@RequestMapping(value = "/")
	public String index() {
	
		return "index";
	}
	@RequestMapping(value = "/index")
	public String index2() {
	
		return "index";
	}
	
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
	@RequestMapping(value = "/login")
	public String login() {
	
	return "login";
}
	@RequestMapping(value = "/loginOk")
	public String loginOk(HttpServletRequest request,Model model) {
		
		String mid = request.getParameter("mid");
		String mpw = request.getParameter("mpw");
		
		Mapper dao = sqlSession.getMapper(Mapper.class);
		int loginCheck = dao.loginCheck(mid, mpw);//1이면 아이디와 비밀번호가 모두 일치하는 데이터가 존재-> 로그인 성공
		model.addAttribute("loginCheck", loginCheck);
		
		if(loginCheck == 1) {
			HttpSession session = request.getSession();
			session.setAttribute("sessionid", mid);
			session.setAttribute("ValidMem", "yes");
			model.addAttribute("memberid", mid);
			
			return "loginOk";
		}else {
			return "redirect:login";
		}
		
	
}
	   @RequestMapping(value="/logout")
	   public String logout(HttpServletRequest request) {
	      HttpSession session = request.getSession();
	      session.invalidate();//로그아웃
	      
	      return "redirect:index";
	      }
	   @RequestMapping(value="/memberInfo")
	   public String memberInfo(HttpServletRequest request,Model model) {
		   
	      HttpSession session = request.getSession();
	      String sessionid =(String)session.getAttribute("sessionid");//현재 로그인한 회원의 아이디
	      
	      Mapper dao = sqlSession.getMapper(Mapper.class);
	      MemberDto memberDto = dao.memberInfo(sessionid);
	      
	      model.addAttribute("memberDto", memberDto);
	      
	      return "memberInfo";
	   }
	   @RequestMapping(value="/memberDelete")
	   public String memberDelete(HttpServletRequest request,Model model) {
		   
	      HttpSession session = request.getSession();
	      String sessionid =(String)session.getAttribute("sessionid");//현재 로그인한 회원의 아이디
	      
	      Mapper dao = sqlSession.getMapper(Mapper.class);
	      dao.memberDelete(sessionid);//회원정보 삭제
	   
	      return "redirect:index";
	   }
	   @RequestMapping(value="/writeForm")
	   public String writeForm(HttpServletRequest request,Model model) {
		   
	      HttpSession session = request.getSession();
	      String sessionid =(String)session.getAttribute("sessionid");//현재 로그인한 회원의 아이디
	      
	      Mapper dao = sqlSession.getMapper(Mapper.class);
	      
	      if(sessionid == null) {//로그인 하지 않은 상태
	    	  model.addAttribute("mid", "GUEST");
	    	  model.addAttribute("mname", "비회원");
	      }else {//로그인 한 상태
	    	  MemberDto memberdto =dao.memberInfo(sessionid);
	    	  model.addAttribute("mid", memberdto.getMid());
	    	  model.addAttribute("mname", memberdto.getMname());
	      }
	
	      return "writeForm";
	   }
	   @RequestMapping(value="/write")
	   public String write(HttpServletRequest request,Model model) {
		   
	      HttpSession session = request.getSession();
	      String sessionid =(String)session.getAttribute("sessionid");//현재 로그인한 회원의 아이디
	      
	      Mapper dao = sqlSession.getMapper(Mapper.class);
	      
	      String btitle =  request.getParameter("btitle");//글재목
	      String bcontent = request.getParameter("bcontent");//글내용
	      String bmid = null;
    	  String bmname = null;
    	  
	      if(sessionid == null) {//로그인 하지 않은 상태
	    	  bmid = "GUEST";
	    	  bmname = "비회원";
	    	
	      }else {//로그인 한 상태
	    	  MemberDto memberdto =dao.memberInfo(sessionid);
	    	  bmid =memberdto.getMid();
	    	  bmname = memberdto.getMname();
	      }
	      
	      
	
	      return "redirect:list";
	   }
}
