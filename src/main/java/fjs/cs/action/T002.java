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
import fjs.cs.dao.impl.T002Dao;
import fjs.cs.dto.MstCustomer;

@WebServlet("/T002")
public class T002 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	T002Dao t002Dao = new T002Dao();
	MstCustomer t002Dto = new MstCustomer();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<MstCustomer> getDataAll = t002Dao.getData();
		List<MstCustomer> resultPagaOne = paginateList(getDataAll, 1, 15);
		t002Dto.setPageData(resultPagaOne);
		
		req.setAttribute("model", t002Dto);
		req.setAttribute("tag", 1);
		
		int endPage = calculateEndPage(getDataAll.size(), 15);
		req.setAttribute("endl", endPage);
	    RequestDispatcher myRD = req.getRequestDispatcher(Constants.T002_SEARCH);
	    myRD.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
	    resp.setContentType("text/html");
	    List<MstCustomer> listPaga = handleActions(request, t002Dao);
	    
	    int pageSize = 15;
        
        int endPage = calculateEndPage(listPaga.size(), pageSize);
        
        int page = handlePagination(request, endPage);
        
        List<MstCustomer> paginatedList = paginateList(listPaga, page, pageSize);
        t002Dto.setPageData(paginatedList);
        request.setAttribute("tag", page);
        request.setAttribute("endl", endPage);
        request.setAttribute("model", t002Dto);
	    
	    RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.T002_SEARCH);
	    dispatcher.forward(request, resp);
	}
	
	private int handlePagination(HttpServletRequest request, int endPage) {
        String currentPageStr = request.getParameter("currentPage");
        int currentPage;
        if (currentPageStr != null && !currentPageStr.isEmpty()) {
            currentPage = Integer.parseInt(currentPageStr);
        } else {
        	// Nếu không có "currentPage" hoặc nó trống, mặc định là trang đầu tiên (1).
            currentPage = 1; 
        }
     // Gọi phương thức getPage để phân trang và trả về trang hiện tại.
        return getPage(request, currentPage, endPage);
    }
	
	private int calculateEndPage(int totalItems, int pageSize) {
        int endPage = totalItems / pageSize;
        if (totalItems % pageSize != 0) {
            endPage++;
        }
        return endPage;
    }
	
	private List<MstCustomer> handleActions(HttpServletRequest request, T002Dao t002Dao) {
		String search = request.getParameter("searchAction");
	    String delete = request.getParameter("deleteAction");
	    String name = request.getParameter("txtCustomerName");
	    String sex = request.getParameter("browser");
	    String birthdayFrom = request.getParameter("txtBirthdayFromName");
	    String birthdayTo = request.getParameter("txtBirthdayToName");
	    
	    String[] selectedCustomerIds = request.getParameterValues("selectedCustomers");
	    if (search != null && search.equals("searchAction")) {
	    	return handleSearch(name, sex, birthdayFrom, birthdayTo);
	    } else if (delete != null && delete.equals("deleteAction")) {
	    	handleDelete(selectedCustomerIds);
	    }
	    return t002Dao.getData();
	}

	private List<MstCustomer> handleSearch(String name, String sex, String birthdayFrom, String birthdayTo) {
	    List<MstCustomer> resultSearch = t002Dao.getDataSearch(name, sex, birthdayFrom, birthdayTo);
	    return resultSearch;
	}

	/**
	 * Xóa dữ liệu khách hàng dựa trên danh sách các ID được chọn.
	 * 
	 * @param selectedCustomerIds Một mảng chứa các ID của các khách hàng cần xóa.
	 */
	private void handleDelete(String[] selectedCustomerIds) {
	    if (selectedCustomerIds != null) {
	        t002Dao.deleteData(selectedCustomerIds);
	    }
	}

	/**
	 * Xác định trang mới dựa trên trang hiện tại, hành động yêu cầu và trang cuối cùng.
	 *
	 * @param currentPage Trang hiện tại.
	 * @param pageAction  Hành động yêu cầu (ví dụ: "first", "previous", "next", "last").
	 * @param endPage     Trang cuối cùng.
	 * @return Trang mới sau khi xử lý hành động yêu cầu.
	 */
	private int getPage(HttpServletRequest request, int currentPage, int endPage) {
		String pageAction = request.getParameter("pageAction");
	    if (pageAction != null && !pageAction.isEmpty()) {
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
	public static List<MstCustomer> paginateList(List<MstCustomer> fullList, int page, int pageSize) {
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
