package com.atguigu.jxc.service;

import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.entity.Goods;
import com.atguigu.jxc.entity.GoodsType;

import java.util.ArrayList;

/**
 * @description
 */
public interface GoodsTypeService {
    ArrayList<Object> loadGoodsType();

    void saveGoodsType(String goodsTypeName, Integer pId);

    void deleteGoodsTypeById(Integer goodsTypeId);


    ArrayList<GoodsType> queryAllGoodsTypeIds(Integer goodTypeId);
}
