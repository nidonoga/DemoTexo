package com.demo.demotexo.view;

import java.util.Objects;

public class ProducerPrizeDetailView {
	
	private String producer;
	private Integer interval;
	private Integer previousWin;
	private Integer followingWin;
	
	public ProducerPrizeDetailView() {
		
	}

	public ProducerPrizeDetailView(String producer, Integer previousWin, Integer followingWin, Integer interval) {
		super();
		this.producer = producer;
		this.interval = interval;
		this.previousWin = previousWin;
		this.followingWin = followingWin;
	}

	public ProducerPrizeDetailView(String producer, Integer previousWin) {
		this.producer = producer;
		this.previousWin = previousWin;
	}
	
	public String getProducer() {
		return producer;
	}
	
	public void setProducer(String producer) {
		this.producer = producer;
	}
	
	public Integer getInterval() {
		return interval;
	}
	
	public void setInterval(Integer interval) {
		this.interval = interval;
	}
	
	public Integer getPreviousWin() {
		return previousWin;
	}
	
	public void setPreviousWin(Integer previousWin) {
		this.previousWin = previousWin;
	}
	
	public Integer getFollowingWin() {
		return followingWin;
	}
	
	public void setFollowingWin(Integer followingWin) {
		this.followingWin = followingWin;
	}

	@Override
	public int hashCode() {
		return Objects.hash(followingWin, interval, previousWin, producer);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProducerPrizeDetailView other = (ProducerPrizeDetailView) obj;
		return Objects.equals(followingWin, other.followingWin) && Objects.equals(interval, other.interval)
				&& Objects.equals(previousWin, other.previousWin) && Objects.equals(producer, other.producer);
	}
	
	

}
