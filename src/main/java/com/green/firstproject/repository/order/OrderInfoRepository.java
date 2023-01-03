package com.green.firstproject.repository.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.green.firstproject.entity.order.OrderInfoEntity;

@Repository
public interface OrderInfoRepository extends JpaRepository<OrderInfoEntity, Long>{
    
}