package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.Customer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program: jxc
 * @description:
 * @author: jq
 * @create: 2023-06-04 11:19
 **/
public interface CustomerMapper {

    public List<Customer> queryCustomerList(@Param(value = "page") Integer page,
                                            @Param(value = "limit") Integer limit,
                                            @Param(value = "customerName") String customerName);

    void saveCustomer(@Param(value = "customer") Customer customer);

    void updateByCustomer(@Param(value = "customerId") Integer customerId, @Param(value = "customer") Customer customer);

    void deleteByIds(@Param(value = "idList") String[] idList);
}
