package fjs.cs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import fjs.cs.common.AbstractCommon;
import fjs.cs.rowmapper.RowMapper;

public class AbstractDao extends AbstractCommon {
	public <T> List<T> query(String sql, RowMapper<T> rowMapper, Object... parammeters) {
		List<T> result = new ArrayList<>();
		try (Connection connection = getConnection();
				PreparedStatement stament = connection.prepareStatement(sql);
				ResultSet resultSet = stament.executeQuery();
				) {
			setParameter(stament, parammeters);
			while(resultSet.next()) {
				result.add(rowMapper.mapRow(resultSet));
			}
			return result;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private void setParameter(PreparedStatement stament, Object... parammeters) {
		try {
			for (int i = 0; i < parammeters.length; i++) {
				Object parammeter = parammeters[i];
				int index = i + 1;
				if (parammeter instanceof String) {
					stament.setString(index, (String)parammeter);
				}
				if (parammeter instanceof Timestamp) {
					stament.setTimestamp(index, (Timestamp)parammeter);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
