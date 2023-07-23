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

		@GetMapping("/orb-candle-time")
		public ResponseEntity<String> getOrbCandleTime(@RequestParam int minutes) {
		    String result = stockCandleService.getOrbCandleTime(minutes);
		    return new ResponseEntity<>(result, HttpStatus.OK);
		}

		@GetMapping("/generate-candles")
		public ResponseEntity<List<StockCandle>> generateCandlesWithInterval(@RequestParam int intervalInMinutes) {
		    List<StockCandle> generatedCandles = stockCandleService.generateCandlesWithInterval(intervalInMinutes);
		    return new ResponseEntity<>(generatedCandles, HttpStatus.OK);
		}

}
