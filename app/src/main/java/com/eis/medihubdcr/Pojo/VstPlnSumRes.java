package com.eis.medihubdcr.Pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VstPlnSumRes{

	@SerializedName("error")
	private boolean error;

	@SerializedName("VSTPSUM")
	private List<VSTPSUMItem> vSTPSUM;

	@SerializedName("errormsg")
	private String errormsg;

	public void setError(boolean error){
		this.error = error;
	}

	public boolean isError(){
		return error;
	}

	public void setVSTPSUM(List<VSTPSUMItem> vSTPSUM){
		this.vSTPSUM = vSTPSUM;
	}

	public List<VSTPSUMItem> getVSTPSUM(){
		return vSTPSUM;
	}

	public void setErrormsg(String errormsg){
		this.errormsg = errormsg;
	}

	public String getErrormsg(){
		return errormsg;
	}

	@Override
 	public String toString(){
		return 
			"VstPlnSumRes{" + 
			"error = '" + error + '\'' + 
			",vSTPSUM = '" + vSTPSUM + '\'' + 
			",errormsg = '" + errormsg + '\'' + 
			"}";
		}
}