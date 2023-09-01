package fjs.cs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fjs.cs.common.AbstractCommon;
import fjs.cs.dto.T001Dto;

public class T001Dao extends AbstractCommon {
	public T001Dto checkLogin(String user, String pass) {
		try (Connection connection = getConnection();
			PreparedStatement stament = connection.prepareStatement(
			"SELECT COUNT(*) AS CNT FROM MSTUSER WHERE DELETE_YMD IS NULL AND USERID = ? AND PASSWORD = ?")){
			stament.setString(1, user);
			stament.setString(2, pass);
			
			try (ResultSet resultSet = stament.executeQuery()){
				if (resultSet.next()) {
					int count = resultSet.getInt("CNT");
					if (count == 1) {
						return new T001Dto(null, user, pass, null);
					}
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
