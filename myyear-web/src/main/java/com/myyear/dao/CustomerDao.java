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
    
    /**
     * 更新指定id的资料完整度
     * @param id
     */
    void updateCustomerPerfectRatio(
    		@Param("id") Long id
    );
    
    /**
     * 更新用户信息
     * @param customer
     */
    void updateCustomerInfo(
    		@Param("customer") Customer customer
    );
    
    /**
     * 更新用户密码
     * @param password
     * @param id
     */
    void updateCustomerPassword(
    		@Param("password") String password,
    		@Param("id") Long id
    );
}