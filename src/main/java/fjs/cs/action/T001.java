package fjs.cs.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fjs.cs.common.Constants;
import fjs.cs.dao.T001Dao;
import fjs.cs.dto.T001Dto;

@WebServlet("/T001")
public class T001 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher myRD = null;
		//hiển thị màn hình login
		myRD = req.getRequestDispatcher(Constants.T001_LOGIN);
		myRD.forward(req, resp);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
		resp.setContentType("text/html");
		
		//Lấy thông tin đăng nhập từ resquest
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		// Kiểm tra đăng nhập
		T001Dao loginDao = new T001Dao();
		T001Dto result = loginDao.checkLogin(username, password);
		
		if (result != null) {
			resp.sendRedirect("/CustomerJspServlet/T002");
		}else {
			String messageErrors = Constants.MESSAGE_ERROR_USER_NOT_EXIST;
			req.setAttribute("error", messageErrors);
			RequestDispatcher dispatcher = req.getRequestDispatcher(Constants.T001_LOGIN);
			dispatcher.forward(req, resp);
		}
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doDelete(req, resp);
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPut(req, resp);
	}
}
