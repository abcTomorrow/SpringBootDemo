package com.wojiushiwo.common.dataobject;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by 我就是我
 * 2018/11/10 下午12:05
 */
@Data
public class ProductInfoOutVO {
    private String productId;

    /** 名字. */
    private String productName;

    /** 单价. */
    private BigDecimal productPrice;

    /** 库存. */
    private Integer productStock;

    /** 描述. */
    private String productDescription;

    /** 小图. */
    private String productIcon;

    /** 状态, 0正常1下架. */
    private Integer productStatus;

    /** 类目编号. */
    private Integer categoryType;
}
