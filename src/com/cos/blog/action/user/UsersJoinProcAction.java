package com.cos.blog.action.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.User;

import com.cos.blog.action.Action;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.Users;
import com.cos.blog.repository.UsersRepository;
import com.cos.blog.util.SHA256;
import com.cos.blog.util.Script;

public class UsersJoinProcAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//0. 유효성 검사
		if(
			request.getParameter("username").equals("")||
			request.getParameter("username")==null||
			request.getParameter("password").equals("")||
			request.getParameter("password")==null||
			request.getParameter("email").equals("")||
			request.getParameter("email")==null||
			request.getParameter("address").equals("")||
			request.getParameter("address")==null
		) {
			return ;
		}
		
		
		
		//1. 파라메터 받기(x-www-form-urlencoded mime type -> key = value)
		String username = request.getParameter("username");
		String rawPassword = request.getParameter("password");
		String password=SHA256.encodeSha256(rawPassword);
		String email = request.getParameter("email");
		String address=request.getParameter("address");
		String userRole=RoleType.USER.toString();
		
		//2. User 오브젝트 변환
		Users user=Users.builder()
				.username(username)
				.password(password)
				.address(address)
				.email(email)
				.userRole(userRole)
				.build();
		
		//3. DB 연결 - Repository save 호출
		UsersRepository usersRepository=UsersRepository.getInstance();
		int result=usersRepository.save(user);
		
		//4. index.jsp 페이지 이동
		if(result==1) {
			Script.href("회원가입에 성공하였습니다.", "/blog/user?cmd=login", response);
		}else {
			Script.back("회원가입에 실패하셨습니다.", response);
		}
	}
}
