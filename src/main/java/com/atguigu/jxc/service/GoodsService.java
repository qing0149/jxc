package com.atguigu.jxc.service;

import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.entity.Goods;
import com.atguigu.jxc.util.PageUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface GoodsService {


    ServiceVO getCode();

    Map<String,Object> queryGoodsPages(Integer page, Integer rows, String codeOrName, Integer goodsTypeId);

    Map<String, Object> queryAllUnitList();

    /**
     * 修改或者添加商品
     * @param goods
     */
    void saveOrUpdateGoods(Goods goods);

    void deleteGoodsByGoodsId(Integer goodsId);

    ArrayList<Goods> queryGoodsList(PageUtil pageUtil, String goodsName, Integer goodsTypeId);

    List<Goods> getNoInventoryQuantity(PageUtil pageUtil, String nameOrCode);

    List<Goods> getInventoryQuantity(PageUtil pageUtil, String nameOrCode);

    void saveOrUpdateStock(Integer goodsId, Integer inventoryQuantity, BigDecimal purchasingPrice);
}
