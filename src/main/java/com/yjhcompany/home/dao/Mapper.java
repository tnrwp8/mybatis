package com.yjhcompany.home.dao;

import java.util.List;

import com.yjhcompany.home.dto.BoardDto;
import com.yjhcompany.home.dto.MemberDto;

public interface Mapper {
	
	//회원관리용
	public void joinDao(
			String mid,
			String mpw,
			String mname
			, String memail
			);//회원 가입 메서드
	public int loginCheck(
			String mid, 
			String mpw
			);//로그인 체크 매서드
	public MemberDto memberInfo(String mid);
	public void memberDelete(String mid);
	
	//게시판용
	public void writeDao(
			String btitle,
			String bcontent,
			String bmid,
			String bmname
			);//게시판 글쓰기
	public List<BoardDto> listDao();//게시판 모든 글목록 가져오기
}
