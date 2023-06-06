package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.OverflowListGoods;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName OverflowListGoodsDao
 * @Description TODO
 * @Author qing
 * @Date 2023/6/5 19:29
 * @Version 1.0
 */
public interface OverflowListGoodsDao {
    /**
     * 添加商品报溢单商品列表
     * @param overflowListGoods
     */
    void saveOverflowListGoods(@Param(value = "overFlowListGoods") OverflowListGoods overflowListGoods);
}
