package com.wojiushiwo.server.dao;

import com.wojiushiwo.server.App;
import com.wojiushiwo.server.domain.ProductCategoryDomain;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ContextConfiguration(classes = App.class)
public class ProductInfoMapperTest {
    @Autowired
    private ProductCategoryReposity productCategoryReposity;

    @Autowired
    private ProductInfoMapper productInfoMapper;
    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    @Test
    public void select() {
        productCategoryReposity.findByCategoryTypeIn(Arrays.asList(11)).stream().forEach(vo -> System.out.println(vo.getCategoryName()));

        Example example = new Example(ProductCategoryDomain.class);
        example.createCriteria().andEqualTo("categoryType", "11");
        List<ProductCategoryDomain> productCategoryDomains = productCategoryMapper.selectByExample(example);
        productCategoryDomains.stream().forEach(productCategoryDomain -> {
            System.out.println(productCategoryDomain.getCategoryName());
        });

    }

}