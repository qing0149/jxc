package com.atguigu.jxc.service;

import com.atguigu.jxc.entity.OverflowList;
import com.atguigu.jxc.entity.OverflowListGoods;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @ClassName OverflowListGoodsService
 * @Description TODO
 * @Author qing
 * @Date 2023/6/5 19:10
 * @Version 1.0
 */
public interface OverflowListGoodsService {
    /**
     * 新增报溢单
     * @param overflowList
     * @param overflowListGoodsStr
     */
    void saveOverFlowListGoods(OverflowList overflowList, String overflowListGoodsStr, HttpSession httpSession);

    List<OverflowList> getOverFlowList(String sTime, String eTime);

    List<OverflowListGoods> getGoodsListByOverflowListId(Integer overflowListId);
}
