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
import fjs.cs.dao.T002Dao;
import fjs.cs.dto.mstcustomer;

@WebServlet("/T002")
public class T002 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	T002Dao t002Dao = new T002Dao();
	mstcustomer t002Dto = new mstcustomer();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<mstcustomer> result = t002Dao.getData();
		if (result.size() > 15) {
			int endPage = result.size() / 15;
	        if (result.size() % 15 != 0) {
	            endPage++;
	        }
	        req.setAttribute("endl", endPage);
	        t002Dto.setPageData(paginateList(result, 1, 15));
	        
		} else {
			t002Dto.setPageData(result);
			req.setAttribute("endl", 1);
		}
		req.setAttribute("model", t002Dto);
		req.setAttribute("tag", 1);
		
		RequestDispatcher myRD = req.getRequestDispatcher(Constants.T002_SEARCH);
		myRD.forward(req, resp);
	}
	
	/**
	 * Xử lý yêu cầu POST từ trang T002.
	 *
	 * @param request HttpServletRequest của yêu cầu.
	 * @param resp HttpServletResponse để xử lý phản hồi.
	 * @throws ServletException Nếu có lỗi trong quá trình xử lý yêu cầu.
	 * @throws IOException Nếu có lỗi trong việc xử lý đầu ra.
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
	    resp.setContentType("text/html");
	    // Lấy dữ liệu từ các trường đầu vào
	    String name = request.getParameter("txtCustomerName");
	    String sex = request.getParameter("browser");
	    String birthdayFrom = request.getParameter("txtBirthdayFromName");
	    String birthdayTo = request.getParameter("txtBirthdayToName");

	    // Tìm kiếm dữ liệu dựa trên các tham số đầu vào
	    List<mstcustomer> resultSearch = t002Dao.getDataSearch(name, sex, birthdayFrom, birthdayTo);

	    String tag = request.getParameter("tag");
	    String pageStr = request.getParameter("page");

	    if(tag == null || tag == "") {
        	request.setAttribute("tag", 1);
        }
	    
	    int endPage = resultSearch.size() / 15;
	    if (resultSearch.size() % 15 != 0) {
	    	endPage++;
	    }
	    
	    if (resultSearch.size() > 15) {
	        int page = getPage(tag, pageStr, endPage);

	        request.setAttribute("tag", page);
	        List<mstcustomer> pageData = paginateList(resultSearch, page, 15);
	        t002Dto.setPageData(pageData);
	    } else {
	    	request.setAttribute("tag", 1);
	        t002Dto.setPage(1);
	        t002Dto.setPageData(paginateList(resultSearch, 1, 15));
	    }
	    request.setAttribute("endl", endPage == 0 ? 1 : endPage);
	    request.setAttribute("name", name);
	    request.setAttribute("sex", sex);
	    request.setAttribute("birthdayFrom", birthdayFrom);
	    request.setAttribute("birthdayTo", birthdayTo);
	    request.setAttribute("model", t002Dto);
	    RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.T002_SEARCH);
	    dispatcher.forward(request, resp);
	}

	private int getPage(String tag, String pageStr, int endPage) {
	    if (tag != null && !tag.isEmpty()) {
	        try {
	            int page = Integer.parseInt(tag);
	            if (pageStr != null) {
	                if (pageStr.equals("1")) {
	                    page = 1;
	                } else if (pageStr.equals("2")) {
	                    page--;
	                } else if (pageStr.equals("3")) {
	                    page++;
	                } else if (pageStr.equals("4")) {
	                    page = endPage;
	                }
	            }
	            return page;
	        } catch (NumberFormatException e) {
	            
	        }
	    }
	    return 1; // Trang mặc định
	}

	
	public static <T> List<T> paginateList(List<T> fullList, int page, int pageSize) {
        if (fullList == null || fullList.isEmpty() || page < 1 || pageSize < 1) {
            return null; // Hoặc trả về danh sách trống hoặc xử lý theo yêu cầu của bạn
        }

        int totalItems = fullList.size();
        int startIndex = (page - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, totalItems);

        if (startIndex >= totalItems) {
            return null; // Không có dữ liệu cho trang hiện tại
        }

        return fullList.subList(startIndex, endIndex);
    }

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doDelete(req, resp);
	}
}
