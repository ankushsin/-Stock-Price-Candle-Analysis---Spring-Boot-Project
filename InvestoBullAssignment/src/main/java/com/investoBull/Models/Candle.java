package com.investoBull.Models;

import java.time.LocalDateTime;

import jakarta.persistence.Embeddable;
import jakarta.validation.Valid;

@Embeddable
public class Candle {
	
	@Valid
	private LocalDateTime lastTradeTime;
	@Valid
    private Integer quotationLot;
	@Valid
    private long tradedQty;
	@Valid
    private Integer openInterest;
	@Valid
    private double open;
	@Valid
    private double high;
	@Valid
    private double low;
	@Valid
    private double close;
	public Candle() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Candle(@Valid LocalDateTime lastTradeTime, @Valid Integer quotationLot, @Valid long tradedQty,
			@Valid Integer openInterest, @Valid double open, @Valid double high, @Valid double low,
			@Valid double close) {
		super();
		this.lastTradeTime = lastTradeTime;
		this.quotationLot = quotationLot;
		this.tradedQty = tradedQty;
		this.openInterest = openInterest;
		this.open = open;
		this.high = high;
		this.low = low;
		this.close = close;
	}
	public LocalDateTime getLastTradeTime() {
		return lastTradeTime;
	}
	public void setLastTradeTime(LocalDateTime lastTradeTime) {
		this.lastTradeTime = lastTradeTime;
	}
	public Integer getQuotationLot() {
		return quotationLot;
	}
	public void setQuotationLot(Integer quotationLot) {
		this.quotationLot = quotationLot;
	}
	public long getTradedQty() {
		return tradedQty;
	}
	public void setTradedQty(long tradedQty) {
		this.tradedQty = tradedQty;
	}
	public Integer getOpenInterest() {
		return openInterest;
	}
	public void setOpenInterest(Integer openInterest) {
		this.openInterest = openInterest;
	}
	public double getOpen() {
		return open;
	}
	public void setOpen(double open) {
		this.open = open;
	}
	public double getHigh() {
		return high;
	}
	public void setHigh(double high) {
		this.high = high;
	}
	public double getLow() {
		return low;
	}
	public void setLow(double low) {
		this.low = low;
	}
	public double getClose() {
		return close;
	}
	public void setClose(double close) {
		this.close = close;
	}
	@Override
	public String toString() {
		return "Candle [lastTradeTime=" + lastTradeTime + ", quotationLot=" + quotationLot + ", tradedQty=" + tradedQty
				+ ", openInterest=" + openInterest + ", open=" + open + ", high=" + high + ", low=" + low + ", close="
				+ close + "]";
	}
	
	
	
    
    
    

}
