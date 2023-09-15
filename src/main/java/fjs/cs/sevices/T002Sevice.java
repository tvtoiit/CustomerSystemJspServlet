package fjs.cs.sevices;

import java.util.List;

import fjs.cs.dao.T002Dao;
import fjs.cs.dao.impl.IT002Dao;
import fjs.cs.dto.mstcustomer;
import fjs.cs.sevices.impl.IT002Sevice;

public class T002Sevice implements IT002Sevice {
	private IT002Dao t002Dao;
	
	public T002Sevice() {
		t002Dao = new T002Dao(); 
	}
	
	@Override
	public List<mstcustomer> getData() {
		return t002Dao.getData();
	}

	@Override
	public List<mstcustomer> getDataSearch(String name, String sex, String birthdayFrom, String birthdayTo) {
		return t002Dao.getDataSearch(name, sex, birthdayFrom, birthdayTo);
	}

	@Override
	public List<mstcustomer> pagingData(int index) {
		return t002Dao.pagingData(index);
	}
}
