package com.atguigu.jxc.service.impl;

import com.atguigu.jxc.dao.SupplierMapper;
import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.domain.SuccessCode;
import com.atguigu.jxc.entity.Supplier;
import com.atguigu.jxc.service.SupplierService;
import com.atguigu.jxc.util.PageUtil;
import com.atguigu.jxc.vo.SupplierVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @program: jxc
 * @description:
 * @author: jq
 * @create: 2023-06-03 14:03
 **/
@Service
public class SupplierServiceImpl implements SupplierService {
    @Resource
    SupplierMapper supplierMapper;

    @Override
    public Map<String, Object> queryAllSupplier(SupplierVo supplierVo) {
        PageUtil pageUtil = new PageUtil(supplierVo.getPage(), supplierVo.getRows());
        List<Supplier> supplierList = supplierMapper.queryAllSupplier(supplierVo.getSupplierName(), pageUtil.getPage(), pageUtil.getLimit());
        HashMap<String, Object> supplierMap = new HashMap<>();
        supplierMap.put("total", supplierList.size());
        supplierMap.put("rows", supplierList);
        return supplierMap;
    }

    @Override
    public ServiceVO saveOrUpdateSupplier(Integer supplierId, Supplier supplier) {
        try {
            if (supplierId==null){
                supplierMapper.saveSupplier(supplier);
            }else {
                supplierMapper.updateSupplier(supplierId,supplier);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new ServiceVO(SuccessCode.SUCCESS_CODE,SuccessCode.SUCCESS_MESS,null);
    }

    @Override
    public ServiceVO deleteSupplierByIds(String ids) {
        //todo 批量删除
        String[] split = ids.split(",");
        List<String> idList = Arrays.stream(ids.split(",")).collect(Collectors.toList());
        supplierMapper.deleteSupplierBatch(idList);
        return new ServiceVO(SuccessCode.SUCCESS_CODE,SuccessCode.SUCCESS_MESS);
    }
}
