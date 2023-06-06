package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.DamageList;
import com.atguigu.jxc.entity.DamageListGoods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName DamageListGoodsDao
 * @Description TODO
 * @Author qing
 * @Date 2023/6/5 16:29
 * @Version 1.0
 */
public interface DamageListGoodsDao {
    void saveDamageListDao(@Param(value = "damageList") DamageList damageList);

    void saveDamageListGoodsDao(@Param(value = "damageListGoods") DamageListGoods damageListGoods);

    List<DamageList> queryDamageList();

    List<DamageListGoods> queryDamageListByDamageListId(@Param(value = "damageListId") Integer damageListId);
}
