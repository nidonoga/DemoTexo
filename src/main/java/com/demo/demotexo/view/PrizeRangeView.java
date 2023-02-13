package com.demo.demotexo.view;

import java.util.List;

public class PrizeRangeView {
	
	private List<ProducerPrizeDetailView> min;
	
	private List<ProducerPrizeDetailView> max;
	
	public PrizeRangeView() {
		
	}

	public PrizeRangeView(List<ProducerPrizeDetailView> min, List<ProducerPrizeDetailView> max) {
		this.min = min;
		this.max = max;
	}

	public List<ProducerPrizeDetailView> getMin() {
		return min;
	}

	public void setMin(List<ProducerPrizeDetailView> min) {
		this.min = min;
	}

	public List<ProducerPrizeDetailView> getMax() {
		return max;
	}

	public void setMax(List<ProducerPrizeDetailView> max) {
		this.max = max;
	}
	
	

}
