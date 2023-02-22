package com.yjhcompany.home.dto;

import java.sql.Timestamp;

public class BoardDto {
	 private int bid;
	 private String btitle;
	 private String bcontent;
	 private String bmid;
	 private String bname;
	 private Timestamp bdate;
	public BoardDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BoardDto(int bid, String btitle, String bcontent, String bmid, String bname, Timestamp bdate) {
		super();
		this.bid = bid;
		this.btitle = btitle;
		this.bcontent = bcontent;
		this.bmid = bmid;
		this.bname = bname;
		this.bdate = bdate;
	}
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public String getBtitle() {
		return btitle;
	}
	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}
	public String getBcontent() {
		return bcontent;
	}
	public void setBcontent(String bcontent) {
		this.bcontent = bcontent;
	}	
	public String getBmid() {
		return bmid;
	}
	public void setBmid(String bmid) {
		this.bmid = bmid;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	public Timestamp getBdate() {
		return bdate;
	}
	public void setBdate(Timestamp bdate) {
		this.bdate = bdate;
	}
	
}
