package com.myyear.service;

import com.myyear.pojo.Customer;

/**
 * 用户业务接口
 * @author HeJianLiang
 *
 */
public interface CustomerService {

	/**
	 * 判断指定用户id是否存在
	 * @param id
	 * @return
	 */
	boolean isCustomerByID(Long id);
	
	/**
	 * 判断指定用户账号是否存在
	 * @param accountNumber
	 * @return
	 */
	boolean isCustomerByAccountNumber(String accountNumber);
	
}
