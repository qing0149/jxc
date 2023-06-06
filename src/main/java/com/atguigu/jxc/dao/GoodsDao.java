package com.atguigu.jxc.dao;

import com.atguigu.jxc.dto.GoodsUnit;
import com.atguigu.jxc.dto.SaleTotalMap;
import com.atguigu.jxc.entity.Goods;
import com.atguigu.jxc.entity.PurchaseList;
import com.atguigu.jxc.entity.PurchaseListGoods;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
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

    void updateGoods(@Param(value = "goods") Goods goods);

    void saveGoods(@Param(value = "goods") Goods goods);

    Goods selectGoodsByGoodsId(Integer goodsId);

    void deleteGoodsById(Integer goodsId);

    PurchaseListGoods searchPurchaseListGoods(Integer goodsId);

    PurchaseList searchStateByPurchaseList(Integer purchaseListId);

    // ArrayList<Goods> queryGoodsPages(@Param(value = "page") Integer page,
    //                                  @Param(value = "rows") Integer limit,
    //                                  @Param(value = "codeorName") String goodsName,
    //                                  @Param(value = "goodTypeIds") List<Integer> goodTypeIds);
    ArrayList<Goods> queryGoodsPagesByTypes(@Param(value = "page") Integer page,
                                            @Param(value = "limit") Integer limit,
                                            @Param(value = "goodsName") String goodsName,
                                            @Param(value = "goodTypeIds") List<Integer> goodTypeIds);

    List<Goods> getNoInventoryQuantity(@Param(value = "page") Integer page,
                                       @Param(value = "limit") Integer limit,
                                       @Param(value = "nameOrCode") String nameOrCode);

    List<Goods> getInventoryQuantity(@Param(value = "page") Integer page,
                                     @Param(value = "limit") Integer limit,
                                     @Param(value = "nameOrCode") String nameOrCode);

    void saveOrUpdateStock(@Param(value = "goodsId") Integer goodsId,
                           @Param(value = "inventoryQuantity") Integer inventoryQuantity,
                           @Param(value ="purchasingPrice" ) BigDecimal purchasingPrice);

    List<Goods> queryAllGoods();
}
