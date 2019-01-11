package com.wojiushiwo.server.service;

import com.wojiushiwo.server.dao.ProductInfoMapperTest;
import com.wojiushiwo.server.domain.ProductInfoDomain;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProductServiceTest extends ProductInfoMapperTest {

    @Autowired
    private ProductService productService;

    @Test
    public void findByProductStatus() {
        List<ProductInfoDomain> list = productService.findUpAll();
        Assert.assertTrue(list.size() > 0);
    }

}