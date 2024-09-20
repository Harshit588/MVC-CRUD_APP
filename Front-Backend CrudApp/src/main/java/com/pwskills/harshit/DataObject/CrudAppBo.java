package com.pwskills.harshit.DataObject;

public class CrudAppBo {

	private Integer sid;
	private String sname;
	private Integer sage;
	private String saddress;
	private String stutas;

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public Integer getSage() {
		return sage;
	}

	public void setSage(Integer sage) {
		this.sage = sage;
	}

	public String getSaddress() {
		return saddress;
	}

	public void setSaddress(String saddress) {
		this.saddress = saddress;
	}

	public String getStutas() {
		return stutas;
	}

	public void setStutas(String stutas) {
		this.stutas = stutas;
	}

	@Override
	public String toString() {
		return "CrudAppBo [sid=" + sid + ", sname=" + sname + ", sage=" + sage + ", saddress=" + saddress + ", stutas="
				+ stutas + "]";
	}

}
