package com.investoBull.Services;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.investoBull.Models.Candle;
import com.investoBull.Models.StockCandle;
import com.investoBull.Repositories.StockCandleRepository;
import java.util.ArrayList;


@Service
public class StockCandleServiceImpl implements StockCandleService {
	
	@Autowired
	private StockCandleRepository stockCandleRepository;

	@Override
	public void saveStockCandle(StockCandle stockCandle) {
		stockCandleRepository.save(stockCandle);
		
	}

	@Override
	public StockCandle findStockCandleById(Long id) {
		return stockCandleRepository.findById(id).orElse(null);
	}

	@Override
	public List<StockCandle> getAllStockCandles() {
		return stockCandleRepository.findAll();
	}

	@Override
	public void deleteStockCandleById(Long id) {
		stockCandleRepository.deleteById(id);
		
	}

	@Override
	public String getOrbCandleTime(int minutes) {
        List<StockCandle> stockCandles = stockCandleRepository.findAll();
        if (stockCandles == null || stockCandles.isEmpty()) {
            return "No candles available.";
        }

        LocalDateTime orbCandleTime = calculateOrbCandleTime(stockCandles, minutes);
        if (orbCandleTime == null) {
            return "ORB candle not found for the given minutes.";
        }

        return "ORB candle generated at \"" + orbCandleTime + "\"";
    }

    private LocalDateTime calculateOrbCandleTime(List<StockCandle> stockCandles, int minutes) {
        // Combine all candles from all StockCandles into a single list
        List<Candle> candles = stockCandles.stream()
                .flatMap(stockCandle -> stockCandle.getCandles().stream())
                .collect(Collectors.toList());

        // Sort the candles by 'LastTradeTime' in ascending order (earliest to latest).
        candles.sort(Comparator.comparing(candle -> parseLastTradeTime(candle.getLastTradeTime())));

        // Define the end time for the ORB candle by adding the specified minutes to the first candle's time.
        LocalDateTime endTime = parseLastTradeTime(candles.get(0).getLastTradeTime()).plusMinutes(minutes);

        // Find the first candle that ends on or after the calculated endTime.
        for (Candle candle : candles) {
            LocalDateTime candleTime = parseLastTradeTime(candle.getLastTradeTime());
            if (!candleTime.isAfter(endTime)) {
                return candleTime; // The first ORB candle's time.
            }
        }

        return null; // ORB candle not found within the given minutes.
    }

    private LocalDateTime parseLastTradeTime(LocalDateTime lastTradeTime) {
        // No need to parse, as it's already LocalDateTime.
        return lastTradeTime;
    }

    @Override
    public List<StockCandle> generateCandlesWithInterval(int intervalInMinutes) {
        List<StockCandle> stockCandles = stockCandleRepository.findAll();
        if (stockCandles.isEmpty()) {
            throw new RuntimeException("No candles available.");
        }

        List<StockCandle> generatedCandles = new ArrayList<>();

        // The implementation for generating candles with the specified interval
        for (StockCandle stockCandle : stockCandles) {
            List<Candle> originalCandles = stockCandle.getCandles();
            List<Candle> newCandles = new ArrayList<>();

            // Sort the candles by 'LastTradeTime' in ascending order (earliest to latest).
            originalCandles.sort(Comparator.comparing(candle -> parseLastTradeTime(candle.getLastTradeTime())));

            // Create new candles with the specified interval
            for (int i = 0; i < originalCandles.size(); i += intervalInMinutes) {
                int endIndex = Math.min(i + intervalInMinutes, originalCandles.size());
                List<Candle> subList = originalCandles.subList(i, endIndex);
                Candle mergedCandle = mergeCandles(subList);
                newCandles.add(mergedCandle);
            }

            StockCandle newStockCandle = new StockCandle();
            newStockCandle.setCandles(newCandles);
            generatedCandles.add(newStockCandle);
        }

        return generatedCandles;
    }
    
    // Method to merge candles based on your specific requirement
    private Candle mergeCandles(List<Candle> candles) {
        

        
        Candle firstCandle = candles.get(0);
        Candle lastCandle = candles.get(candles.size() - 1);

        return new Candle(
                lastCandle.getLastTradeTime(), // Use the last time for the new merged candle
                firstCandle.getQuotationLot(), // Use the quotation lot of the first candle
                lastCandle.getTradedQty(), // Use the traded quantity of the last candle
                lastCandle.getOpenInterest(), // Use the open interest of the last candle
                firstCandle.getOpen(), // Use the open price of the first candle
                candles.stream().mapToDouble(Candle::getHigh).max().orElse(0.0), // Use the maximum high price
                candles.stream().mapToDouble(Candle::getLow).min().orElse(0.0), // Use the minimum low price
                lastCandle.getClose() // Use the close price of the last candle
        );
    }


	

}
