package fjs.cs.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import fjs.cs.common.AbstractCommon;
import fjs.cs.dao.impl.GennericDao;
import fjs.cs.rowmapper.RowMapper;

public class AbstractDao<T> extends AbstractCommon implements GennericDao<T> {
	/**
	 * Thực thi một truy vấn SQL với các tham số tùy chọn và ánh xạ kết quả thành
	 * danh sách các đối tượng sử dụng một RowMapper.
	 * 
	 * @param sql         Câu truy vấn SQL cần thực thi.
	 * @param rowMapper   RowMapper được sử dụng để ánh xạ mỗi hàng trong ResultSet
	 *                    thành một đối tượng kiểu T.
	 * @param parammeters Các tham số tùy chọn để đặt trong PreparedStatement.
	 * @param <T>         Kiểu đối tượng cần trả về.
	 * @return Danh sách các đối tượng kiểu T chứa kết quả của truy vấn hoặc null
	 *         trong trường hợp có lỗi.
	 */
	@Override
	public <T> List<T> query(String sql, RowMapper<T> rowMapper, Object... parammeters) {
		List<T> result = new ArrayList<>();
		try (Connection connection = getConnection(); 
			PreparedStatement stament = connection.prepareStatement(sql)) {
			// Đặt các tham số tùy chọn trong PreparedStatement
			setParameter(stament, parammeters);

			try (ResultSet resultSet = stament.executeQuery()) {
				// Duyệt qua ResultSet và ánh xạ mỗi hàng thành một đối tượng kiểu T
				while (resultSet.next()) {
					result.add(rowMapper.mapRow(resultSet));
				}
				return result;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Thực hiện môt truy vấn thêm (INSERT) vào trong cơ sở dữ liệu
	 * 
	 * @param sql			Câu truy vấn SQL cần thực thi.
	 * @param parammeters	Các tham số tùy chọn để đặt trong PreparedStatement.
	 * @return Khóa chính được tạo ra sau khi thêm dữ liệu hoặc -1 nếu có lỗi 
	 * 		   hoặc không có khóa chính được tạo ra.
	 */
	@Override
	public int insert(String sql, Object... parammeters) {
		// Mở kết nối đến cơ sở dữ liệu
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			// Tắt chế độ tự động commit để quản lý giao dịch thủ công
			connection.setAutoCommit(false);
			
			// Đặt các tham số vào PreparedStatement
			setParameter(statement, parammeters);
			
			 // Thực hiện truy vấn INSERT và lấy số hàng bị ảnh hưởng
			int rowsAffected = statement.executeUpdate();

			if (rowsAffected > 0) {
				 // Lấy khóa chính được tạo ra (nếu có)
				ResultSet resultSet = statement.getGeneratedKeys();
				if (resultSet.next()) {
					int id = resultSet.getInt(1);
					 // Commit giao dịch và trả về khóa chính
					connection.commit();
					return id;
				}
			}
			// Nếu không có khóa chính được tạo ra, thực hiện rollback
			connection.rollback();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		 // Trả về -1 trong trường hợp có lỗi hoặc không có khóa chính được tạo ra
		return -1;
	}

	/**
	 * Thực hiện 1 truy vấn cập nhật (UPDATE, DELETE) vào trong cơ sở dữ liệu.
	 * 
	 * @param sql 			Câu truy vấn SQL cần thực thi.
	 * @param parammeters	Các tham số tùy chọn để đặt trong PreparedStatement.
	 */
	@Override
	public void update(String sql, Object... parammeters) {
		try {
			// Mở kết nối đến cơ sở dữ liệu
			try (Connection connection = getConnection()) {
				try (PreparedStatement statement = connection.prepareStatement(sql)) {
					// Tắt chế độ tự động commit để quản lý giao dịch thủ công
					connection.setAutoCommit(false);
					
					// Đặt các tham số vào PreparedStatement
					setParameter(statement, parammeters);
					
					// Thực hiện truy vấn cập nhật và commit giao dịch
					statement.executeUpdate();
					connection.commit();
				} catch (SQLException e) {
					e.printStackTrace();
					
					// Nếu có lỗi, thực hiện rollback giao dịch
					if (connection != null) {
						connection.rollback();
					}
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	
	
	/**
	 * Đặt các tham số vào PreparedStatement dựa trên kiểu dữ liệu của từng tham số.
	 * 
	 * @param stament		PreparedStatement để đặt tham số.
	 * @param parammeters	Các tham số cần đặt vào PreparedStatement.
	 */
	private void setParameter(PreparedStatement stament, Object... parammeters) {
		try {
			for (int i = 0; i < parammeters.length; i++) {
				Object parammeter = parammeters[i];
				int index = i + 1;
				
				// Kiểm tra kiểu dữ liệu của tham số và đặt vào PreparedStatement tương ứng
				if (parammeter instanceof String) {
					stament.setString(index, (String) parammeter);
				}
				if (parammeter instanceof Integer) {
					stament.setInt(index, (Integer)parammeter);
				}
				if (parammeter instanceof Timestamp) {
					stament.setTimestamp(index, (Timestamp) parammeter);
				}
				if (parammeter instanceof BigDecimal) {
					stament.setBigDecimal(index, (BigDecimal)parammeter);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} 
}
