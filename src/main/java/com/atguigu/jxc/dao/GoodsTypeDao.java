package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.Goods;
import com.atguigu.jxc.entity.GoodsType;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

/**
 * @description 商品类别
 */
public interface GoodsTypeDao {

    List<GoodsType> getAllGoodsTypeByParentId(Integer pId);

    Integer updateGoodsTypeState(GoodsType parentGoodsType);


    Long saveGoodsType(@Param(value = "goodsTypeName") String goodsTypeName,
                       @Param(value = "pId") Integer pId,
                       @Param(value = "goodTypeStatus") int goodTypeStatus);

    /**
     * 删除商品类型
     *
     * @param goodsTypeId
     */
    void deleteGoodsTypeById(Integer goodsTypeId);

    ArrayList<GoodsType> queryAllGoodsType(@Param(value = "pid") Integer pid);

    GoodsType querGoodsTypeById(Integer goodTypeId);
}
