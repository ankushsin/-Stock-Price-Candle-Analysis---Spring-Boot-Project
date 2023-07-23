package com.investoBull.Models;

import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class StockCandle {
	   @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @ElementCollection
	    private List<Candle> candles;

	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public List<Candle> getCandles() {
	        return candles;
	    }

	    public void setCandles(List<Candle> candles) {
	        this.candles = candles;
	    }

		public StockCandle() {
			
			// TODO Auto-generated constructor stub
		}

		public StockCandle(Long id, List<Candle> candles) {
			super();
			this.id = id;
			this.candles = candles;
		}

		@Override
		public String toString() {
			return "StockCandle [id=" + id + ", candles=" + candles + "]";
		}
		
	    
	    

}
