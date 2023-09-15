package fjs.cs.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fjs.cs.common.Constants;
import fjs.cs.sevices.T001Sevice;
import fjs.cs.sevices.impl.IT001Sevice;

@WebServlet("/T001")
public class T001 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IT001Sevice t001Sevices;
	
	public T001() {
		t001Sevices = new T001Sevice();
	}
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
		String inputUsername = req.getParameter("username");
		String inputPassword = req.getParameter("password");
		
		if (inputUsername == null || inputUsername.isEmpty()) {
			req.setAttribute("errorNoValueUser", Constants.MESSAGE_ERROR_NO_INPUT_USERNAME);
		} else if (inputPassword == null || inputPassword.isEmpty()) {
			req.setAttribute("errorNoValuePass", Constants.MESSAGE_ERROR_NO_INPUT_PASSWORD);
		} else {
			// Kiểm tra đăng nhập
			List<Integer> result = t001Sevices.checkLogin(inputUsername, inputPassword);
			if (result != null && !result.isEmpty()) {
				resp.sendRedirect(Constants.T002_HOME);
				return;
			}else {
				req.setAttribute("errorfail", Constants.MESSAGE_ERROR_USER_NOT_EXIST);
			}
		}
		
		// Lưu trữ giá trị đã nhập
	    req.setAttribute("username", inputUsername);
	    req.setAttribute("password", inputPassword);
		showLoginPage(req, resp);
	}
	private void showLoginPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher(Constants.T001_LOGIN);
        dispatcher.forward(req, resp);
    }
}
