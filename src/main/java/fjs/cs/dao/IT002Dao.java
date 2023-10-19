package fjs.cs.dao;

import java.util.List;

import fjs.cs.dto.MstCustomer;

/**
 * Interface IT002Dao định nghĩa các phương thức để thực hiện màn hình tìm kiếm dữ liệu T002.
 */
public interface IT002Dao {
	List<MstCustomer> getData();
	List<MstCustomer> getDataSearch(String name, String sex, String birthdayFrom, String birthdayTo);
	List<MstCustomer> deleteData(String[] selecValue);
}
