package com.hicc.cloud.teacher.db;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

public class UpdateFile extends BmobObject {
	
	private int version;
	private BmobFile apkFile;
	private String description;
	
	public UpdateFile(){
		
	}
	
	public UpdateFile(int version, BmobFile file, String description){
		this.version =version;
		this.apkFile = file;
		this.description = description;
	}
	
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public BmobFile getFile() {
		return apkFile;
	}

	public void setFile(BmobFile file) {
		this.apkFile = file;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
