package com.aca.web0823.reboard.domain;

public class ReBoard {
	private int reboard_id;
	private String title;
	private String writer;
	private String content;
	private String regdate;
	private int hit;
	private int team;
	private int step;
	private int depth;
	public int getReboard_id() {
		return reboard_id;
	}
	public void setReboard_id(int reboard_id) {
		this.reboard_id = reboard_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public int getTeam() {
		return team;
	}
	public void setTeam(int team) {
		this.team = team;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}

}
