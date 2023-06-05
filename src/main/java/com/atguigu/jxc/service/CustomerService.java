package com.atguigu.jxc.service;

import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.entity.Customer;
import com.atguigu.jxc.util.PageUtil;

import java.util.Map;

/**
 * @program: jxc
 * @description:
 * @author: jq
 * @create: 2023-06-04 10:49
 **/
public interface CustomerService {
     Map<String, Object> queryCustomerList(PageUtil pageUtil, String customerName);

     ServiceVO saveOrUpdateCustomer(Integer customerId, Customer customer);

     ServiceVO deleteCustomerBatch(String ids);
}
