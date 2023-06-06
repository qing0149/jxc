package com.atguigu.jxc.controller;

import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.domain.SuccessCode;
import com.atguigu.jxc.entity.DamageList;
import com.atguigu.jxc.entity.DamageListGoods;
import com.atguigu.jxc.entity.OverflowList;
import com.atguigu.jxc.entity.OverflowListGoods;
import com.atguigu.jxc.service.DamageListGoodsService;
import com.atguigu.jxc.service.OverflowListGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: jxc
 * @description:
 * @author: jq
 * @create: 2023-06-05 16:21
 **/
@RestController
@RequestMapping
public class DamageListGoodsController {
    @Autowired
    DamageListGoodsService damageListGoodsService;
    @Autowired
    OverflowListGoodsService overflowListGoodsService;

    /**
     * 保存报损订单
     *
     * @param damageList
     * @param damageListGoodsStr
     * @return
     */
    @PostMapping(value = "/damageListGoods/save")
    public ServiceVO saveDamageListGoods(DamageList damageList, String damageListGoodsStr, HttpSession httpSession) {
        damageListGoodsService.saveDamageListGoods(damageList, damageListGoodsStr, httpSession);
        return new ServiceVO(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS);
    }

    @PostMapping(value = "/overflowListGoods/save")
    public ServiceVO saveOverFlowListGoods(OverflowList overflowList, String OverflowListGoods, HttpSession httpSession) {
        overflowListGoodsService.saveOverFlowListGoods(overflowList, OverflowListGoods, httpSession);
        return new ServiceVO(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS);
    }

    @PostMapping(value = "/damageListGoods/list")
    public Map<String, Object> getDamageListGoods(String sTime, String eTime) {
        List<DamageList> damageListGoodsList = damageListGoodsService.getDamageListGoods(sTime, eTime);
        HashMap<String, Object> map = new HashMap<>();
        map.put("rows", damageListGoodsList);
        return map;
    }

    @PostMapping(value = "/damageListGoods/goodsList")
    public Map<String, Object> goodsList(Integer damageListId) {
        List<DamageListGoods> damageListGoodsList = damageListGoodsService.goodsList(damageListId);
        HashMap<String, Object> map = new HashMap<>();
        map.put("rows", damageListGoodsList);
        return map;
    }

    @PostMapping(value = "/overflowListGoods/list")
    public Map<String, Object> overflowListGoods(String sTime, String eTime) {
        List<OverflowList> overflowLists = overflowListGoodsService.getOverFlowList(sTime, eTime);
        HashMap<String, Object> map = new HashMap<>();
        map.put("rows", overflowLists);
        return map;
    }

    @PostMapping(value = "/overflowListGoods/goodsList")
    public Map<String, Object> getGoodsListByOverflowListId(Integer overflowListId) {
        List<OverflowListGoods> overflowListGoodsList = overflowListGoodsService.getGoodsListByOverflowListId(overflowListId);
        HashMap<String, Object> map = new HashMap<>();
        map.put("rows", overflowListGoodsList);
        return map;
    }

}
