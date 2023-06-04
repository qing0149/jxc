package com.atguigu.jxc.service;

import com.atguigu.jxc.domain.ServiceVO;

import java.util.Map;

public interface GoodsService {


    ServiceVO getCode();

    Map<String,Object> queryGoodsPages(Integer page, Integer rows, String codeOrName, Integer goodsTypeId);

    Map<String, Object> queryAllUnitList();

}
