package com.myyear.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.myyear.dao.CustomerDao;
import com.myyear.pojo.Customer;
import com.myyear.service.CustomerService;
import com.myyear.service.LoginService;
import com.myyear.util.RtnResult;
import com.myyear.util.WebUtils;

/**
 * 登陆业务的实现类
 * @author HeJianLiang
 *
 */
@Service("loginService")
public class LoginServiceImpl implements LoginService {

	private static final Logger LOGGER = Logger.getLogger(LoginServiceImpl.class);
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private CustomerDao customerDao;
	
	public RtnResult login(Customer customer) {
		boolean flag = false;
		System.out.println("ok");
		// 用户账号
		String accountNumber = customer.getAccount_number();
		
		// 1, 判断账号是否存在
		Customer realCustomer = customerDao.getCustomerByAccountNumber(accountNumber);
		
		if (null == realCustomer) {
			return new RtnResult(WebUtils.ERROR_RESULT, WebUtils.ERROR_STATUS, "账号不存在, 操作失败.");
		}
		
		// 2, 校验密码
		String loginPassword = customer.getPassword();
		String realPassword = realCustomer.getPassword();
		
		if (!realPassword.equals(loginPassword)) {
			return new RtnResult(WebUtils.ERROR_RESULT, WebUtils.ERROR_STATUS, "账号密码有误, 操作失败.");
		}
		
		
		return new RtnResult(WebUtils.SUCCESS_RESULT, WebUtils.SUCCESS_STATUS, WebUtils.SUCCESS_MESSAGE, realCustomer);
	}

}
