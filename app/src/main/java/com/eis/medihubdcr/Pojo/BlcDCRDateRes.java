package com.eis.medihubdcr.Pojo;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class BlcDCRDateRes{

	@SerializedName("error")
	private boolean error;

	@SerializedName("blcdcrdates")
	private List<BlcdcrdatesItem> blcdcrdates;

	public void setError(boolean error){
		this.error = error;
	}

	public boolean isError(){
		return error;
	}

	public void setBlcdcrdates(List<BlcdcrdatesItem> blcdcrdates){
		this.blcdcrdates = blcdcrdates;
	}

	public List<BlcdcrdatesItem> getBlcdcrdates(){
		return blcdcrdates;
	}

	@Override
 	public String toString(){
		return 
			"BlcDCRDateRes{" + 
			"error = '" + error + '\'' + 
			",blcdcrdates = '" + blcdcrdates + '\'' + 
			"}";
		}
}