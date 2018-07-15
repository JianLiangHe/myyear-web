package com.myyear.service;

import com.myyear.dto.UpdateCustomerPasswordDto;
import com.myyear.pojo.Customer;
import com.myyear.util.RtnResult;

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
	
	/**
	 * 更新用户资料完整度
	 * @param id
	 */
	void updateCustomerPerfectRatio(Long id);
	
	/**
	 * 更新用户信息
	 * @param customer
	 * @return
	 */
	RtnResult updateCustomerInfo(Customer customer);
	
	/**
	 * 修改用户密码
	 * @param dto
	 * @return
	 */
	RtnResult updateCustomerPassword(UpdateCustomerPasswordDto dto);
}
