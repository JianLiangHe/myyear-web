package com.myyear.service;

import com.myyear.pojo.Customer;
import com.myyear.util.RtnResult;

public interface RegisterService {

	/**
	 * 注册
	 * @param customer
	 * @return
	 */
	RtnResult register(Customer customer);
	
}
