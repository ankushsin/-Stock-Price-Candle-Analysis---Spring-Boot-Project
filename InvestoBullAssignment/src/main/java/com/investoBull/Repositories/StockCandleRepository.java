package com.investoBull.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.investoBull.Models.StockCandle;

@Repository
public interface StockCandleRepository extends JpaRepository<StockCandle, Long> {
	
	

}
