package com.wojiushiwo.order.controller;

import com.wojiushiwo.order.VO.ResultVO;
import com.wojiushiwo.order.dto.OrderDTO;
import com.wojiushiwo.order.enums.ResultEnum;
import com.wojiushiwo.order.exception.OrderException;
import com.wojiushiwo.order.form.OrderForm;
import com.wojiushiwo.order.service.OrderService;
import com.wojiushiwo.order.utils.JsonUtil;
import com.wojiushiwo.order.utils.OrderForm2OrderDTOConverter;
import com.wojiushiwo.order.utils.ResultVoUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 我就是我
 * 2018/11/13 下午2:46
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {
    @Autowired
    private OrderService orderService;

    /**
     * 1. 参数检验
     * 2. 查询商品信息(调用商品服务)
     * 3. 计算总价
     * 4. 扣库存(调用商品服务)
     * 5. 订单入库
     */
    @RequestMapping("/create")
    public ResultVO<Map<String, String>> createOrder(@Validated OrderForm orderForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            if (bindingResult.hasErrors()) {
                log.error("【创建订单】参数不正确, orderForm={}", JsonUtil.toJson(orderForm));
                throw new OrderException(ResultEnum.PARAM_ERROR.getCode(),
                        bindingResult.getFieldError().getDefaultMessage());
            }
        }
        OrderDTO orderDTO = OrderForm2OrderDTOConverter.converter(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【创建订单】购物车信息为空");
            throw new OrderException(ResultEnum.CART_EMPTY);
        }
        OrderDTO result = orderService.create(orderDTO);
        Map<String, String> map = new HashMap<>();
        map.put("orderId", result.getOrderId());
        return ResultVoUtils.success(map);
    }

}
