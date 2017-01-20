package com.javalec.ex.frontcontroller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.ex.command.BCommand;
import com.javalec.ex.command.BContentCommand;
import com.javalec.ex.command.BDeleteCommand;
import com.javalec.ex.command.BListCommand;
import com.javalec.ex.command.BModifyCommand;
import com.javalec.ex.command.BReplyCommand;
import com.javalec.ex.command.BReplyViewCommand;
import com.javalec.ex.command.BWriteCommand;

/**
 * Servlet implementation class BFrontController
 */
@WebServlet("*.do")
public class BFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BFrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		ActionDo(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		ActionDo(request, response);
	}
	private void ActionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("actionDo");
		
		request.setCharacterEncoding("UTF-8");
		
		String viewPage = null;//보여주는 페이지
		BCommand command = null;//무엇을 실행
		
		String uri = request.getRequestURI();//URI 를 불러옴
		String conPath = request.getContextPath();
		String com = uri.substring(conPath.length());//URI에서 ContextPath부분을 잘라냄
		
		if(com.equals("/write_view.do")){
			viewPage = "write_view.jsp";
		}else if(com.equals("/write.do")){
			command = new BWriteCommand();
			command.execute(request,response);
			viewPage = "list.do";
			
		}else if(com.equals("/list.do")){//목록
			command=new BListCommand();
			command.execute(request,response);
			viewPage = "list.jsp";
			
		}else if (com.equals("/content_view.do")) {//
			command = new BContentCommand();
			command.execute(request,response);
			viewPage = "content_view.jsp";
			
		}else if (com.equals("/modify.do")) {//수정
			command = new BModifyCommand();
			command.execute(request,response);
			viewPage = "list.do";
			
		}else if (com.equals("/delete.do")) {//삭제
			command = new BDeleteCommand();
			command.execute(request,response);
			viewPage = "list.do";
			
		}else if (com.equals("/reply_view.do")) {//답글
			command = new BReplyViewCommand();
			command.execute(request,response);
			viewPage = "reply_view.jsp";
			
		}else if (com.equals("/reply.do")) {
			command = new BReplyCommand();
			command.execute(request,response);
			viewPage = "list.do";
			
		}
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
}
