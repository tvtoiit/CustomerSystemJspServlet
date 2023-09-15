package fjs.cs.dto;

import java.sql.Timestamp;
import java.util.List;

public class AbstractDto<T> {
	private Timestamp deleteYmd;
	private Timestamp insertYmd;
	private int insertPsnCd;
	private Timestamp updateYmd;
	private List<T> pageData;
	private int updatePsnCd;
	private Integer page;

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public List<T> getPageData() {
		return pageData;
	}

	public void setPageData(List<T> pageData) {
		this.pageData = pageData;
	}

	public Timestamp getDeleteYmd() {
		return deleteYmd;
	}

	public void setDeleteYmd(Timestamp deleteYmd) {
		this.deleteYmd = deleteYmd;
	}

	public Timestamp getInsertYmd() {
		return insertYmd;
	}

	public void setInsertYmd(Timestamp insertYmd) {
		this.insertYmd = insertYmd;
	}

	public int getInsertPsnCd() {
		return insertPsnCd;
	}

	public void setInsertPsnCd(int insertPsnCd) {
		this.insertPsnCd = insertPsnCd;
	}

	public Timestamp getUpdateYmd() {
		return updateYmd;
	}

	public void setUpdateYmd(Timestamp updateYmd) {
		this.updateYmd = updateYmd;
	}

	public int getUpdatePsnCd() {
		return updatePsnCd;
	}

	public void setUpdatePsnCd(int updatePsnCd) {
		this.updatePsnCd = updatePsnCd;
	}
}
