package com.myyear.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.myyear.dao.CustomerDao;
import com.myyear.pojo.Customer;
import com.myyear.service.CustomerService;

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
	
	public boolean isCustomerByID(Long id) {
		boolean flag = false;
		
		Customer customer = customerDao.selectByPrimaryKey(id);
		
		flag = (customer == null ? false : true);
		
		return flag;
	}

	public boolean isCustomerByAccountNumber(String accountNumber) {
		boolean flag = false;
		
		Customer customer = customerDao.getCustomerByAccountNumber(accountNumber);
		
		flag = (customer == null ? false : true);
		
		return flag;
	}

}
