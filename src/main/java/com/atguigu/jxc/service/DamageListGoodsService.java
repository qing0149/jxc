package com.atguigu.jxc.service;

import com.atguigu.jxc.entity.DamageList;
import com.atguigu.jxc.entity.DamageListGoods;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @ClassName DamageListGoodsService
 * @Description TODO
 * @Author qing
 * @Date 2023/6/5 16:28
 * @Version 1.0
 */
public interface DamageListGoodsService {
    void saveDamageListGoods(DamageList damageList, String damageListGoodsStr, HttpSession httpSession);

    List<DamageListGoods> goodsList(Integer damageListId);

    List<DamageList> getDamageListGoods(String sTime, String eTime);
}
