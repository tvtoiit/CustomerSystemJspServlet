package fjs.cs.action;

import java.io.IOException;
import java.util.Collections;
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
	    showData(req, result);
	    RequestDispatcher myRD = req.getRequestDispatcher(Constants.T002_SEARCH);
	    myRD.forward(req, resp);
	}
	
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
	    resp.setContentType("text/html");
	    
	    // Lấy dữ liệu từ các trường đầu vào
	    String name = request.getParameter("txtCustomerName");
	    String sex = request.getParameter("browser");
	    String birthdayFrom = request.getParameter("txtBirthdayFromName");
	    String birthdayTo = request.getParameter("txtBirthdayToName");
	    String tag = request.getParameter("currentPage");
	    String pageStr = request.getParameter("pageAction");
	    String search = request.getParameter("searchAction");
	    String delete = request.getParameter("deleteAction");
	    String[] selectedCustomerIds = request.getParameterValues("selectedCustomers");
	    
	    // Xử lý tìm kiếm
	    if (search != null && !search.isEmpty()) {
	    	if (search.equals("searchAction")) {
		        handleSearch(request, name, sex, birthdayFrom, birthdayTo, tag, pageStr, selectedCustomerIds);
		    }
	    }
	    
	    // Xử lý xóa
	    if (delete != null && !delete.isEmpty()) {
	    	if (delete.equals("deleteAction")) {
		        handleDelete(selectedCustomerIds);
		        List<mstcustomer> result = t002Dao.getData();
		        showData(request, result);
		    }
	    }
	    
	    RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.T002_SEARCH);
	    dispatcher.forward(request, resp);
	}

	// Hàm xử lý tìm kiếm
	private void handleSearch(HttpServletRequest request, String name, String sex, String birthdayFrom, String birthdayTo, String tag, String pageStr, String[] selectedCustomerIds) {
	    // Tìm kiếm dữ liệu dựa trên các tham số đầu vào
	    List<mstcustomer> resultSearch = t002Dao.getDataSearch(name, sex, birthdayFrom, birthdayTo);
	    
	    if (tag == null || tag.isEmpty()) {
	        request.setAttribute("tag", 1);
	    }
	    
	    int endPage = resultSearch.size() / 15;
	    if (resultSearch.size() % 15 != 0) {
	        endPage++;
	    }
	    
	    if (resultSearch.size() > 15) {
	        int page = getPage(Integer.parseInt(tag), pageStr, endPage);
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
	}

	// Hàm xử lý xóa
	private void handleDelete(String[] selectedCustomerIds) {
	    if (selectedCustomerIds != null) {
	        t002Dao.deleteData(selectedCustomerIds);
	    }
	}
	
	private void showData(HttpServletRequest req, List<mstcustomer> result) {
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
	}

	/**
	 * Xác định trang mới dựa trên trang hiện tại, hành động yêu cầu và trang cuối cùng.
	 *
	 * @param currentPage Trang hiện tại.
	 * @param pageAction  Hành động yêu cầu (ví dụ: "first", "previous", "next", "last").
	 * @param endPage     Trang cuối cùng.
	 * @return Trang mới sau khi xử lý hành động yêu cầu.
	 */
	private int getPage(int currentPage, String pageAction, int endPage) {
	    if (pageAction != null && !pageAction.isEmpty()) {
	    	// Xử lý các hành động yêu cầu khác nhau
	        if (pageAction.equals("first")) {
	            return 1;
	        } else if (pageAction.equals("previous")) {
	            return Math.max(currentPage - 1, 1);
	        } else if (pageAction.equals("next")) {
	            return Math.min(currentPage + 1, endPage);
	        } else if (pageAction.equals("last")) {
	            return endPage;
	        }
	    }
	    return currentPage;
	}
	
	/**
	 * Phân trang một danh sách dữ liệu bất kỳ dựa trên trang và kích thước trang cho trước.
	 *
	 * @param fullList  Danh sách dữ liệu đầy đủ.
	 * @param page      Trang hiện tại (bắt đầu từ 1).
	 * @param pageSize  Kích thước trang (số lượng mục trên mỗi trang).
	 * @param <T>       Kiểu dữ liệu của danh sách.
	 * @return Danh sách con chứa dữ liệu của trang hiện tại hoặc danh sách trống nếu tham số không hợp lệ hoặc không có dữ liệu cho trang hiện tại.
	 */
	public static <T> List<T> paginateList(List<T> fullList, int page, int pageSize) {
	    // Kiểm tra nếu danh sách đầy đủ là null hoặc trống hoặc tham số không hợp lệ
	    if (fullList == null || fullList.isEmpty() || page <= 0 || pageSize <= 0) {
	        // Trả về danh sách trống nếu tham số không hợp lệ hoặc không có dữ liệu
	        return Collections.emptyList();
	    }

	    int totalItems = fullList.size();
	    int startIndex = (page - 1) * pageSize;
	    int endIndex = Math.min(startIndex + pageSize, totalItems);

	    // Kiểm tra nếu startIndex vượt quá tổng số mục
	    if (startIndex >= totalItems) {
	        // Trả về danh sách trống nếu không có dữ liệu cho trang hiện tại
	        return Collections.emptyList();
	    }

	    // Trả về danh sách con chứa dữ liệu của trang hiện tại
	    return fullList.subList(startIndex, endIndex);
	}

}
