package com.myyear.controller;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myyear.dto.UpdateCustomerPasswordDto;
import com.myyear.pojo.Customer;
import com.myyear.service.CustomerService;
import com.myyear.service.LoginService;
import com.myyear.service.RegisterService;
import com.myyear.util.DateUtil;
import com.myyear.util.MD5Util;
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
	
	@Autowired
	private RegisterService registerService;
	
	@Autowired
	private CustomerService customerService;
	
	@ApiOperation(value = "注册")
	@RequestMapping(value = "register", method = RequestMethod.POST)
	public RtnResult register(
			@ApiParam(value = "账号", required = true) @RequestParam(value = "account_number", required = true) String account_number,
			@ApiParam(value = "密码", required = true) @RequestParam(value = "password", required = true) String password, 
			@ApiParam(value = "用户名称", required = true) @RequestParam(value = "user_name", required = true) String user_name,
			@ApiParam(value = "性别(0:女, 1:男)", required = true) @RequestParam(value = "sex", required = true) int sex,
			@ApiParam(value = "出生日期", required = true) @RequestParam(value = "birthday", required = true) String birthday,
			@ApiParam(value = "邮箱", required = false) @RequestParam(value = "email", required = false) String email,
			@ApiParam(value = "头像", required = false) @RequestParam(value = "photo", required = false) String photo,
			@ApiParam(value = "城市", required = false) @RequestParam(value = "city", required = false) String city
	) {
		Customer customer = new Customer();
		customer.setAccount_number(account_number);
		customer.setPassword(password);
		customer.setUser_name(user_name);
		customer.setSex(sex);
		customer.setBirthday(DateUtil.stringToDate2(birthday));
		customer.setEmail(email);
		customer.setPhoto(photo);
		customer.setCity(city);
		
		return registerService.register(customer);
	}
	
	@ApiOperation(value = "登陆")
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public RtnResult login(
			@ApiParam(value = "账号", required = true) @RequestParam(value = "account_number", required = true) String account_number,
			@ApiParam(value = "密码", required = true) @RequestParam(value = "password", required = true) String password
	) {
		Customer customer = new Customer();
		customer.setAccount_number(account_number);
		customer.setPassword(MD5Util.string2MD5(password));
		
		return loginService.login(customer);
	}

	@ApiOperation(value = "编辑用户信息")
	@RequestMapping(value = "updateCustomerInfo", method = RequestMethod.POST)
	public RtnResult updateCustomerInfo(
			@ApiParam(value = "用户ID", required = true) @RequestParam(value = "id", required = true) Long id,
			@ApiParam(value = "性别(0:女, 1:男)", required = true) @RequestParam(value = "sex", required = true) int sex,
			@ApiParam(value = "出生日期", required = true) @RequestParam(value = "birthday", required = true) String birthday,
			@ApiParam(value = "邮箱", required = false) @RequestParam(value = "email", required = false) String email,
			@ApiParam(value = "头像", required = false) @RequestParam(value = "photo", required = false) String photo,
			@ApiParam(value = "城市", required = false) @RequestParam(value = "city", required = false) String city
			
	) {
		Customer customer = new Customer();
		customer.setId(id);
		customer.setSex(sex);
		customer.setBirthday(DateUtil.stringToDate2(birthday));
		customer.setEmail(email);
		customer.setPhoto(photo);
		customer.setCity(city);
		
		return customerService.updateCustomerInfo(customer);
	}
	
	@ApiOperation("修改密码")
	@RequestMapping(value = "updatePassword", method = RequestMethod.POST)
	public RtnResult updatePassword(
			@ApiParam(value = "用户ID", required = true) @RequestParam(value = "id", required = true) Long id,
			@ApiParam(value = "旧密码", required = true) @RequestParam(value = "old_password", required = true) String old_password,
			@ApiParam(value = "新密码1(第一次输入)", required = true) @RequestParam(value = "new_password1", required = true) String new_password1,
			@ApiParam(value = "新密码2(第二次输入)", required = true) @RequestParam(value = "new_password2", required = true) String new_password2
	) {
		UpdateCustomerPasswordDto dto = new UpdateCustomerPasswordDto();
		dto.setId(id);
		dto.setOld_password(old_password);
		dto.setNew_password1(new_password1);
		dto.setNew_password2(new_password2);

		return customerService.updateCustomerPassword(dto);
	}
}
