package com.wojiushiwo.order.service.impl;

import com.wojiushiwo.common.dataobject.DecreaseStockInput;
import com.wojiushiwo.common.dataobject.ProductInfoOutVO;
import com.wojiushiwo.order.domain.OrderDetailDomain;
import com.wojiushiwo.order.domain.OrderMasterDomain;
import com.wojiushiwo.order.dto.OrderDTO;
import com.wojiushiwo.order.enums.OrderStatusEnum;
import com.wojiushiwo.order.enums.PayStatusEnum;
import com.wojiushiwo.order.repository.OrderDetailRepository;
import com.wojiushiwo.order.repository.OrderMasterRepository;
import com.wojiushiwo.order.service.OrderService;
import com.wojiushiwo.order.utils.KeyUtil;
import com.wojiushiwo.product.client.ProductClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 我就是我
 * 2018/11/11 下午10:39
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private ProductClient productClient;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        //orderId-->detailId 1对多
        String orderId = KeyUtil.getUnikey();
        List<String> productIdList = orderDTO.getOrderDetailList().stream().map(OrderDetailDomain::getProductId).collect(Collectors.toList());
        //调用商品服务 查询商品信息
        List<ProductInfoOutVO> productInfoList = productClient.listForOrder();

        //计算总金额 使用的是productInfo的单价 和 OrderDetailDomain的数量
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (OrderDetailDomain domain : orderDTO.getOrderDetailList()) {
            for (ProductInfoOutVO productInfo : productInfoList) {
                if (domain.getProductId().equals(productInfo.getProductId())) {
                    totalAmount = productInfo.getProductPrice().multiply(new BigDecimal(domain.getProductQuantity())).add(totalAmount);
                    //将明细数据保存orderDetail表
                    BeanUtils.copyProperties(productInfo, domain);
                    domain.setOrderId(orderId);
                    domain.setDetailId(KeyUtil.getUnikey());
                    orderDetailRepository.save(domain);
                }
            }
        }
        //扣库存 调用商品服务
        List<DecreaseStockInput> decreaseStockInputList = orderDTO.getOrderDetailList().stream().map(v -> new DecreaseStockInput(v.getProductId(), v.getProductQuantity())).collect(Collectors.toList());
        productClient.decreaseStockProcess(decreaseStockInputList);

        //保存订单主表数据
        OrderMasterDomain masterDomain = new OrderMasterDomain();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO, masterDomain);
        masterDomain.setBuyerAmount(totalAmount);
        masterDomain.setOrderStatus(OrderStatusEnum.NEW.getCode());
        masterDomain.setProductStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(masterDomain);
        return orderDTO;
    }
}
