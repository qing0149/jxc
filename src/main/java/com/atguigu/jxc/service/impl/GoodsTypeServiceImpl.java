package com.atguigu.jxc.service.impl;

import com.atguigu.jxc.dao.GoodsTypeDao;
import com.atguigu.jxc.domain.ErrorCode;
import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.domain.SuccessCode;
import com.atguigu.jxc.entity.Goods;
import com.atguigu.jxc.entity.GoodsType;
import com.atguigu.jxc.entity.Log;
import com.atguigu.jxc.service.GoodsTypeService;
import com.atguigu.jxc.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    @Autowired
    private GoodsTypeDao goodsTypeDao;

    @Override
    public  ArrayList<Object> loadGoodsType() {
        logService.save(new Log(Log.SELECT_ACTION, "查询商品类别信息"));


        return this.getAllGoodsType(-1); // 根节点默认从-1开始
    }

    /**
     * 递归查询所有商品类别
     *
     * @return
     */
    public ArrayList<Object> getAllGoodsType(Integer parentId){

        ArrayList<Object> array = this.getGoodSTypeByParentId(parentId);

        for(int i = 0;i < array.size();i++){

            HashMap obj = (HashMap) array.get(i);

            if(obj.get("state").equals("open")){// 如果是叶子节点，不再递归

            }else{// 如果是根节点，继续递归查询
                obj.put("children", this.getAllGoodsType(Integer.parseInt(obj.get("id").toString())));
            }

        }

        return array;
    }

    /**
     * 根据父ID获取所有子商品类别
     * @return
     */
    public ArrayList<Object> getGoodSTypeByParentId(Integer parentId){

        ArrayList<Object> array = new ArrayList<>();

        List<GoodsType> goodsTypeList = goodsTypeDao.getAllGoodsTypeByParentId(parentId);

        System.out.println("goodsTypeList" + goodsTypeList);
        //遍历商品类别
        for(GoodsType goodsType : goodsTypeList){

            HashMap obj = new HashMap<String, Object>();

            obj.put("id", goodsType.getGoodsTypeId());
            obj.put("text", goodsType.getGoodsTypeName());

            if(goodsType.getGoodsTypeState() == 1){
                obj.put("state", "closed");

            }else{
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
