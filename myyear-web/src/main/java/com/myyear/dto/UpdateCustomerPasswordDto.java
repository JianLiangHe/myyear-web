package com.myyear.dto;

import java.io.Serializable;

/**
 * 接受用户修改密码参数的dto
 * @author Administrator
 *
 */
public class UpdateCustomerPasswordDto implements Serializable {

	/**
	 * 用户id
	 */
	private Long id;
	
	/**
	 * 旧密码
	 */
	private String old_password;
	
	/**
	 * 新密码1(第一次输入)
	 */
	private String new_password1;
	
	/**
	 * 新密码2(第二次输入)
	 */
	private String new_password2;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOld_password() {
		return old_password;
	}

	public void setOld_password(String old_password) {
		this.old_password = old_password;
	}

	public String getNew_password1() {
		return new_password1;
	}

	public void setNew_password1(String new_password1) {
		this.new_password1 = new_password1;
	}

	public String getNew_password2() {
		return new_password2;
	}

	public void setNew_password2(String new_password2) {
		this.new_password2 = new_password2;
	}
	
}
