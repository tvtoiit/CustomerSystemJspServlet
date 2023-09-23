package fjs.cs.rowmapper;

import java.sql.ResultSet;

import fjs.cs.dto.mstcustomer;

public class T003Mapper implements RowMapper<mstcustomer> {

	@Override
	public mstcustomer mapRow(ResultSet resultSet) {
		try {
			mstcustomer t003Dto = new mstcustomer();
			t003Dto.setCustomerId(resultSet.getBigDecimal("CUSTOMER_ID"));
			t003Dto.setCustomerName(resultSet.getString("CUSTOMER_NAME"));
			t003Dto.setSex(resultSet.getString("SEX"));
			t003Dto.setBirthDay(resultSet.getString("BIRTHDAY"));
			t003Dto.setEmail(resultSet.getString("EMAIL"));
			t003Dto.setAddress(resultSet.getString("ADDRESS"));
//			t003Dto.setDeleteYmd(resultSet.getTimestamp("DELETE_YMD"));
//			t003Dto.setInsertYmd(resultSet.getTimestamp("INSERT_YMD"));
//			t003Dto.setCustomerId(resultSet.getBigDecimal("CURRENT_TIMESTAMP"));
//			t003Dto.setInsertPsnCd(resultSet.getInt("INSERT_PSN_CD"));
//			t003Dto.setUpdateYmd(resultSet.getTimestamp("UPDATE_YMD"));
//			t003Dto.setUpdatePsnCd(resultSet.getInt("UPDATE_PSN_CD"));
			return t003Dto;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
