package com.myyear.util;

import java.io.Serializable;

/**
 * 返回结果集对象
 * @author HeJianLiang
 *
 */
public class RtnResult implements Serializable {

	/**
	 * 结果
	 */
	private boolean result;
	
	/**
	 * 状态码
	 */
	private int code;
	
	/**
	 * 消息
	 */
	private String message;
	
	/**
	 * 返回对象
	 */
	private Object data;

	public RtnResult() {
		super();
	}

	public RtnResult(boolean result, int code, String message) {
		super();
		this.result = result;
		this.code = code;
		this.message = message;
	}

	public RtnResult(boolean result, int code, String message, Object data) {
		super();
		this.result = result;
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
}
