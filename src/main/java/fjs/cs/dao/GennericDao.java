package fjs.cs.dao;

import java.util.List;

import fjs.cs.rowmapper.RowMapper;

/**
 * Giao diện GennericDao định nghĩa các phương thức chung cho việc truy vấn và tương tác với cơ sở dữ liệu.
 *
 * @param <T> Kiểu dữ liệu của đối tượng trong cơ sở dữ liệu.
 */
public interface GennericDao<T> {
	/**
     * Thực hiện một truy vấn SQL và trả về danh sách kết quả ánh xạ vào đối tượng kiểu T sử dụng RowMapper.
     *
     * @param sql         Câu truy vấn SQL cần thực thi.
     * @param rowMapper   RowMapper được sử dụng để ánh xạ từ ResultSet thành đối tượng kiểu T.
     * @param parameters  Các tham số tùy chọn để đặt trong PreparedStatement.
     * @return Danh sách các đối tượng kiểu U chứa kết quả của truy vấn.
     */
	<T> List<T> query(String sql, RowMapper<T> rowMapper, Object... parammeters);
	
	/**
     * Thực hiện một truy vấn cập nhật (INSERT, UPDATE, DELETE) vào cơ sở dữ liệu.
     *
     * @param sql         Câu truy vấn SQL cần thực thi.
     * @param parameters  Các tham số tùy chọn để đặt trong PreparedStatement.
     */
	void update(String sql, Object... parammeters);
	
	/**
     * Thực hiện một truy vấn thêm dữ liệu (INSERT) vào cơ sở dữ liệu và trả về khóa chính được tạo ra (nếu có).
     *
     * @param sql         Câu truy vấn SQL cần thực thi để thêm dữ liệu.
     * @param parameters  Các tham số tùy chọn để đặt trong PreparedStatement.
     * @return Khóa chính được tạo ra sau khi thêm dữ liệu hoặc -1 nếu có lỗi hoặc không có khóa chính được tạo ra.
     */
	int insert(String sql, Object... parammeters);
}
