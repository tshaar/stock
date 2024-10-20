package com.stock.exchange.stock.repo;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stock.exchange.stock.model.Stock;


@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
	
	 Optional<Stock> findByTicker(String ticker);
}