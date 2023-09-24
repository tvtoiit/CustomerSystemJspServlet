package fjs.cs.dao.impl;

import java.util.List;

import fjs.cs.dto.mstcustomer;

/**
 * Interface IT002Dao định nghĩa các phương thức để thực hiện màn hình tìm kiếm dữ liệu T002.
 */
public interface IT002Dao {
	List<mstcustomer> getData();
	List<mstcustomer> getDataSearch(String name, String sex, String birthdayFrom, String birthdayTo);
	List<mstcustomer> deleteData(String[] selecValue);
}
