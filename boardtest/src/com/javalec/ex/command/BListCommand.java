package com.javalec.ex.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.ex.dao.boardDAO;
import com.javalec.ex.dto.boardDTO;

public class BListCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		boardDAO dao = new boardDAO();
		ArrayList<boardDTO> dtos = dao.list();
		request.setAttribute("list", dtos);
	}
}