package com.atguigu.jxc.service;

import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.entity.Supplier;
import com.atguigu.jxc.vo.SupplierVo;

import java.util.Map;

/**
 * @ClassName SupplierService
 * @Description TODO
 * @Author qing
 * @Date 2023/6/3 14:03
 * @Version 1.0
 */
public interface SupplierService {
    Map<String, Object> queryAllSupplier(SupplierVo supplierVo);

    ServiceVO saveOrUpdateSupplier(Integer supplierId, Supplier supplier);

    ServiceVO deleteSupplierByIds(String ids);
}
