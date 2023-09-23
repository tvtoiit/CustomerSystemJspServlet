package fjs.cs.action;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fjs.cs.common.Constants;
import fjs.cs.dao.T003Dao;
import fjs.cs.dto.mstcustomer;

/**
 * Servlet này xử lý các yêu cầu liên quan đến quản lý thông tin khách hàng.
 */
@WebServlet("/T003")
public class T003 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final T003Dao t003Dao = new T003Dao();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		
		if (id != null) {
			try {
				List<mstcustomer> dto = t003Dao.getCustomerById(Integer.parseInt(id));
				req.setAttribute("dto", dto);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		req.getRequestDispatcher(Constants.T003_EDIT).forward(req, resp);
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
		
		mstcustomer dto = new mstcustomer();
		
		dto.setCustomerName(name);
	    dto.setSex(sex);
	    dto.setBirthDay(birthday);
	    dto.setEmail(email);
	    dto.setAddress(address);
	    
	    // Lấy giá trị loggedInPsnCd từ session
	    HttpSession session = req.getSession();
	    BigDecimal loggedInPsnCd = (BigDecimal) session.getAttribute("loggedInPsnCd");
		
		//String ids = req.getParameter("id");
		if (id != "" && !id.isEmpty()) {
			BigDecimal decimalId = new BigDecimal(id);
			dto.setCustomerId(decimalId);
	        t003Dao.update(dto); 
		} else {
	        t003Dao.save(dto, loggedInPsnCd);
		}
		resp.sendRedirect(Constants.T002_HOME);
	}
}
