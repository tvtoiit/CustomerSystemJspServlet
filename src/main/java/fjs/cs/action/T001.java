package fjs.cs.action;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fjs.cs.common.Constants;
import fjs.cs.dao.impl.T001Dao;
import fjs.cs.dto.MstUser;

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
	
	/**
	 * Xử lý yêu cầu POST từ trang đăng nhập.
	 *
	 * @param req HttpServletRequest của yêu cầu.
	 * @param resp HttpServletResponse để xử lý phản hồi.
	 * @throws ServletException Nếu có lỗi trong quá trình xử lý yêu cầu.
	 * @throws IOException Nếu có lỗi trong việc xử lý đầu ra.
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
		// Thiết lập loại nội dung của phản hồi là text/html
		resp.setContentType("text/html");
		
		// Tạo một đối tượng DAO để kiểm tra đăng nhập
		T001Dao t001Dao = new T001Dao();
		
		//Lấy thông tin đăng nhập từ resquest
		String inputUsername = req.getParameter("username");
		String inputPassword = req.getParameter("password");
		
		// Kiểm tra xem người dùng đã nhập tên người dùng và mật khẩu hay chưa
		if (inputUsername == null || inputUsername.isEmpty()) {
			// Nếu không có tên người dùng, đặt thuộc tính lỗi cho thông báo
			req.setAttribute("errorNoValueUser", Constants.MESSAGE_ERROR_NO_INPUT_USERNAME);
		} else if (inputPassword == null || inputPassword.isEmpty()) {
			// Nếu không có mật khẩu, đặt thuộc tính lỗi cho thông báo
			req.setAttribute("errorNoValuePass", Constants.MESSAGE_ERROR_NO_INPUT_PASSWORD);
		} else {
			// Nếu có tên người dùng và mật khẩu, kiểm tra đăng nhập
			List<Integer> result = t001Dao.checkLogin(inputUsername, inputPassword);
			
			if (result != null && !result.isEmpty() && result.get(0) > 0) {
				
	            List<MstUser> userInfo = t001Dao.getUserInfo(inputUsername, inputPassword);
	            
	            if (!userInfo.isEmpty()) {
	                HttpSession session = req.getSession();
	                for (MstUser c : userInfo) {
	                	BigDecimal loggedInPsnCd = c.getPsnCd();
	                	session.setAttribute("loggedInPsnCd", loggedInPsnCd);
	                }
	                // Nếu đăng nhập thành công, chuyển hướng đến màn hình T002
	                resp.sendRedirect(Constants.T002_HOME);
	                return;
	            }
			}else {
				// Nếu đăng nhập không thành công, đặt thuộc tính lỗi cho thông báo
				req.setAttribute("errorfail", Constants.MESSAGE_ERROR_USER_NOT_EXIST);
			}
		}
		
		// Lưu trữ giá trị đã nhập để hiển thị lại trên giao diện
	    req.setAttribute("username", inputUsername);
	    req.setAttribute("password", inputPassword);
	    
	    // Hiển thị trang đăng nhập
		showLoginPage(req, resp);
	}
	
	/**
	 * Chuyển hướng yêu cầu đến trang đăng nhập (T001_LOGIN).
	 */
	private void showLoginPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher(Constants.T001_LOGIN);
        dispatcher.forward(req, resp);
    }
}
