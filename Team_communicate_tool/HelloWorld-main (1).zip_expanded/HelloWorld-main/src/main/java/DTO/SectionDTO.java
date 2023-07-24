package DTO;

public class SectionDTO {
	private int project_id;
	private int section_id;
	private String section_name;
	private String write_Id;
	private String section_keep; //0이면 false 1이면 true
	
	public int getProject_id() {
		return project_id;
	}
	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}
	public int getSection_id() {
		return section_id;
	}
	public void setSection_id(int section_id) {
		this.section_id = section_id;
	}
	public String getSection_name() {
		return section_name;
	}
	public void setSection_name(String section_name) {
		this.section_name = section_name;
	}
	public String getWrite_Id() {
		return write_Id;
	}
	public void setWrite_Id(String write_Id) {
		this.write_Id = write_Id;
	}
	public String getSection_keep() {
		return section_keep;
	}
	public void setSection_keep(String section_keep) {
		this.section_keep = section_keep;
	}

}
