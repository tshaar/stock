package com.stock.exchange.stock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stock.exchange.stock.model.Stock;
import com.stock.exchange.stock.model.StockPurchase;
import com.stock.exchange.stock.model.StockPurchaseDTO;
import com.stock.exchange.stock.repo.StockPurchaseRepository;
import com.stock.exchange.stock.repo.StockRepository;

import jakarta.persistence.OptimisticLockException;

@Service
public class StockPurchaseService {

	@Autowired
    private StockRepository stockRepository;
	@Autowired
    private StockPurchaseRepository stockPurchaseRepository;

   
    @Transactional
    public StockPurchase saveStockPurchase(StockPurchaseDTO stockPurchaseDTO) {
    	try {
    		
        Stock stock = stockRepository.findByTicker(stockPurchaseDTO.getTicker())
                .orElseThrow(() -> new RuntimeException("Stock not found with ticker: " + stockPurchaseDTO.getTicker()));
        StockPurchase stockPurchase = new StockPurchase();
        stock.setQuantity(stock.getQuantity() - stockPurchaseDTO.getBoughtQuantity());
        stockPurchase.setBuySide(stockPurchaseDTO.getBuySide());
        stockPurchase.setBoughtQuantity(stockPurchaseDTO.getBoughtQuantity());
        stockPurchase.setStock(stock);
        return stockPurchaseRepository.save(stockPurchase);
    	}
    	 catch (OptimisticLockException e) {
    	        throw new RuntimeException("Failed to purchase stock due to concurrent updates. Please retry.", e);
    	}
    }
}
