package com.javalec.ex.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.ex.dao.boardDAO;

public class BReplyCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		String id = request.getParameter("id");
	//	String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String group = request.getParameter("group");
		String step = request.getParameter("step");
		String indent = request.getParameter("indent");
		
		boardDAO dao = new boardDAO();
		dao.reply(id, name, title, content, group, step, indent);
		
	}

}
