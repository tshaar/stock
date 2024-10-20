
package com.stock.exchange.stock.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stock.exchange.stock.model.Stock;
import com.stock.exchange.stock.repo.StockRepository;


@Service
public class StockService {

	@Autowired
	StockRepository stockRepository;


	// Method to update an existing stock by ID
	public Stock updateStock(Stock stockDetails) {
		Optional<Stock> optionalStock = stockRepository.findByTicker(stockDetails.getTicker());

		if (optionalStock.isPresent()) {
			Stock stock = optionalStock.get();
			stock.setTicker(stockDetails.getTicker());
			stock.setPrice(stockDetails.getPrice());
			stock.setQuantity(stock.getQuantity() + stockDetails.getQuantity() );
			return stockRepository.save(stock);
		}
		return stockRepository.save(stockDetails);
	}

	// Method to add multiple stocks
	public List<Stock> addManyStocks(List<Stock> stocks) {
		return stockRepository.saveAll(stocks);
	}

	// Optional: Method to get a stock by ID
	public java.util.Optional<Stock> getStockById(Long id) {
		return stockRepository.findById(id);
	}

	// Optional: Method to retrieve all stocks
	public List<Stock> getAllStocks() {
		return stockRepository.findAll();
	}


}
