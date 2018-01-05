package com.http;

public enum CMResponseCode {
	
	
;
	
	private CMResponseCode(int code) {
		this.code = code;
	}

	private int code;
	
	public void setCode(int code){
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
}
