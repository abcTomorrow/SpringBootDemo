package com.wojiushiwo.order.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wojiushiwo.order.domain.OrderDetailDomain;
import com.wojiushiwo.order.dto.OrderDTO;
import com.wojiushiwo.order.enums.ResultEnum;
import com.wojiushiwo.order.exception.OrderException;
import com.wojiushiwo.order.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 我就是我
 * 2018/11/13 下午3:52
 */
@Slf4j
public class OrderForm2OrderDTOConverter {

    public static OrderDTO converter(OrderForm orderForm) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerPhone(orderDTO.getBuyerPhone());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());

        Gson gson = new Gson();
        List<OrderDetailDomain> orderDetails = new ArrayList<>();
        try {
            orderDetails = gson.fromJson(orderForm.getItems(), new TypeToken<List<OrderDetailDomain>>() {
            }.getType());
        } catch (Exception e) {
            log.error("items类型转换失败,{}", JsonUtil.toJson(orderForm.getItems()));
            throw new OrderException(ResultEnum.PARAM_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetails);
        return orderDTO;
    }

}
