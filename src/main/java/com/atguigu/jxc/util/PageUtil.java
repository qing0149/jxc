package com.atguigu.jxc.util;

import lombok.Getter;
import lombok.Setter;

/**
 * @program: jxc
 * @description:
 * @author: jq
 * @create: 2023-06-03 12:49
 **/
@Getter
@Setter
public class PageUtil {
    private Integer page;
    private Integer limit;

    public PageUtil(Integer page, Integer limit) {
        //1 10 limit 0,10
        //2 10 limit 10,20
        //3 10 limit 20,30
        if (page!=0){
            this.page = (page-1)*limit;
        }
        this.limit = page*limit;
    }
}
