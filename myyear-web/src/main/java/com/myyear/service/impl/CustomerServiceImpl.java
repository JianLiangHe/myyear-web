package com.myyear.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.myyear.dao.CustomerDao;
import com.myyear.dto.UpdateCustomerPasswordDto;
import com.myyear.pojo.Customer;
import com.myyear.service.CustomerService;
import com.myyear.util.DateUtil;
import com.myyear.util.MD5Util;
import com.myyear.util.RtnResult;
import com.myyear.util.WebUtils;

/**
 * 用业务的实现类
 * @author HeJianLiang
 *
 */
@Service("customerService")
@Transactional(propagation = Propagation.REQUIRED)
public class CustomerServiceImpl implements CustomerService {

	private static final Logger LOGGER = Logger.getLogger(CustomerServiceImpl.class);
	
	@Autowired
	private CustomerDao customerDao;
	
	@Override
	public boolean isCustomerByID(Long id) {
		boolean flag = false;
		
		Customer customer = customerDao.selectByPrimaryKey(id);
		
		flag = (customer == null ? false : true);
		
		return flag;
	}

	@Override
	public boolean isCustomerByAccountNumber(String accountNumber) {
		boolean flag = false;
		
		Customer customer = customerDao.getCustomerByAccountNumber(accountNumber);
		
		flag = (customer == null ? false : true);
		
		return flag;
	}

	@Override
	public void updateCustomerPerfectRatio(Long id) {
		customerDao.updateCustomerPerfectRatio(id);
	}

	@Override
	public RtnResult updateCustomerInfo(Customer customer) {
		Long customerID = customer.getId();
		
		try {
			// 计算年龄
			int age = DateUtil.getAge(customer.getBirthday());
			customer.setAge(age);
			
			// 更新信息
			customerDao.updateCustomerInfo(customer);
			
			// 更新资料完整度
			updateCustomerPerfectRatio(customerID);

			// 查询用户
			customer = customerDao.selectByPrimaryKey(customerID);
			
			return new RtnResult(WebUtils.SUCCESS_RESULT, WebUtils.SUCCESS_STATUS, WebUtils.SUCCESS_MESSAGE, customer);
		} catch (Exception e) {
			LOGGER.error("更新用户信息有误, 用户id: " + customerID);
			return new RtnResult(WebUtils.ERROR_RESULT, WebUtils.ERROR_STATUS, WebUtils.ERROR_MESSAGE);
		}
	}

	@Override
	public RtnResult updateCustomerPassword(UpdateCustomerPasswordDto dto) {
		boolean flag = false;
		
		Long customerID = dto.getId();
		
		// 1, 判断旧密码是否正确
		Customer customer = customerDao.selectByPrimaryKey(customerID);
		
		String customerPassword = customer.getPassword();
		
		String inputOldPassword = MD5Util.string2MD5(dto.getOld_password());
		
		flag = inputOldPassword.equals(customerPassword);
		
		if (!flag) {
			LOGGER.info("用户修改密码有误, 原密码输入不正确, 用户id: " + customerID);
			return new RtnResult(WebUtils.ERROR_RESULT, WebUtils.ERROR_STATUS, "原密码输入不正确, 操作失败.");
		}
		
		// 2, 判断新密码长度
		String newPassword1 = dto.getNew_password1();
		String newPassword2 = dto.getNew_password2();
		
		if (newPassword1 == null || newPassword1.length()<6 || newPassword1.length() > 12) {
			LOGGER.info("用户修改密码有误, 新密码输入不正确, 用户id: " + customerID);
			return new RtnResult(WebUtils.ERROR_RESULT, WebUtils.ERROR_STATUS, "新密码长度范围不正确, 操作失败.");
		}
		
		// 3, 判断新密码是否相同
		flag = newPassword1.equals(newPassword2);
		
		if (!flag) {
			LOGGER.info("用户修改密码有误, 新密码1与新密码2不相同, 用户id: " + customerID);
			return new RtnResult(WebUtils.ERROR_RESULT, WebUtils.ERROR_STATUS, "新密码不相同, 操作失败.");
		}
		
		// 4, 判断新密码是否与旧密码相同
		// 新密码加密
		newPassword1 = MD5Util.string2MD5(newPassword1);
		
		flag = newPassword1.equals(customerPassword);
		
		if (flag) {
			LOGGER.info("用户修改密码有误, 新密码与旧密码相同, 用户id: " + customerID);
			return new RtnResult(WebUtils.ERROR_RESULT, WebUtils.ERROR_STATUS, "新密码不能与旧密码相同, 操作失败.");
		}
		
		try {
			// 5, 更新
			customerDao.updateCustomerPassword(newPassword1, customerID);
			return new RtnResult(WebUtils.SUCCESS_RESULT, WebUtils.SUCCESS_STATUS, WebUtils.SUCCESS_MESSAGE);
		} catch (Exception e) {
			LOGGER.error("用户修改密码有误, 用户id: " + customerID);
			return new RtnResult(WebUtils.ERROR_RESULT, WebUtils.ERROR_STATUS, WebUtils.ERROR_MESSAGE);
		}
	}

}
