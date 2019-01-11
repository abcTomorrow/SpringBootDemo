package com.wojiushiwo.server.dao;

import com.wojiushiwo.server.domain.ProductInfoDomain;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;
@Repository
public interface ProductInfoMapper extends Mapper<ProductInfoDomain>, MySqlMapper<ProductInfoDomain> {
}
