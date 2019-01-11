package com.wojiushiwo.server.controller;

import com.wojiushiwo.common.dataobject.DecreaseStockInput;
import com.wojiushiwo.common.dataobject.ProductInfoOutVO;
import com.wojiushiwo.server.VO.ProductInfoVO;
import com.wojiushiwo.server.VO.ProductVO;
import com.wojiushiwo.server.VO.ResultVO;
import com.wojiushiwo.server.domain.ProductCategoryDomain;
import com.wojiushiwo.server.domain.ProductInfoDomain;
import com.wojiushiwo.server.service.CategoryService;
import com.wojiushiwo.server.service.ProductService;
import com.wojiushiwo.server.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    /**
     * 1. 查询所有在架的商品
     * 2. 获取类目type列表
     * 3. 查询类目
     * 4. 构造数据
     * 类目type与商品为1对多
     */
    @GetMapping("/list")
    public ResultVO list() {
        //查詢所有在架的商品
        List<ProductInfoDomain> productInfos = productService.findUpAll();
        //获取类目type列表‰
        List<Integer> categoryTypeList = productInfos.stream().map(ProductInfoDomain::getCategoryType).collect(Collectors.toList());
        //查詢類目
        List<ProductCategoryDomain> categoryDomainList = categoryService.findByProductCategoryType(categoryTypeList);

        List<ProductVO> resultList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(categoryTypeList) && !CollectionUtils.isEmpty(categoryDomainList)) {
            for (ProductCategoryDomain categoryDomain : categoryDomainList) {
                ProductVO productVO = new ProductVO();
//                BeanUtils.copyProperties(categoryDomain, productVO);
                productVO.setCategoryName(categoryDomain.getCategoryName());
                productVO.setCategoryType(categoryDomain.getCategoryType());
                List<ProductInfoVO> productInfoVOList = new ArrayList<>();
                for (ProductInfoDomain infoDomain : productInfos) {
                    if (categoryDomain.getCategoryType() == infoDomain.getCategoryType()) {
                        ProductInfoVO productInfoVO = new ProductInfoVO();
                        BeanUtils.copyProperties(infoDomain, productInfoVO);
                        productInfoVOList.add(productInfoVO);
                    }
                }

                productVO.setProductInfoVOList(productInfoVOList);
                resultList.add(productVO);
            }
        }
        return ResultVOUtil.success(resultList);
    }

    /**
     * 查询商品列表 供订单服务使用
     *
     * @param -- productIdList
     * @return
     */
    @GetMapping("/listForOrder")
    public List<ProductInfoOutVO> productListForOrder() {
        return productService.productListForOrder(Arrays.asList("20181107190922", "20181108190922"));
    }

    @PostMapping("/decreaseStock")
    public List<ProductInfoDomain> decreaseStockProcess(@RequestBody List<DecreaseStockInput> sourceList) {
        return productService.decreaseStockProcess(sourceList);
    }

    @GetMapping("/test")
    public List<String> sout() {
        List<String> list = Arrays.asList("11", "22");
//        ObjectMapper objectMapper = new ObjectMapper();
//        try {
//            return objectMapper.writeValueAsString(list);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return list;
    }

}
