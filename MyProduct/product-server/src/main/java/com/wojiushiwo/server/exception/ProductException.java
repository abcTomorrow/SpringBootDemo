package com.wojiushiwo.server.exception;

import com.wojiushiwo.server.enums.ResultEnum;

/**
 * Created by 我就是我
 * 2018/11/13 下午3:39
 */
public class ProductException extends RuntimeException {
    private Integer code;

    public ProductException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public ProductException(String message, Integer code) {
        super(message);
        this.code = code;
    }
}
