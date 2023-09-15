package fjs.cs.sevices;

import fjs.cs.dao.T003Dao;
import fjs.cs.dao.impl.IT003Dao;
import fjs.cs.sevices.impl.IT003Sevice;

public class T003Sevice implements IT003Sevice {

	private IT003Dao t003Dao;
	
	public T003Sevice () {
		t003Dao = new T003Dao();
	}
	@Override
	public int save(int id) {
		return t003Dao.save(id);
	}

}
