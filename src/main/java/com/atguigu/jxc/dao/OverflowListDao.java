package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.OverflowList;
import com.atguigu.jxc.entity.OverflowListGoods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName OverflowListDao
 * @Description TODO
 * @Author qing
 * @Date 2023/6/5 19:29
 * @Version 1.0
 */
public interface OverflowListDao {
    void saveOverFlowList(@Param(value = "overflowList") OverflowList overflowList);

    List<OverflowList> queryAllOverFlowList();

    List<OverflowListGoods> getGoodsListByOverflowListId(Integer overflowListId);
}
