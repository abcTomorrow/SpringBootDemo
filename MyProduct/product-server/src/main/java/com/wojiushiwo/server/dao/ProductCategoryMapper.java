package com.wojiushiwo.server.dao;

import com.wojiushiwo.server.domain.ProductCategoryDomain;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.MySqlMapper;

@Repository
public interface ProductCategoryMapper extends tk.mybatis.mapper.common.Mapper<ProductCategoryDomain>, MySqlMapper<ProductCategoryDomain> {
}
