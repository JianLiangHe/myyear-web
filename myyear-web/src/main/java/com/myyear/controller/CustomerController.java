package com.myyear.controller;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myyear.pojo.Customer;
import com.myyear.service.LoginService;
import com.myyear.service.impl.LoginServiceImpl;
import com.myyear.util.RtnResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(description = "用户接口")
@RestController
@RequestMapping("/api/customer/")
public class CustomerController {

	private static final Logger LOGGER = Logger.getLogger(CustomerController.class);
	
	@Autowired
	private LoginService loginService;
	
	@ApiOperation(value = "登陆")
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public RtnResult login(
			@ApiParam(value = "账号", required = true) @RequestParam(value = "account_number", required = true) String account_number,
			@ApiParam(value = "密码", required = true) @RequestParam(value = "password", required = true) String password
	) {
		Customer customer = new Customer();
		customer.setAccount_number(account_number);
		customer.setPassword(password);
		return loginService.login(customer);
	}

}
