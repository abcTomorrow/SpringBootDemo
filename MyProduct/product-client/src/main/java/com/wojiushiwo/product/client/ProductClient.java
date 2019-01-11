package com.wojiushiwo.product.client;

import com.wojiushiwo.common.dataobject.DecreaseStockInput;
import com.wojiushiwo.common.dataobject.ProductInfoOutVO;
import com.wojiushiwo.server.domain.ProductInfoDomain;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by 我就是我
 * 2018/11/11 下午10:45
 */
@FeignClient("product-server")
public interface ProductClient {
    @RequestMapping("product/listForOrder")
    List<ProductInfoOutVO> listForOrder();

    @PostMapping("product/decreaseStock")
    List<ProductInfoDomain> decreaseStockProcess(@RequestBody List<DecreaseStockInput> sourceList);
}
