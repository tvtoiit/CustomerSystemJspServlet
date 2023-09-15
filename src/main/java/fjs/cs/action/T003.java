package fjs.cs.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fjs.cs.common.Constants;
import fjs.cs.sevices.T003Sevice;
import fjs.cs.sevices.impl.IT003Sevice;

@WebServlet("/T003")
public class T003 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IT003Sevice t003Sevice;
	
	public T003() {
		t003Sevice = new T003Sevice();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher myRD = null;
		myRD = req.getRequestDispatcher(Constants.T003_EDIT);
		myRD.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		String id = req.getParameter("customerId");
		String name = req.getParameter("customerName");
		String sex = req.getParameter("sex");
		String birthday = req.getParameter("birthday");
		String email = req.getParameter("email");
		String address = req.getParameter("address");
		
		
		
	}
}
