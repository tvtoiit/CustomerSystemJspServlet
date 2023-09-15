package fjs.cs.dao;

import fjs.cs.dao.impl.IT003Dao;
import fjs.cs.dto.mstcustomer;
import fjs.cs.rowmapper.T002Mapper;

public class T003Dao extends AbstractDao<mstcustomer> implements IT003Dao {

	@Override
	public int save(int id) {
		StringBuilder sql = new StringBuilder("INSERT INTO MSTCUSTOMER (CUSTOMER_ID, CUSTOMER_NAME, SEX, BIRTHDAY, EMAIL, ADDRESS, DELETE_YMD, INSERT_YMD, INSERT_PSN_CD, UPDATE_YMD, UPDATE_PSN_CD)");
		sql.append(" VALUES (NEXT VALUE FOR SEQ_CUSTOMER_ID, ?, ?, ?, ?, ?, NULL, CURRENT_TIMESTAMP, ?, CURRENT_TIMESTAMP, ?)");
		return insert(sql.toString(), new T002Mapper(), id);
	}
	
}
