package fjs.cs.sevices;

import java.util.List;

import fjs.cs.dao.T001Dao;
import fjs.cs.dao.impl.IT001Dao;
import fjs.cs.sevices.impl.IT001Sevice;

public class T001Sevice implements IT001Sevice {
	private IT001Dao t001Dao;
	
	public T001Sevice() {
		t001Dao = new T001Dao();
	}
	
	@Override
	public List<Integer> checkLogin(String user, String pass) {
		return t001Dao.checkLogin(user, pass);
	}

}
