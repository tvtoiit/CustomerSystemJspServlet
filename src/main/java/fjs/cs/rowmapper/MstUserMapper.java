package fjs.cs.rowmapper;

import java.math.BigDecimal;
import java.sql.ResultSet;

import fjs.cs.dto.mstuser;

public class MstUserMapper implements RowMapper<mstuser> {

	@Override
	public mstuser mapRow(ResultSet resultSet) {
		try {
            BigDecimal psnCd = resultSet.getBigDecimal("PSN_CD");
            String userId = resultSet.getString("USERID");
            String passWord = resultSet.getString("PASSWORD");
            String userName = resultSet.getString("USERNAME");

            return new mstuser(psnCd, userId, passWord, userName);
        } catch (Exception e) {
            return null;
        }
	}

}
