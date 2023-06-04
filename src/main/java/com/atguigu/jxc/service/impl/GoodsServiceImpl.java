package com.atguigu.jxc.service.impl;

import com.atguigu.jxc.dao.GoodsDao;
import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.domain.SuccessCode;
import com.atguigu.jxc.dto.GoodsUnit;
import com.atguigu.jxc.dto.SaleTotalMap;
import com.atguigu.jxc.entity.Goods;
import com.atguigu.jxc.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @description
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Resource
    private GoodsDao goodsDao;

    @Override
    public ServiceVO getCode() {

        // 获取当前商品最大编码
        String code = goodsDao.getMaxCode();

        // 在现有编码上加1
        Integer intCode = Integer.parseInt(code) + 1;

        // 将编码重新格式化为4位数字符串形式
        String unitCode = intCode.toString();

        for (int i = 4; i > intCode.toString().length(); i--) {
            unitCode = "0" + unitCode;

        }
        return new ServiceVO<>(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS, unitCode);
    }

    @Override
    public Map<String, Object> queryGoodsPages(Integer page, Integer rows, String codeOrName, Integer goodsTypeId) {
        ArrayList<Goods> goods = goodsDao.queryGoodsPages(page, rows, codeOrName, goodsTypeId);
        HashMap<String, Object> map = new HashMap<>();
        if (CollectionUtils.isEmpty(goods)) {
            map.put("total", 0);
            map.put("rows", null);
        } else {
            map.put("total", goods.size());
            ArrayList<SaleTotalMap> saleTotalMaps = goodsDao.selectSaleTotal();
            Map<Integer, Integer> saleTotalMapsCollect = saleTotalMaps.stream().collect(Collectors.toMap(SaleTotalMap::getGoodsId, SaleTotalMap::getSaleTotal));
            goods.stream().forEach(good -> {
                if (saleTotalMapsCollect.containsKey(good.getGoodsId())) {
                    good.setSaleTotal(saleTotalMapsCollect.get(good.getGoodsId()));
                } else {
                    good.setSaleTotal(0);
                }
            });
            map.put("rows", goods);
        }
        return map;
    }

    @Override
    public Map<String, Object> queryAllUnitList() {
        ArrayList<GoodsUnit> goodsUnits = goodsDao.queryAllUnitList();
        HashMap<String, Object> map = new HashMap<>();
        map.put("rows", goodsUnits);
        return map;
    }
}
