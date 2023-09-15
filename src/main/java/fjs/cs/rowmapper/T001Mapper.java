package fjs.cs.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class T001Mapper implements RowMapper<Integer> {

	@Override
	public Integer mapRow(ResultSet resultSet) {
		try {
			return resultSet.getInt("CNT");
		} catch (SQLException e) {
			return null;
		}
	}
}
