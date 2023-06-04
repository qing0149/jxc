package com.atguigu.jxc.vo;

import lombok.Data;

/**
 * @program: jxc
 * @description:
 * @author: jq
 * @create: 2023-06-03 10:50
 **/
@Data
public class GoodsVo {
    Integer page;
    Integer rows;
    String codeOrName;
    Integer goodsTypeId;
}
