package com.atguigu.jxc.controller;

import com.atguigu.jxc.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @program: jxc
 * @description:
 * @author: jq
 * @create: 2023-06-03 23:57
 **/
@RequestMapping
@RestController
public class GoodsUnitController {
    @Autowired
    private GoodsService goodsService;

    @PostMapping("/unit/list")
    public Map<String, Object> queryAllUnitList() {
        return goodsService.queryAllUnitList();
    }

}
