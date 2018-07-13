package com.myyear.service;

import com.myyear.pojo.Customer;
import com.myyear.util.RtnResult;

/**
 * 登陆业务接口
 * @author HeJianLiang
 *
 */
public interface LoginService {

	/**
	 * 登陆
	 * @param customer
	 * @return
	 */
	RtnResult login(Customer customer);
	
}
