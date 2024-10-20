package com.stock.exchange.stock.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stock.exchange.stock.model.Stock;
import com.stock.exchange.stock.model.StockPurchaseDTO;

@Component
public class StockPurchaseListener {
	
	@Autowired
    private TaskExecutor taskExecutor;
	
	@Autowired
	private  StockService service;
	
	@Autowired
	private StockPurchaseService purchase;
	
	@Autowired
	private ObjectMapper objectMapper;

    @JmsListener(destination = "stock.publish.queue")
    public void receivePublish(String message) {
        System.out.println("Received message: " + message);
        taskExecutor.execute(() -> processPublish(message));  
    }
    
    @JmsListener(destination = "stock.purchase.queue")
    public void receivePurchase(String message) {
        System.out.println("Received message: " + message);
        taskExecutor.execute(() -> processPurchase(message));  
    }
    
    private void processPublish(String message) {
    	Stock stock = null;
		try {
			stock = objectMapper.readValue(message, Stock.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
    	service.updateStock(stock);
    	System.out.println("Processing message in thread: " + Thread.currentThread());
    }
    
    private void processPurchase(String message) {
    	StockPurchaseDTO dto = null;
    	try {
    		dto = objectMapper.readValue(message, StockPurchaseDTO.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
  
       purchase.saveStockPurchase(dto);

    }
}
