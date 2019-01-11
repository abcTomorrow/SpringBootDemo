package com.wojiushiwo.order.dto;

import com.wojiushiwo.order.domain.OrderDetailDomain;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by 我就是我
 * 2018-11-11 下午10:37
 */
@Data
public class OrderDTO {

    /**
     * 订单id.
     */
    private String orderId;

    /**
     * 买家名字.
     */
    private String buyerName;

    /**
     * 买家手机号.
     */
    private String buyerPhone;

    /**
     * 买家地址.
     */
    private String buyerAddress;

    /**
     * 买家微信Openid.
     */
    private String buyerOpenid;

    /**
     * 订单总金额.
     */
    private BigDecimal orderAmount;

    /**
     * 订单状态, 默认为0新下单.
     */
    private Integer orderStatus;

    /**
     * 支付状态, 默认为0未支付.
     */
    private Integer payStatus;

    private List<OrderDetailDomain> orderDetailList;
}
