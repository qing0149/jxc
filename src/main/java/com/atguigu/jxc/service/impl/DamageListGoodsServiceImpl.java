package com.atguigu.jxc.service.impl;


import com.atguigu.jxc.dao.DamageListGoodsDao;
import com.atguigu.jxc.entity.DamageList;
import com.atguigu.jxc.entity.DamageListGoods;
import com.atguigu.jxc.entity.User;
import com.atguigu.jxc.service.DamageListGoodsService;

import com.atguigu.jxc.util.DateUtil;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.util.resources.cldr.CalendarData;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import static com.atguigu.jxc.util.DateUtil.isScopeTime;

/**
 * @program: jxc
 * @description:
 * @author: jq
 * @create: 2023-06-05 16:29
 **/
@Service
public class DamageListGoodsServiceImpl implements DamageListGoodsService {
    @Resource
    DamageListGoodsDao damageListGoodsDao;


    @Override
    @Transactional
    public void saveDamageListGoods(DamageList damageList, String damageListGoodsStr, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("currentUser");
        damageList.setUserId(user.getUserId());
        damageListGoodsDao.saveDamageListDao(damageList);
        Gson gson = new Gson();
        TypeToken<List<DamageListGoods>> typeToken = new TypeToken<List<DamageListGoods>>() {
        };
        List<DamageListGoods> goodsList = gson.fromJson(damageListGoodsStr, typeToken.getType());
        DamageListGoods damageListGoods = goodsList.get(0);
        damageListGoods.setDamageListId(damageList.getDamageListId());
        damageListGoodsDao.saveDamageListGoodsDao(damageListGoods);
    }

    @Override
    public List<DamageListGoods> goodsList(Integer damageListId) {
        List<DamageListGoods> damageLists = damageListGoodsDao.queryDamageListByDamageListId(damageListId);
        return damageLists;
    }


    @Override
    public List<DamageList> getDamageListGoods(String sTime, String eTime) {
        //todo:queryDamageList还没有完成
        List<DamageList> damageLists = damageListGoodsDao.queryDamageList();
        damageLists = damageLists.stream().filter(damageList ->
                isScopeTime(damageList.getDamageDate(), sTime, eTime)).collect(Collectors.toList());
        return damageLists;
    }

}
