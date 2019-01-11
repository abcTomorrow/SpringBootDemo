package com.wojiushiwo.order.VO;

import lombok.Data;

/**
 * Created by 我就是我
 * 2018/11/13 下午2:48
 */
@Data
public class ResultVO<T> {
    private Integer code;
    private String msg;
    private T data;
}
