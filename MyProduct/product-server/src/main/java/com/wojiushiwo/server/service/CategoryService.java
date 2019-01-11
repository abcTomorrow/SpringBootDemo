package com.wojiushiwo.server.service;

import com.wojiushiwo.server.dao.ProductCategoryMapper;
import com.wojiushiwo.server.dao.ProductCategoryReposity;
import com.wojiushiwo.server.domain.ProductCategoryDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private ProductCategoryMapper productCategoryMapper;
    @Autowired
    private ProductCategoryReposity productCategoryReposity;

    public List<ProductCategoryDomain> findByProductCategoryType(List<Integer> categoryTypeList) {
        return productCategoryReposity.findByCategoryTypeIn(categoryTypeList);
    }


}
