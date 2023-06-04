package com.atguigu.jxc.controller;

import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.entity.Supplier;
import com.atguigu.jxc.service.SupplierService;
import com.atguigu.jxc.vo.SupplierVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @program: jxc
 * @description:
 * @author: jq
 * @create: 2023-06-03 13:55
 **/
@RestController
@RequestMapping("/supplier")
public class SupplierController {
    @Autowired
    SupplierService supplierService;

    /**
     * 查询所有供应商
     *
     * @param supplierVo
     * @return
     */
    @PostMapping("/list")
    public Map<String, Object> queryAllSupplier(SupplierVo supplierVo) {
        Map<String, Object> allSupplier = supplierService.queryAllSupplier(supplierVo);
        return allSupplier;
    }

    @PostMapping("/save")
    public ServiceVO saveOrUpdateSupplier(Integer supplierId, Supplier supplier) {

        return supplierService.saveOrUpdateSupplier(supplierId, supplier);
    }

    @PostMapping("/delete")
    public ServiceVO deleteSupplierByIds(String ids) {
        // System.out.println(ids);
        return supplierService.deleteSupplierByIds(ids);
    }
}
