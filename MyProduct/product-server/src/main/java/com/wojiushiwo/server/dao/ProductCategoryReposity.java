package com.wojiushiwo.server.dao;

import com.wojiushiwo.server.domain.ProductCategoryDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCategoryReposity extends JpaRepository<ProductCategoryDomain, Integer> {
    List<ProductCategoryDomain> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
