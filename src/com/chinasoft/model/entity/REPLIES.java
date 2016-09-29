package com.chinasoft.model.entity;

public class REPLIES {

	private long R_no;
	private int R_floor;
	private boolean R_statedel;
	private int R_pfloor;
	private String R_content;
	private int N_id;
	private int M_id;
	private String R_reptime;
	
	

	public String getR_reptime() {
		return R_reptime;
	}
	public void setR_reptime(String r_reptime) {
		R_reptime = r_reptime;
	}

	public String getR_content() {
		return R_content;
	}
	public void setR_content(String r_content) {
		R_content = r_content;
	}
	public int getN_id() {
		return N_id;
	}
	public void setN_id(int n_id) {
		N_id = n_id;
	}
	public int getM_id() {
		return M_id;
	}
	public void setM_id(int m_id) {
		M_id = m_id;
	}
	public long getR_no() {
		return R_no;
	}
	public void setR_no(long r_no) {
		R_no = r_no;
	}
	public int getR_floor() {
		return R_floor;
	}
	public void setR_floor(int r_floor) {
		R_floor = r_floor;
	}
	public boolean getR_statedel() {
		return R_statedel;
	}
	public void setR_statedel(boolean r_statedel) {
		R_statedel = r_statedel;
	}
	public int getR_pfloor() {
		return R_pfloor;
	}
	public void setR_pfloor(int r_pfloor) {
		R_pfloor = r_pfloor;
	}
	
	
}
