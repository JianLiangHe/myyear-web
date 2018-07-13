package com.myyear.dao;

import org.apache.ibatis.annotations.Param;

import com.myyear.pojo.Customer;

public interface CustomerDao {
    int deleteByPrimaryKey(Long id);

    int insert(Customer record);

    int insertSelective(Customer record);

    Customer selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Customer record);

    int updateByPrimaryKey(Customer record);
    
    /**
     * 根据AccountNumber获取用户
     * @param accountNumber
     * @return
     */
    Customer getCustomerByAccountNumber(
    		@Param("account_number") String accountNumber
    );
}