package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.Supplier;
import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Primary;

import java.util.List;

/**
 * @ClassName SupplierMapper
 * @Description TODO
 * @Author qing
 * @Date 2023/6/3 14:04
 * @Version 1.0
 */
public interface SupplierMapper {
    List<Supplier> queryAllSupplier(@Param(value = "supplierName") String supplierName, @Param(value = "page") Integer page, @Param(value = "limit") Integer limit);

    void saveSupplier(@Param(value = "supplier") Supplier supplier);

    void updateSupplier(@Param(value = "supplierId") Integer supplierId,@Param(value = "supplier") Supplier supplier);

    void deleteSupplierBatch(List<String> idList);
}
