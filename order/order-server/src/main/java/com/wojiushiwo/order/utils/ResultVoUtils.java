package com.wojiushiwo.order.utils;

import com.wojiushiwo.order.VO.ResultVO;

/**
 * Created by 我就是我
 * 2018/11/13 下午2:49
 */
public class ResultVoUtils {
    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(object);
        return resultVO;
    }
}
