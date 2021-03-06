package com.cos.blog.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.blog.action.Action;
import com.cos.blog.action.board.BoardDeleteProcAction;
import com.cos.blog.action.board.BoardDetailAction;
import com.cos.blog.action.board.BoardHomeAction;
import com.cos.blog.action.board.BoardSearchAction;
import com.cos.blog.action.board.BoardUpdateAction;
import com.cos.blog.action.board.BoardUpdateProcAction;
import com.cos.blog.action.board.BoardWriteAction;
import com.cos.blog.action.board.BoardWriteProcAction;
import com.cos.blog.action.kakao.KakaoJoinProcAction;
import com.cos.blog.action.kakao.KakaocallbackAction;
import com.cos.blog.action.user.UsersLoginAction;

// http://localhost:8000/blog/board
@WebServlet("/oauth/kakao")
public class KakaoController extends HttpServlet {
	private final static String TAG = "KakaoController : ";
	private static final long serialVersionUID = 1L;
       
    public KakaoController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// http://localhost:8000/blog/user?cmd=join
		String cmd = request.getParameter("cmd");
		System.out.println(TAG+"router : "+cmd);
		Action action = router(cmd);
		action.execute(request, response);
	}
	
	public Action router(String cmd) {
		if(cmd.equals("callback")) {
			// 홈페이지로 이동
			return new KakaocallbackAction();
		}else if(cmd.equals("joinProc")) {
			// 홈페이지로 이동
			return new KakaoJoinProcAction(); //Board의 목록
		}
		return null;
		
	}
	

}
