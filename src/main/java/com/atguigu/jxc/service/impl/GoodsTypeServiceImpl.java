package com.atguigu.jxc.service.impl;

import com.atguigu.jxc.dao.GoodsTypeDao;
import com.atguigu.jxc.entity.GoodsType;
import com.atguigu.jxc.entity.Log;
import com.atguigu.jxc.service.GoodsTypeService;
import com.atguigu.jxc.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @description
 */
@Service
public class GoodsTypeServiceImpl implements GoodsTypeService {

    @Autowired
    private LogService logService;
    @Resource
    private GoodsTypeDao goodsTypeDao;

    @Override
    public ArrayList<Object> loadGoodsType() {
        logService.save(new Log(Log.SELECT_ACTION, "查询商品类别信息"));
        return this.getAllGoodsType(-1); // 根节点默认从-1开始
    }

    @Override
    public void saveGoodsType(String goodsTypeName, Integer pId) {
        GoodsType goodsType = goodsTypeDao.querGoodsTypeById(pId);
        if (goodsType.getGoodsTypeState() == 0) {
            goodsType.setGoodsTypeState(1);
            goodsTypeDao.updateGoodsTypeState(goodsType);
        }
        int goodTypeStatus = 0;
        Long id = goodsTypeDao.saveGoodsType(goodsTypeName, pId, goodTypeStatus);
    }

    @Override
    public void deleteGoodsTypeById(Integer goodsTypeId) {
        goodsTypeDao.deleteGoodsTypeById(goodsTypeId);
    }


    @Override
    public ArrayList<GoodsType> queryAllGoodsTypeIds(Integer goodTypeId) {
        GoodsType goodsType = goodsTypeDao.querGoodsTypeById(goodTypeId);
        ArrayList<GoodsType> goodsTypes = new ArrayList<>();
        if (goodsType.getPId().intValue() == -1) {
            goodsTypes = goodsTypeDao.queryAllGoodsType(null);
        } else if (goodsType.getGoodsTypeState() == 1) {
            goodsTypes = goodsTypeDao.queryAllGoodsType(goodTypeId);
            for (GoodsType gt : goodsTypes) {
                if (gt.getGoodsTypeState() == 1) {
                    goodsTypes.addAll(goodsTypeDao.queryAllGoodsType(goodTypeId));
                }
            }
        } else {
            goodsTypes = new ArrayList<>();
            goodsTypes.add(goodsType);
        }
        return goodsTypes;
    }


    /**
     * 递归查询所有商品类别
     *
     * @return
     */
    public ArrayList<Object> getAllGoodsType(Integer parentId) {
        ArrayList<Object> array = this.getGoodSTypeByParentId(parentId);
        for (int i = 0; i < array.size(); i++) {
            HashMap obj = (HashMap) array.get(i);
            if (obj.get("state").equals("open")) {// 如果是叶子节点，不再递归
            } else {// 如果是根节点，继续递归查询
                obj.put("children", this.getAllGoodsType(Integer.parseInt(obj.get("id").toString())));
            }
        }
        return array;
    }

    /**
     * 根据父ID获取所有子商品类别
     *
     * @return
     */
    public ArrayList<Object> getGoodSTypeByParentId(Integer parentId) {
        ArrayList<Object> array = new ArrayList<>();
        List<GoodsType> goodsTypeList = goodsTypeDao.getAllGoodsTypeByParentId(parentId);
        System.out.println("goodsTypeList" + goodsTypeList);
        // 遍历商品类别
        for (GoodsType goodsType : goodsTypeList) {
            HashMap obj = new HashMap<String, Object>();
            obj.put("id", goodsType.getGoodsTypeId());
            obj.put("text", goodsType.getGoodsTypeName());
            if (goodsType.getGoodsTypeState() == 1) {
                obj.put("state", "closed");
            } else {
                obj.put("state", "open");
            }
            obj.put("iconCls", "goods-type");
            HashMap<String, Object> attributes = new HashMap<>();
            attributes.put("state", goodsType.getGoodsTypeState());
            obj.put("attributes", attributes);
            array.add(obj);
        }
        return array;
    }
}
