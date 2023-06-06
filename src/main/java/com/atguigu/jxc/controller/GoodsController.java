package com.atguigu.jxc.controller;

import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.domain.SuccessCode;
import com.atguigu.jxc.entity.Goods;
import com.atguigu.jxc.service.GoodsService;
import com.atguigu.jxc.util.PageUtil;
import com.atguigu.jxc.vo.GoodsVo;
import com.google.gson.JsonElement;

import com.google.gson.JsonObject;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description 商品信息Controller
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    // /**
    //  * 分页查询商品库存信息
    //  *
    //  * @param page        当前页
    //  * @param rows        每页显示条数
    //  * @param codeOrName  商品编码或名称
    //  * @param goodsTypeId 商品类别ID
    //  * @return
    //  */
    @PostMapping({"/listInventory"})
    public Map<String, Object> queryGoodsPages(GoodsVo goodsVo) {
        PageUtil pageUtil = new PageUtil(goodsVo.getPage(), goodsVo.getRows());
        Map<String, Object> goods = goodsService.queryGoodsPages(pageUtil.getPage(), pageUtil.getLimit(), goodsVo.getCodeOrName(), goodsVo.getGoodsTypeId());
        return goods;
    }

    /**
     * 分页查询商品信息
     *
     * @param page        当前页
     * @param rows        每页显示条数
     * @param goodsName   商品名称
     * @param goodsTypeId 商品类别ID
     * @return 生成商品编码
     * @return
     */
    @PostMapping("/list")
    public Map<String, Object> queryGoodsList(Integer page, Integer rows, String goodsName, Integer goodsTypeId) {
        PageUtil pageUtil = new PageUtil(page, rows);
        ArrayList<Goods> goodsList = goodsService.queryGoodsList(pageUtil, goodsName, goodsTypeId);
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", goodsList.size());
        map.put("rows", goodsList);
        return map;
    }


    /**
     * 生成商品编码
     *
     * @return
     */
    @RequestMapping("/getCode")
    @RequiresPermissions(value = "商品管理")
    public ServiceVO getCode() {
        return goodsService.getCode();
    }

    /**
     * 添加或修改商品信息
     *
     * @param goods 商品信息实体
     * @return
     */
    @PostMapping(value = "/save")
    public ServiceVO saveOrUpdateGoods(Goods goods) {
        goodsService.saveOrUpdateGoods(goods);
        return getSuccessServiceVo();

    }

    private static ServiceVO<Object> getSuccessServiceVo() {
        return new ServiceVO<>(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS);
    }

    /**
     * price
     * 删除商品信息
     *
     * @param goodsId 商品ID
     * @return
     */
    @PostMapping(value = "/delete")
    public ServiceVO deleteGoodsByGoodsId(Integer goodsId) {
        goodsService.deleteGoodsByGoodsId(goodsId);
        return getSuccessServiceVo();
    }

    /**
     * 分页查询无库存商品信息
     *
     * @param page       当前页
     * @param rows       每页显示条数
     * @param nameOrCode 商品名称或商品编码
     * @return
     */
    @PostMapping(value = "/getNoInventoryQuantity")
    public Map<String, Object> getNoInventoryQuantity(Integer page, Integer rows, String nameOrCode) {
        PageUtil pageUtil = new PageUtil(page, rows);
        List<Goods> noInventoryQuantityGoods = goodsService.getNoInventoryQuantity(pageUtil, nameOrCode);
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", noInventoryQuantityGoods.size());
        map.put("rows", noInventoryQuantityGoods);
        return map;
    }


    /**
     * 分页查询有库存商品信息
     *
     * @param page       当前页
     * @param rows       每页显示条数
     * @param nameOrCode 商品名称或商品编码
     * @return
     */
    @PostMapping(value = "/getHasInventoryQuantity")
    public Map<String, Object> getInventoryQuantity(Integer page, Integer rows, String nameOrCode) {
        PageUtil pageUtil = new PageUtil(page, rows);
        List<Goods> inventoryQuantityGoods = goodsService.getInventoryQuantity(pageUtil, nameOrCode);
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", inventoryQuantityGoods.size());
        map.put("rows", inventoryQuantityGoods);
        return map;
    }


    /**
     * 添加商品期初库存
     *
     * @param goodsId           商品ID
     * @param inventoryQuantity 库存
     * @param purchasingPrice   成本价
     * @return
     */
    @PostMapping(value = "/saveStock")
    public ServiceVO saveStock(@RequestParam(value = "goodsId") Integer goodsId,
                               Integer inventoryQuantity, BigDecimal purchasingPrice) {
        goodsService.saveOrUpdateStock(goodsId, inventoryQuantity, purchasingPrice);
        return getSuccessServiceVo();
    }


    /**
     * 删除商品库存
     * @param goodsId 商品ID
     * @return
     */

    /**
     * 查询库存报警商品信息
     *
     * @return
     */
    @PostMapping("/listAlarm")
    public Map<String, Object> getListAlarm() {
        List<Goods> goodsList = goodsService.getListAlarm();
        HashMap<String, Object> map = new HashMap<>();
        map.put("rows", goodsList);
        return map;
    }

}
