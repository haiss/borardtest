package com.javalec.ex.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.ex.dao.boardDAO;
import com.javalec.ex.dto.boardDTO;

public class BContentCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		String id = request.getParameter("id");
		boardDAO dao = new boardDAO();
		boardDTO dto = dao.contentView(id);
		
		request.setAttribute("content_view", dto);
		
	}

}
