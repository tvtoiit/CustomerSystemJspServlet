package fjs.cs.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import fjs.cs.dto.T001Dto;

public class T001Mapper implements RowMapper<T001Dto> {

	@Override
	public T001Dto mapRow(ResultSet resultSet) {
		try {
			T001Dto t001 = new T001Dto();
			t001.setPsnCd(resultSet.getBigDecimal("PSN_CD"));
			t001.setUserId(resultSet.getString("USERID"));
			t001.setPassWord(resultSet.getString("PASSWORD"));
			t001.setUserName(resultSet.getString("USERNAME"));
			t001.setDeleteYmd(resultSet.getTimestamp("DELETE_YMD"));
			t001.setInsertYmd(resultSet.getTimestamp("INSERT_YMD"));
			t001.setInsertPsnCd(resultSet.getInt("INSERT_PSN_CD"));
			t001.setUpdateYmd(resultSet.getTimestamp("UPDATE_YMD"));
			t001.setInsertPsnCd(resultSet.getInt("UPDATE_PSN_CD"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
