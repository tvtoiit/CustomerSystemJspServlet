package fjs.cs.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

	public class T001Mapper implements RowMapper<Integer> {
		@Override
		public Integer mapRow(ResultSet resultSet) {
			try {
	            int count = resultSet.getInt("CNT");
	            return count;
	        } catch (SQLException e) {
	            return 0;
	        }
		}
	}
