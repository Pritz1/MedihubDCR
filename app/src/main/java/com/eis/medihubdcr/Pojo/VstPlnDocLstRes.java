package com.eis.medihubdcr.Pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VstPlnDocLstRes{

	@SerializedName("VSTPSUMDOC")
	private List<VSTPSUMDOCItem> vSTPSUMDOC;

	@SerializedName("error")
	private boolean error;

	@SerializedName("errormsg")
	private String errormsg;

	public void setVSTPSUMDOC(List<VSTPSUMDOCItem> vSTPSUMDOC){
		this.vSTPSUMDOC = vSTPSUMDOC;
	}

	public List<VSTPSUMDOCItem> getVSTPSUMDOC(){
		return vSTPSUMDOC;
	}

	public void setError(boolean error){
		this.error = error;
	}

	public boolean isError(){
		return error;
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
			"VstPlnDocLstRes{" + 
			"vSTPSUMDOC = '" + vSTPSUMDOC + '\'' + 
			",error = '" + error + '\'' + 
			",errormsg = '" + errormsg + '\'' + 
			"}";
		}
}