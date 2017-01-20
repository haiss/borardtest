package com.javalec.ex.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.ex.dao.boardDAO;
import com.javalec.ex.dto.boardDTO;

public class BReplyViewCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		String id = request.getParameter("id");
		boardDAO dao = new boardDAO();
		boardDTO dto = dao.reply_view(id);
		
		request.setAttribute("reply_view", dto);
		
	}

}
