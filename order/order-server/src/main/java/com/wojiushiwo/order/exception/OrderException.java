package com.wojiushiwo.order.exception;

import com.wojiushiwo.order.enums.ResultEnum;

/**
 * Created by 我就是我
 * 2018/11/13 下午4:02
 */
public class OrderException extends RuntimeException {
    private Integer code;

    public OrderException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public OrderException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
