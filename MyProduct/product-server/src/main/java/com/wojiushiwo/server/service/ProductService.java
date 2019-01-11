package com.wojiushiwo.server.service;

import com.wojiushiwo.common.dataobject.DecreaseStockInput;
import com.wojiushiwo.common.dataobject.ProductInfoOutVO;
import com.wojiushiwo.server.dao.ProductInfoMapper;
import com.wojiushiwo.server.dao.ProductInfoRepository;
import com.wojiushiwo.server.domain.ProductInfoDomain;
import com.wojiushiwo.server.enums.ProductStatusEnum;
import com.wojiushiwo.server.enums.ResultEnum;
import com.wojiushiwo.server.exception.ProductException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductInfoMapper productInfoMapper;
    @Autowired
    private ProductInfoRepository productInfoRepository;

    public List<ProductInfoDomain> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }


    public List<ProductInfoOutVO> productListForOrder(List<String> productIdList) {
        return productInfoRepository.findByProductIdIn(productIdList)
                .stream().map(productInfoDomain -> {
                    ProductInfoOutVO productInfoOutVO = new ProductInfoOutVO();
                    BeanUtils.copyProperties(productInfoDomain, productInfoOutVO);
                    return productInfoOutVO;
                }).collect(Collectors.toList());
    }

    @Transactional
    public List<ProductInfoDomain> decreaseStockProcess(List<DecreaseStockInput> decreaseStockInputList) {
        List<ProductInfoDomain> resultList = new ArrayList<>();
        //减库存 然后重新保存进数据库
        for (DecreaseStockInput source : decreaseStockInputList) {
            Optional<ProductInfoDomain> productInfo = productInfoRepository.findById(source.getProductId());
            if (!productInfo.isPresent()) {
                throw new ProductException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            ProductInfoDomain domain = productInfo.get();
            Integer diffStock = domain.getProductStock() - source.getProductQuantity();
            if (diffStock < 0) {
                throw new ProductException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            domain.setProductStock(diffStock);
            productInfoRepository.save(domain);
            resultList.add(domain);
        }
        return resultList;
    }

}
