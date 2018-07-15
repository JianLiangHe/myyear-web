package com.myyear.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myyear.dao.CustomerDao;
import com.myyear.pojo.Customer;
import com.myyear.service.CustomerService;
import com.myyear.service.RegisterService;
import com.myyear.util.DateUtil;
import com.myyear.util.MD5Util;
import com.myyear.util.RtnResult;
import com.myyear.util.WebUtils;

@Service("registerService")
public class RegisterServiceImpl implements RegisterService {

	private static final Logger LOGGER = Logger.getLogger(RegisterServiceImpl.class);
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private CustomerDao customerDao;
	
	public RtnResult register(Customer customer) {
		boolean flag = false;
		
		String accountNumber = customer.getAccount_number();
		String password = customer.getPassword();
		String email = customer.getEmail();
		
		// 1, 判断账号是否存在
		flag = customerService.isCustomerByAccountNumber(accountNumber);
		
		if (flag) {
			LOGGER.info("用户注册失败, 账号[" + accountNumber + "]已存在.");
			return new RtnResult(WebUtils.ERROR_RESULT, WebUtils.ERROR_STATUS, "账号已经存在, 操作失败.");
		}
		
		// 2, 校验信息
		flag = (password.length() >=6 && password.length() <= 12 ? true : false);
		
		if (!flag) {
			LOGGER.info("用户注册失败, 密码长度范围不正确.");
			return new RtnResult(WebUtils.ERROR_RESULT, WebUtils.ERROR_STATUS, "密码长度只能为(6 - 12位), 操作失败.");
		}
		
		try {
			// 3, 新增用户
			
			// 计算年龄
			int age = DateUtil.getAge(customer.getBirthday());
			customer.setAge(age);
			
			// 密码加密
			customer.setPassword(MD5Util.string2MD5(customer.getPassword()));
			customerDao.insertSelective(customer);
			
			try {
				// 更新用户资料完整度
				Long customerID = customer.getId();
				customerService.updateCustomerPerfectRatio(customerID);
			} catch (Exception e) {
				LOGGER.error("更新用户资料完整度失败, id: " + customer.getId());
			}

			return new RtnResult(WebUtils.SUCCESS_RESULT, WebUtils.SUCCESS_STATUS, WebUtils.SUCCESS_MESSAGE, customer);
		} catch (Exception e) {
			LOGGER.error("注册用户失败.");
			return new RtnResult(WebUtils.ERROR_RESULT, WebUtils.ERROR_STATUS, WebUtils.ERROR_MESSAGE);
		}
	}

}
