package DTO;

import java.sql.Date;

public class DdateDTO {
	int pro_id;
	String pro_name;
	String pro_host;
	String mem_id;
	String pro_end;
	Date pro_s_date;
	String pro_e_date;
	public int getPro_id() {
		return pro_id;
	}
	public void setPro_id(int pro_id) {
		this.pro_id = pro_id;
	}
	public String getPro_name() {
		return pro_name;
	}
	public void setPro_name(String pro_name) {
		this.pro_name = pro_name;
	}
	public String getPro_host() {
		return pro_host;
	}
	public void setPro_host(String pro_host) {
		this.pro_host = pro_host;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getPro_end() {
		return pro_end;
	}
	public void setPro_end(String pro_end) {
		this.pro_end = pro_end;
	}
	public Date getPro_s_date() {
		return pro_s_date;
	}
	public void setPro_s_date(Date pro_s_date) {
		this.pro_s_date = pro_s_date;
	}
	public String getPro_e_date() {
		return pro_e_date;
	}
	public void setPro_e_date(String pro_e_date) {
		this.pro_e_date = pro_e_date;
	}
	
	
	
	
}