package com.eis.medihubdcr.Pojo;

import com.google.gson.annotations.SerializedName;

public class BlcdcrdatesItem{

	@SerializedName("dcrdate")
	private String dcrdate;

	@SerializedName("dcrno")
	private String dcrno;

	@SerializedName("dremark")
	private String dremark;

	public void setDcrdate(String dcrdate){
		this.dcrdate = dcrdate;
	}

	public String getDcrdate(){
		return dcrdate;
	}

	public void setDcrno(String dcrno){
		this.dcrno = dcrno;
	}

	public String getDcrno(){
		return dcrno;
	}

	public void setDremark(String dremark){
		this.dremark = dremark;
	}

	public String getDremark(){
		return dremark;
	}

	@Override
 	public String toString(){
		return 
			"BlcdcrdatesItem{" + 
			"dcrdate = '" + dcrdate + '\'' + 
			",dcrno = '" + dcrno + '\'' + 
			",dremark = '" + dremark + '\'' + 
			"}";
		}
}