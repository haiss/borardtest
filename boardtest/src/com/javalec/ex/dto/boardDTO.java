package com.javalec.ex.dto;

import java.sql.Timestamp;

public class boardDTO {
	private int id, hit, ggroup, step, indent;
	private String name, pw, title, content;
	private	Timestamp date;

	public boardDTO(){
		super();
	}
	public boardDTO(int id, int hit, int ggroup, int step, int indent, String name, String pw, String title,
			String content, Timestamp date) {
		this.id = id;
		this.hit = hit;
		this.ggroup = ggroup;
		this.step = step;
		this.indent = indent;
		this.name = name;
		this.pw = pw;
		this.title = title;
		this.content = content;
		this.date = date;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public int getGgroup() {
		return ggroup;
	}
	public void setGgroup(int group) {
		this.ggroup = group;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	public int getIndent() {
		return indent;
	}
	public void setIndent(int indent) {
		this.indent = indent;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	
	
}
