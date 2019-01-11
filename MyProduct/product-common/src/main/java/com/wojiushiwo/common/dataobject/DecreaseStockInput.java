package com.wojiushiwo.common.dataobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by 我就是我
 * 2018/11/13 下午3:31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DecreaseStockInput {
    private String productId;
    private Integer productQuantity;
}
