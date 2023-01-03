package com.green.firstproject.repository.stock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.green.firstproject.entity.stock.DrinkStockEntity;

@Repository
public interface DrinkStockRepository extends JpaRepository<DrinkStockEntity, Long>{
    
}
