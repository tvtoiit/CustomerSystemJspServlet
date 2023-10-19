package fjs.cs.dao;

import java.math.BigDecimal;
import java.util.List;

import fjs.cs.dto.MstCustomer;

/**
 * Interface IT003Dao định nghĩa các phương thức để thực hiện màn hình thêm và cập nhật
 */
public interface IT003Dao {
	int save(MstCustomer ms, BigDecimal loggedInPsnCd );
	MstCustomer getCustomerById(Integer id);
	void update(MstCustomer ms);
}
