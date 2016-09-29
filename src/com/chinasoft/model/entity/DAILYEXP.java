package com.chinasoft.model.entity;

public class DAILYEXP {
	
	private int M_id;
	private String D_lsdate;
	private short D_dsexp;
	private String D_ledate;
	
	public int getM_id() {
		return M_id;
	}
	public String getD_ledate() {
		return D_ledate;
	}
	public void setD_ledate(String d_ledate) {
		D_ledate = d_ledate;
	}
	public void setM_id(int m_id) {
		M_id = m_id;
	}
	public String getD_lsdate() {
		return D_lsdate;
	}
	public void setD_lsdate(String d_lsdate) {
		D_lsdate = d_lsdate;
	}
	public short getD_dsexp() {
		return D_dsexp;
	}
	public void setD_dsexp(short d_dsexp) {
		D_dsexp = d_dsexp;
	}
	
	
}
