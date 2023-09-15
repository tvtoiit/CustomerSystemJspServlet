package fjs.cs.dao.impl;

import java.util.List;

/**
 * Interface IT001Dao định nghĩa các phương thức liên quan đến đăng nhập người dùng.
 */
public interface IT001Dao {
	List<Integer> checkLogin(String user, String pass);
}
