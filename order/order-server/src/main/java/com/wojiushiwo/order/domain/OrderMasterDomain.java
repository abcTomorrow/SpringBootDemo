package com.wojiushiwo.order.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by 我就是我
 * 2018/11/11 下午10:28
 */
@Data
@Entity
@Table(name = "order_master")
public class OrderMasterDomain {
    @Id
    private String orderId;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddress;

    @Column(name = "buyer_openid")
    private String buyerOpenId;

    private BigDecimal buyerAmount;

    private Integer orderStatus;

    private Integer productStatus;

    private Date createTime;

    private Date updateTime;
}
