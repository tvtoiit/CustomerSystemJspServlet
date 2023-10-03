package fjs.cs.dao;

import java.util.List;

import fjs.cs.dto.mstuser;

/**
 * Interface IT001Dao định nghĩa các phương thức liên quan đến đăng nhập người dùng.
 */
public interface IT001Dao {
	List<Integer> checkLogin(String user, String pass);
	List<mstuser> getUserInfo(String userId, String passWord);
}
