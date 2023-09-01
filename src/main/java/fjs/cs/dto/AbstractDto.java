package fjs.cs.dto;

import java.sql.Timestamp;

public class AbstractDto {
	private Timestamp deleteYmd;
	private Timestamp insertYmd;
	private int insertPsnCd;
	private Timestamp updateYmd;
	private int updatePsnCd;
	
	public AbstractDto() {
		
	}
	
	public AbstractDto(Timestamp deleteYmd, Timestamp insertYmd, int insertPsnCd ,Timestamp updateYmd, int updatePsnCd) {
		this.deleteYmd = deleteYmd;
		this.insertYmd = insertYmd;
		this.insertPsnCd = insertPsnCd;
		this.updateYmd = updateYmd;
		this.updatePsnCd = updatePsnCd;
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
