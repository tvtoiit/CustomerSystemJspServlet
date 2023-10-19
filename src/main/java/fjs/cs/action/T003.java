package fjs.cs.action;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fjs.cs.common.Constants;
import fjs.cs.dao.impl.T003Dao;
import fjs.cs.dto.MstCustomer;

/**
 * Servlet này xử lý các yêu cầu liên quan đến quản lý thông tin khách hàng.
 */
@WebServlet("/T003")
public class T003 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final T003Dao t003Dao = new T003Dao();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		String id = request.getParameter("id");
		if (id != null) {
			try {
				MstCustomer listCustomerById = t003Dao.getCustomerById(Integer.parseInt(id));
				request.setAttribute("id", listCustomerById.getCustomerId());
				request.setAttribute("name", listCustomerById.getCustomerName());
				request.setAttribute("sex", listCustomerById.getSex());
				request.setAttribute("birthday", listCustomerById.getBirthDay());
				request.setAttribute("email", listCustomerById.getEmail());
				request.setAttribute("address", listCustomerById.getAddress());
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		request.getRequestDispatcher(Constants.T003_EDIT).forward(request, resp);
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
	    
	    HttpSession session = req.getSession();
	    BigDecimal loggedInPsnCd = (BigDecimal) session.getAttribute("loggedInPsnCd");

	    if (id != null && !id.isEmpty()) {
	        updateCustomer(id, name, sex, birthday, email, address);
	    } else {
	        createCustomer(name, sex, birthday, email, address, loggedInPsnCd);
	    }
	    resp.sendRedirect(Constants.T002_HOME);
	}

	private void updateCustomer(String id, String name, String sex, String birthday, String email, String address) {
	    try {
	        MstCustomer dtoUpdate = new MstCustomer();
	        if (id != null) {
	        	BigDecimal idBigDecimal = new BigDecimal(id);
	        	dtoUpdate.setCustomerId(idBigDecimal);
	        	dtoUpdate.setCustomerName(name);
	        	dtoUpdate.setSex(sex);
	        	dtoUpdate.setBirthDay(birthday);
	        	dtoUpdate.setEmail(email);
	        	dtoUpdate.setAddress(address);
	            t003Dao.update(dtoUpdate);
	        }
	    } catch (NumberFormatException e) {
	        e.printStackTrace();
	    }
	}

	private void createCustomer(String name, String sex, String birthday, String email, String address, BigDecimal loggedInPsnCd) {
	    MstCustomer newCustomer = new MstCustomer();
	    newCustomer.setCustomerName(name);
	    newCustomer.setSex(sex);
	    newCustomer.setBirthDay(birthday);
	    newCustomer.setEmail(email);
	    newCustomer.setAddress(address);
	    t003Dao.save(newCustomer, loggedInPsnCd);
	}

}
