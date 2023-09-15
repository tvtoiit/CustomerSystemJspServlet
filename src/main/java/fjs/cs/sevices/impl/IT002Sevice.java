package fjs.cs.sevices.impl;

import java.util.List;

import fjs.cs.dto.mstcustomer;

public interface IT002Sevice {
	List<mstcustomer> getData();
	List<mstcustomer> getDataSearch(String name, String sex, String birthdayFrom, String birthdayTo);
	List<mstcustomer> pagingData(int index);
}
