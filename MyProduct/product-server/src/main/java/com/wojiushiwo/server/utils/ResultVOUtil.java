package com.wojiushiwo.server.utils;

import com.wojiushiwo.server.VO.ResultVO;

public class ResultVOUtil {
    public static ResultVO success(Object object){
        ResultVO result=new ResultVO();
        result.setCode(0);
        result.setMsg("成功");
        result.setData(object);
        return result;
    }
}
