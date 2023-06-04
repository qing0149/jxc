package com.atguigu.jxc.dao;

import com.atguigu.jxc.dto.GoodsUnit;
import com.atguigu.jxc.dto.SaleTotalMap;
import com.atguigu.jxc.entity.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @description 商品信息
 */
public interface GoodsDao {


    String getMaxCode();


    ArrayList<Goods> queryGoodsPages(@Param(value = "page") Integer page,
                                     @Param(value = "rows") Integer rows,
                                     @Param(value = "codeOrName") String codeOrName,
                                     @Param(value = "goodsTypeId") Integer goodsTypeId);

    /**
     * @return
     */
    ArrayList<SaleTotalMap> selectSaleTotal();

    ArrayList<GoodsUnit> queryAllUnitList();
}
