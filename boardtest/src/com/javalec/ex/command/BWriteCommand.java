package com.javalec.ex.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.ex.dao.boardDAO;

public class BWriteCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		String name = request.getParameter("name");
		String pw = request.getParameter("pw");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		boardDAO dao = new boardDAO();
		dao.write(name, pw, title, content);
	}
}
