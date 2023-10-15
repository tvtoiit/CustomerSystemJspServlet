package fjs.cs.dao.impl;

import java.math.BigDecimal;
import java.util.List;

import fjs.cs.dao.IT003Dao;
import fjs.cs.dto.mstcustomer;
import fjs.cs.rowmapper.T003Mapper;

public class T003Dao extends AbstractDao<mstcustomer> implements IT003Dao {
	
	/**
	 * Lưu thông tin khách hàng.
	 * 
	 * @param 	ms            Đối tượng mstcustomer chứa thông tin khách hàng cần lưu.
	 * @param 	loggedInPsnCd Mã người dùng đang đăng nhập thực hiện lưu thông tin.
	 * @return 	Số lượng bản ghi được thêm vào.
	 */
	@Override
	public int save(mstcustomer ms, BigDecimal loggedInPsnCd) {
		StringBuilder sql = new StringBuilder("INSERT INTO MSTCUSTOMER (CUSTOMER_NAME, SEX, BIRTHDAY, EMAIL, ADDRESS, DELETE_YMD, INSERT_YMD, INSERT_PSN_CD, UPDATE_YMD, UPDATE_PSN_CD)");
	    sql.append(" VALUES (?, ?, ?, ?, ?, NULL, CURRENT_TIMESTAMP, ?, CURRENT_TIMESTAMP, ?)");
	    return insert(sql.toString(), ms.getCustomerName(), ms.getSex(), ms.getBirthDay(), ms.getEmail(), ms.getAddress(), loggedInPsnCd, loggedInPsnCd);
	}
	
	/**
	 * Truy vấn thông tin khách hàng dựa trên ID.
	 * 
	 * @param 	id  ID của khách hàng cần truy vấn.
	 * @return 	Danh sách các đối tượng mstcustomer chứa thông tin khách hàng tìm thấy.
	 */
	@Override
	public mstcustomer getCustomerById(Integer id) {
	    String sql = "SELECT CUSTOMER_ID, CUSTOMER_NAME, SEX, BIRTHDAY, EMAIL, ADDRESS FROM mstcustomer WHERE CUSTOMER_ID = ?";
	    List<mstcustomer> customers = query(sql, new T003Mapper(), id);
	    if (customers != null && !customers.isEmpty()) {
	        return customers.get(0); // Trả về khách hàng đầu tiên nếu có
	    } else {
	        return null;
	    }
	}


	/**
	 * Cập nhật thông tin khách hàng.
	 * 
	 * @param 	ms Đối tượng mstcustomer chứa thông tin khách hàng cần cập nhật.
	 */
	@Override
	public void update(mstcustomer ms) {
		String sql = "UPDATE MSTCUSTOMER SET CUSTOMER_NAME = ?, SEX = ?, BIRTHDAY = ?, EMAIL = ?, ADDRESS = ?, DELETE_YMD = NULL, UPDATE_YMD = CURRENT_TIMESTAMP WHERE CUSTOMER_ID = ?";
		update(sql, ms.getCustomerName(), ms.getSex(), ms.getBirthDay(), ms.getEmail(), ms.getAddress(), ms.getCustomerId());
	}
}
