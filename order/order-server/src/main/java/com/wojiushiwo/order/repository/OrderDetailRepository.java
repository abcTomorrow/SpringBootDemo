package com.wojiushiwo.order.repository;

import com.wojiushiwo.order.domain.OrderDetailDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by 我就是我
 * 2018/11/11 下午10:34
 */
@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetailDomain, String> {
}
