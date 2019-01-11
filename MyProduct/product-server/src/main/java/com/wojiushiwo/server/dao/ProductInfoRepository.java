package com.wojiushiwo.server.dao;

import com.wojiushiwo.server.domain.ProductInfoDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductInfoRepository extends JpaRepository<ProductInfoDomain, String> {
    List<ProductInfoDomain> findByProductStatus(Integer productStatus);

    List<ProductInfoDomain> findByProductIdIn(List<String> productIdList);

}
