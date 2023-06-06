package com.atguigu.jxc.service.impl;

import com.atguigu.jxc.dao.OverflowListDao;
import com.atguigu.jxc.dao.OverflowListGoodsDao;
import com.atguigu.jxc.entity.OverflowList;
import com.atguigu.jxc.entity.OverflowListGoods;
import com.atguigu.jxc.entity.User;
import com.atguigu.jxc.service.OverflowListGoodsService;
import com.atguigu.jxc.util.DateUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: jxc
 * @description:
 * @author: jq
 * @create: 2023-06-05 19:10
 **/
@Service
public class OverflowListGoodsServiceImpl implements OverflowListGoodsService {
    @Resource
    OverflowListGoodsDao overflowListGoodsDao;
    @Resource
    OverflowListDao overflowListDao;

    @Override
    public void saveOverFlowListGoods(OverflowList overflowList, String overflowListGoodsStr, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("currentUser");
        overflowList.setUserId(user.getUserId());
        overflowListDao.saveOverFlowList(overflowList);
        Gson gson = new Gson();
        TypeToken<ArrayList<OverflowListGoods>> typeToken = new TypeToken<ArrayList<OverflowListGoods>>() {
        };
        ArrayList<OverflowListGoods> overflowLists = gson.fromJson(overflowListGoodsStr, typeToken.getType());
        OverflowListGoods overflowListGoods = overflowLists.get(0);
        overflowListGoods.setOverflowListId(overflowList.getOverflowListId());
        overflowListGoodsDao.saveOverflowListGoods(overflowListGoods);
    }

    @Override
    public List<OverflowList> getOverFlowList(String sTime, String eTime) {
        List<OverflowList> overflowLists = overflowListDao.queryAllOverFlowList();
        if (sTime != null && eTime != null) {
            overflowLists = overflowLists.stream().filter(overflowList -> DateUtil.isScopeTime(overflowList.getOverflowDate(),
                    sTime, eTime)).collect(Collectors.toList());

        }
        return overflowLists;
    }

    @Override
    public List<OverflowListGoods> getGoodsListByOverflowListId(Integer overflowListId) {
        List<OverflowListGoods> overflowListGoodsList = overflowListDao.getGoodsListByOverflowListId(overflowListId);
        return overflowListGoodsList;
    }
}
