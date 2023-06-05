package com.atguigu.jxc.controller;

import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.entity.Customer;
import com.atguigu.jxc.service.CustomerService;
import com.atguigu.jxc.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedOperationParameter;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @program: jxc
 * @description:
 * @author: jq
 * @create: 2023-06-04 10:42
 **/
@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("/list")
    public Map<String, Object> queryCustomerList(Integer page, Integer rows, String customerName) {
        PageUtil pageUtil = new PageUtil(page, rows);
        return customerService.queryCustomerList(pageUtil, customerName);
    }

    @PostMapping("/save")
    public ServiceVO saveOrUpdateCustomer(Integer customerId, Customer customer) {
        return customerService.saveOrUpdateCustomer(customerId, customer);
    }

    @PostMapping("/delete")
    public ServiceVO deleteCustomerBatch(String ids) {
        return customerService.deleteCustomerBatch(ids);
    }


}
