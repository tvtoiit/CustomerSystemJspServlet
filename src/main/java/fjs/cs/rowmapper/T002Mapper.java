package fjs.cs.rowmapper;

import java.sql.ResultSet;

import fjs.cs.dto.MstCustomer;

public class T002Mapper implements RowMapper<MstCustomer> {

	@Override
	public MstCustomer mapRow(ResultSet resultSet) {
		try {
			MstCustomer t002Dto = new MstCustomer();
			t002Dto.setCustomerId(resultSet.getBigDecimal("CUSTOMER_ID"));
			t002Dto.setCustomerName(resultSet.getString("CUSTOMER_NAME"));
			t002Dto.setSex(resultSet.getString("SEX"));
			t002Dto.setBirthDay(resultSet.getString("BIRTHDAY"));
			//t002Dto.setEmail(resultSet.getString("EMAIL"));
			t002Dto.setAddress(resultSet.getString("ADDRESS"));
//			t002Dto.setDeleteYmd(resultSet.getTimestamp("DELETE_YMD"));
//			t002Dto.setInsertYmd(resultSet.getTimestamp("INSERT_YMD"));
//			t002Dto.setCustomerId(resultSet.getBigDecimal("CURRENT_TIMESTAMP"));
//			t002Dto.setInsertPsnCd(resultSet.getInt("INSERT_PSN_CD"));
//			t002Dto.setUpdateYmd(resultSet.getTimestamp("UPDATE_YMD"));
//			t002Dto.setUpdatePsnCd(resultSet.getInt("UPDATE_PSN_CD"));
			return t002Dto;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
