package com.investoBull.Services;

import java.util.List;

import com.investoBull.Models.StockCandle;

public interface StockCandleService {
	
	void saveStockCandle(StockCandle stockCandle);
	
    StockCandle findStockCandleById(Long id);
    
    List<StockCandle> getAllStockCandles();
    
    void deleteStockCandleById(Long id);
    
    public String getOrbCandleTime(int minutes);
    
    List<StockCandle> generateCandlesWithInterval(int intervalInMinutes);

}
