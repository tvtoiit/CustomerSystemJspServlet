package fjs.cs.common;

public class Constants {
	/* List Man Hinh */
	//T001Login
	public static final String T001_LOGIN = "/WEB-INF/jsp/T001.jsp";
	// T002(Search)
	public static final String T002_SEARCH = "/WEB-INF/jsp/T002.jsp";
	// T002(Edit)
	public static final String T003_EDIT = "/WEB-INF/jsp/T003.jsp";
	
	//Duong dan vào trang T002 home
	public static final String T002_HOME = "/CustomerJspServlet/T002";
	// message login fail
	public static final String MESSAGE_ERROR_USER_NOT_EXIST = "ユーザーIDまたはパスワードが不正です。";
	
	//message did not input username
	public static final String MESSAGE_ERROR_NO_INPUT_USERNAME = "Bạn chưa nhập giá trị username";
	
	//message did not input password
	public static final String MESSAGE_ERROR_NO_INPUT_PASSWORD = "Bạn chưa nhập giá trị password";

	public static final String MESSAGE_CHECKOFF = "行を選択してください。";
	
	public static final String MESSAGE_TO_SMALL_FROM = "There is an error in the range input of Birthday";
	
	public static final String MESSAGE_ERROR_FROM = "Invalid Birthday (From).";
	public static final String MESSAGE_ERROR_TO = "Invalid Birthday (To).";
}	
