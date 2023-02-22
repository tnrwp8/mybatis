package com.yjhcompany.home.dao;

import com.yjhcompany.home.dto.MemberDto;

public interface Mapper {

	public void joinDao(String mid, String mpw, String mname , String memail);//회원 가입 메서드
	public int loginCheck(String mid, String mpw);//로그인 체크 매서드
	public MemberDto memberInfo(String mid);
}
