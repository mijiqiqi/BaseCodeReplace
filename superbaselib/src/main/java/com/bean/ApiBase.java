package com.bean;

public class ApiBase<T> {
    private int code;
    private String msg;
    private Integer timestamp;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Integer getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Integer timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "ApiBase [code=" + code + ", msg=" + msg + ", data=" + data
				+ "]";
	}

    
}
