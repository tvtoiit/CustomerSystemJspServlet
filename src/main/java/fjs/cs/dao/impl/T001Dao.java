package fjs.cs.dao.impl;

import java.util.List;

import fjs.cs.dao.IT001Dao;
import fjs.cs.dto.MstUser;
import fjs.cs.rowmapper.MstUserMapper;
import fjs.cs.rowmapper.T001Mapper;

public class T001Dao extends AbstractDao<MstUser> implements IT001Dao {
	/**
	 * Kiểm tra đăng nhập của người dùng sử dụng tên người dùng và mật khẩu.
	 *
	 * @param user Tên người dùng để kiểm tra đăng nhập.
	 * @param pass Mật khẩu để kiểm tra đăng nhập.
	 * @return Danh sách chứa kết quả kiểm tra đăng nhập (1 nếu thành công, 0 nếu không)
	 * 		   hoặc null nếu có lỗi.
	 */
	@Override
	public List<Integer> checkLogin(String user, String pass) {
		// Truy vấn SQL để kiểm tra đăng nhập
		String query = "SELECT COUNT(*) AS CNT FROM MSTUSER WHERE DELETE_YMD IS NULL AND USERID = ? AND PASSWORD = ?";
		
		// Thực hiện truy vấn và ánh xạ kết quả vào danh sách sử dụng T001Mapper
		List<Integer> results = query(query, new T001Mapper(), user, pass);
		
		// Kiểm tra kết quả trả về và trả về danh sách hoặc null
		if (!results.isEmpty()) {
	        return results;
	    } else {
	        return null;
	    }
	}

	/**
	 * Truy vấn và lấy thông tin người dùng 
	 * 
	 * @param 	userId Thông tin tài khoản người dùng
	 * @param 	passWord Mật khẩu người dùng
	 * @return 	Danh sách các đối tượng mstuser chứa thông tin người dùng tìm thấy.
	 */
	@Override
	public List<MstUser> getUserInfo(String userId, String passWord) {
	    String query = "SELECT * FROM MSTUSER WHERE DELETE_YMD IS NULL AND USERID = ? AND PASSWORD = ?";
	    List<MstUser> results = query(query, new MstUserMapper(), userId, passWord);
		return results;
	}
}
