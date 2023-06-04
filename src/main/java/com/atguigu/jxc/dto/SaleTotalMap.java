package com.atguigu.jxc.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @program: jxc
 * @description:
 * @author: jq
 * @create: 2023-06-03 13:37
 **/
@Data
@Getter
@Setter
public class SaleTotalMap {
    Integer goodsId;
    Integer saleTotal;
}
