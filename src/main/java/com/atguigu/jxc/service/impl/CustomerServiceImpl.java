package com.atguigu.jxc.service.impl;

import com.atguigu.jxc.dao.CustomerMapper;
import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.domain.SuccessCode;
import com.atguigu.jxc.entity.Customer;
import com.atguigu.jxc.service.CustomerService;
import com.atguigu.jxc.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: jxc
 * @description:
 * @author: jq
 * @create: 2023-06-04 10:51
 **/
@Service
public class CustomerServiceImpl implements CustomerService {
    @Resource
    private CustomerMapper customerMapper;

    @Override
    public Map<String, Object> queryCustomerList(PageUtil pageUtil, String customerName) {
        List<Customer> customerList = customerMapper.queryCustomerList(pageUtil.getPage(), pageUtil.getLimit(), customerName);
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", customerList.size());
        map.put("rows", customerList);
        return map;
    }

    @Override
    public ServiceVO saveOrUpdateCustomer(Integer customerId, Customer customer) {
        if (customerId == null) {
            customerMapper.saveCustomer(customer);
        } else {
            customerMapper.updateByCustomer(customerId, customer);
        }
        return new ServiceVO<>(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS);
    }

    @Override
    public ServiceVO deleteCustomerBatch(String ids) {
        String[] idList = ids.split(",");
        if (ids.length()!=0){
            customerMapper.deleteByIds(idList);
        }
        return new ServiceVO<>(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS);
    }
}
