package com.investoBull.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.investoBull.Models.StockCandle;
import com.investoBull.Services.StockCandleService;

@RestController
@RequestMapping("/api/stock-candles")
public class StockCandleController {
	
	    @Autowired
	    private StockCandleService stockCandleService;
	
	    
//	    ==================================================================
//	      {
//	    	  
//	    	  "candles": [
//	    	   {
//	    	      "lastTradeTime": "2023-07-21T10:30:00",
//	    	      "quotationLot": 100,
//	    	      "tradedQty": 5000,
//	    	      "openInterest": 200,
//	    	      "open": 100.5,
//	    	      "high": 102.0,
//	    	      "low": 100.0,
//	    	      "close": 101.0
//	    	    },
//	    	    {
//	    	      "lastTradeTime": "2023-07-21T10:45:00",
//	    	      "quotationLot": 120,
//	    	      "tradedQty": 6000,
//	    	      "openInterest": 180,
//	    	      "open": 101.0,
//	    	      "high": 103.5,
//	    	      "low": 100.5,
//	    	      "close": 102.5
//	    	    },
//	    	    {
//	    	      "lastTradeTime": "2023-07-21T11:00:00",
//	    	      "quotationLot": 80,
//	    	      "tradedQty": 4000,
//	    	      "openInterest": 160,
//	    	      "open": 102.5,
//	    	      "high": 104.0,
//	    	      "low": 102.0,
//	    	      "close": 103.0
//	    	    },
//	    	    {
//	    	      "lastTradeTime": "2023-07-21T11:15:00",
//	    	      "quotationLot": 150,
//	    	      "tradedQty": 7000,
//	    	      "openInterest": 170,
//	    	      "open": 103.0,
//	    	      "high": 105.0,
//	    	      "low": 102.5,
//	    	      "close": 104.5
//	    	    },
//	    	    {
//	    	      "lastTradeTime": "2023-07-21T11:30:00",
//	    	      "quotationLot": 110,
//	    	      "tradedQty": 5500,
//	    	      "openInterest": 190,
//	    	      "open": 104.5,
//	    	      "high": 106.0,
//	    	      "low": 104.0,
//	    	      "close": 105.0
//	    	    }
//	    	  ]
//	    	}
//	   
//	    FOR SAVING THE REQUIRED DATA == THE SAMPLE CODE IS HERE
	   

	    @PostMapping
		public ResponseEntity<StockCandle> saveStockCandle(@RequestBody StockCandle stockCandle) {
		    stockCandleService.saveStockCandle(stockCandle);
		    return new ResponseEntity<>(stockCandle, HttpStatus.CREATED);
		}

		@GetMapping("/{id}")
		public ResponseEntity<StockCandle> getStockCandleById(@PathVariable Long id) {
		    StockCandle stockCandle = stockCandleService.findStockCandleById(id);
		    if (stockCandle == null) {
		        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		    }
		    return new ResponseEntity<>(stockCandle, HttpStatus.OK);
		}

		@GetMapping
		public ResponseEntity<List<StockCandle>> getAllStockCandles() {
		    List<StockCandle> stockCandles = stockCandleService.getAllStockCandles();
		    if (stockCandles.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		    }
		    return new ResponseEntity<>(stockCandles, HttpStatus.OK);
		}

		@DeleteMapping("/{id}")
		public ResponseEntity<Void> deleteStockCandleById(@PathVariable Long id) {
		    StockCandle stockCandle = stockCandleService.findStockCandleById(id);
		    if (stockCandle == null) {
		        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		    }
		    stockCandleService.deleteStockCandleById(id);
		    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		

		
//		=============================================
//		http://localhost:8080/api/stock-candles/orb-candle-time?minutes=15
//		ORB Generation

		@GetMapping("/orb-candle-time")
		public ResponseEntity<String> getOrbCandleTime(@RequestParam int minutes) {
		    String result = stockCandleService.getOrbCandleTime(minutes);
		    return new ResponseEntity<>(result, HttpStatus.OK);
		}
		
//		==================================================
//		http://localhost:8080/api/stock-candles/generate-candles?intervalInMinutes=15
//			GENERATE CANDLES in intervals

		@GetMapping("/generate-candles")
		public ResponseEntity<List<StockCandle>> generateCandlesWithInterval(@RequestParam int intervalInMinutes) {
		    List<StockCandle> generatedCandles = stockCandleService.generateCandlesWithInterval(intervalInMinutes);
		    return new ResponseEntity<>(generatedCandles, HttpStatus.OK);
		}

}
