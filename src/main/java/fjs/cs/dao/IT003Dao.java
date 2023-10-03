package fjs.cs.dao;

import java.math.BigDecimal;
import java.util.List;

import fjs.cs.dto.mstcustomer;

/**
 * Interface IT003Dao định nghĩa các phương thức để thực hiện màn hình thêm và cập nhật
 */
public interface IT003Dao {
	int save(mstcustomer ms, BigDecimal loggedInPsnCd );
	List<mstcustomer> getCustomerById(Integer id);
	void update(mstcustomer ms);
}
