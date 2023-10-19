package fjs.cs.dao.impl;

import java.util.ArrayList;
import java.util.List;

import fjs.cs.dao.IT002Dao;
import fjs.cs.dto.MstCustomer;
import fjs.cs.rowmapper.T002Mapper;

/**
 * Lớp T002Dao là một DAO (Data Access Object) dùng để thực hiện các thao tác liên quan đến dữ liệu mstcustomer.
 * Lớp này dùng để mở rộng giao diện IT002Dao
 */
public class T002Dao extends AbstractDao<MstCustomer> implements IT002Dao {

	/**
	 * Lấy danh sách dữ liệu từ bảng mstcustomer.
	 * 
	 * @return Danh sách dữ liệu mstcustomer.
	 */
	@Override
	public List<MstCustomer> getData() {
		String sql = "SELECT CUSTOMER_ID, CUSTOMER_NAME, CASE WHEN SEX = 0 THEN 'Male' ELSE 'Female' END AS SEX, BIRTHDAY, ADDRESS FROM mstcustomer WHERE DELETE_YMD IS NULL ORDER BY CUSTOMER_ID";
		return query(sql, new T002Mapper());
	}

	/**
	 * Thực hiện truy vấn dữ liệu khách hàng với các điều kiện tìm kiếm tùy chọn.
	 *
	 * @param name         Tên khách hàng hoặc một phần của tên để tìm kiếm (tìm kiếm gần đúng).
	 * @param sex          Giới tính của khách hàng (0 cho Nam, 1 cho Nữ).
	 * @param birthdayFrom Ngày sinh bắt đầu trong khoảng tìm kiếm.
	 * @param birthdayTo   Ngày sinh kết thúc trong khoảng tìm kiếm.
	 * @return Danh sách khách hàng thỏa mãn các điều kiện tìm kiếm, được sắp xếp theo CUSTOMER_ID.
	 */
	@Override
	public List<MstCustomer> getDataSearch(String name, String sex, String birthdayFrom, String birthdayTo) {
		// Xây dựng truy vấn SQL
		StringBuilder query = new StringBuilder("SELECT CUSTOMER_ID, CUSTOMER_NAME, CASE WHEN SEX = 0 THEN 'Male' else 'Female' end as SEX, BIRTHDAY, ADDRESS ")
				.append("FROM MSTCUSTOMER ")
				.append("WHERE DELETE_YMD IS NULL");
		
		// Danh sách tham số sẽ được sử dụng trong truy vấn SQL
	    List<Object> parameters = new ArrayList<>();
	    
	    // Thêm điều kiện tìm kiếm theo tên nếu tên có
	    if (name != null && !name.isEmpty()) {
	        query.append(" AND CUSTOMER_NAME LIKE ?");
	        parameters.add("%" + name + "%");
	    }
	    
	    // Thêm điều kiện tìm kiếm theo giới tính nếu giới tính nếu có
	    if (sex != null && !sex.isEmpty()) {
	        query.append(" AND SEX = ?");
	        parameters.add(sex);
	    }
	    
	    // Thêm điều kiện tìm kiếm theo ngày sinh bắt đầu nếu có
	    if (birthdayFrom != null && !birthdayFrom.isEmpty()) {
	        query.append(" AND BIRTHDAY >= ?");
	        parameters.add(birthdayFrom);
	    }
	    
	    // Thêm điều kiện tìm kiếm theo ngày sinh kết thúc nếu có
	    if (birthdayTo != null && !birthdayTo.isEmpty()) {
	        query.append(" AND BIRTHDAY <= ?");
	        parameters.add(birthdayTo);
	    }
		
	    // Sắp xếp kết quả theo CUSTOMER_ID
	    query.append(" ORDER BY CUSTOMER_ID");
	    
	    // Thực hiện truy vấn SQL và trả về danh sách khách hàng
		return query(query.toString(), new T002Mapper(), parameters.toArray());
	}

	@Override
	public List<MstCustomer> deleteData(String[] selecValue) {
	    List<MstCustomer> listDelete = new ArrayList<MstCustomer>();
	    try {
	        String query = "UPDATE MSTCUSTOMER "
	                     + "SET Delete_YMD = CURRENT_TIMESTAMP "
	                     + "WHERE customer_Id IN (";
	        for (int i = 0; i < selecValue.length; i++) {
	            String[] ids = selecValue[i].split(",");
	            for (int j = 0; j < ids.length; j++) {
	                query += "?,";
	            }
	        }
	        query = query.substring(0, query.length() - 1) + ")";
	        
	        // Gọi hàm chung update để thực hiện truy vấn cập nhật
	        update(query, (Object[]) selecValue);
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return listDelete;
	}

}
