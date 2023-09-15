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
import fjs.cs.dto.mstcustomer;
import fjs.cs.sevices.T002Sevice;
import fjs.cs.sevices.impl.IT002Sevice;

@WebServlet("/T002")
public class T002 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IT002Sevice t002Sevice;

	public T002() {
		t002Sevice = new T002Sevice();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<mstcustomer> result = t002Sevice.getData();
		req.setAttribute("listData", result);

		RequestDispatcher myRD = req.getRequestDispatcher(Constants.T002_SEARCH);
		myRD.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		mstcustomer t002Dto = new mstcustomer();
		String name = request.getParameter("txtCustomerName");
		String sex = request.getParameter("browser");
		String birthdayFrom = request.getParameter("txtBirthdayFromName");
		String birthdayTo = request.getParameter("txtBirthdayToName");
		List<mstcustomer> resultSearch = t002Sevice.getDataSearch(name, sex, birthdayFrom, birthdayTo);

		if (resultSearch.size() > 15) { 
	        // Thực hiện phân trang 
	        String pageStr = request.getParameter("page");
	        int page;
	        if (pageStr != null && !pageStr.isEmpty()) {
	            page = Integer.parseInt(pageStr);
	        } else {
	            page = 1;
	        }

	        int startIndex = (page - 1) * 15;
	        int endIndex = Math.min(startIndex + 15, resultSearch.size());

	        List<mstcustomer> pageData = resultSearch.subList(startIndex, endIndex);
	        t002Dto.setPage(page);
	        t002Dto.setPageData(pageData);

	        int endPage = resultSearch.size() / 15;
	        if (resultSearch.size() % 15 != 0) {
	            endPage++;
	        }
	        request.setAttribute("tag", pageStr);
	        request.setAttribute("endP", endPage);
	        request.setAttribute("model", t002Dto);
	    } else {
	        t002Dto.setPage(1);
	        t002Dto.setPageData(resultSearch);
	    }
		request.setAttribute("listDataSearch", resultSearch);
		RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.T002_SEARCH);
		dispatcher.forward(request, resp);
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doDelete(req, resp);
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPut(req, resp);
	}

}
