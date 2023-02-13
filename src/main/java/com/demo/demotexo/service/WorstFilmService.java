package com.demo.demotexo.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.demotexo.entity.WorstFilmEntity;
import com.demo.demotexo.repository.WorstFilmRepository;
import com.demo.demotexo.view.PrizeRangeView;
import com.demo.demotexo.view.ProducerPrizeDetailView;

@Service
public class WorstFilmService {
	
	@Autowired
	private WorstFilmRepository worstFilmRepository;
		
	public PrizeRangeView prizeRange() {
		
		List<WorstFilmEntity> listAllWorstFilmByWinner = worstFilmRepository.findAllWorstFilmEntityWinner();
		
		Map<String, List<WorstFilmEntity>> mapByProducer = createMapByProducer(listAllWorstFilmByWinner);
		
		if(mapByProducer.isEmpty())
			return generateEmptyReturn();
		
		List<ProducerPrizeDetailView> listAllProducerRanges = new ArrayList<>();
		
		mapByProducer.keySet().forEach(producer -> listAllProducerRanges.addAll(generateAllProducerRanges(producer, mapByProducer.get(producer))));
		
		
		return new PrizeRangeView(getProducersWithMinRange(listAllProducerRanges), getProducersWithMaxRange(listAllProducerRanges));
	}
	
	private List<ProducerPrizeDetailView> getProducersWithMaxRange(List<ProducerPrizeDetailView> listAllProducerRanges) {
		Integer maxRange = listAllProducerRanges.stream()
			.map(ProducerPrizeDetailView::getInterval)
			.max(Comparator.comparing(Integer::intValue))
			.get();
		
		return listAllProducerRanges.stream()
			.filter(producerPrizeDetail -> producerPrizeDetail.getInterval().equals(maxRange))
			.collect(Collectors.toList());
		
	}
	
	private List<ProducerPrizeDetailView> getProducersWithMinRange(List<ProducerPrizeDetailView> listAllProducerRanges) {
		Integer minRange = listAllProducerRanges.stream()
			.map(ProducerPrizeDetailView::getInterval)
			.min(Comparator.comparing(Integer::intValue))
			.get();
		
		return listAllProducerRanges.stream()
			.filter(producerPrizeDetail -> producerPrizeDetail.getInterval().equals(minRange))
			.collect(Collectors.toList());
		
	}
	
	private List<ProducerPrizeDetailView> generateAllProducerRanges(String producer, List<WorstFilmEntity> listWorstFilmsByProducer) {
		
		List<Integer> listYears = listWorstFilmsByProducer.stream()
			.map(WorstFilmEntity::getReleaseYear)
			.sorted(Comparator.comparing(Integer::intValue))
			.collect(Collectors.toList());
		
		
		List<ProducerPrizeDetailView> listAllProducerRanges = new ArrayList<>();
		Integer previousWin = null;
		for(Integer year : listYears) {
			if(previousWin == null) {
				previousWin = year;
				continue;
			}
			listAllProducerRanges.add(new ProducerPrizeDetailView(producer, previousWin, year, year - previousWin));
			previousWin = year;
		}
		
		return listAllProducerRanges;
	}
	
	private Map<String, List<WorstFilmEntity>> createMapByProducer(List<WorstFilmEntity> listWorstFilmWinner) {
		Map<String, List<WorstFilmEntity>> mapByProducer = listWorstFilmWinner.stream().collect(Collectors.groupingBy(WorstFilmEntity::getProducer));
		return removeProducersWithoutPeriod(mapByProducer);
	}
	
	private Map<String, List<WorstFilmEntity>> removeProducersWithoutPeriod(Map<String, List<WorstFilmEntity>> mapByProducer) {
		return mapByProducer
				.entrySet()
				.stream()
				.filter(map -> map.getValue().size() > 1)
				.collect(Collectors.toMap(reg -> reg.getKey(), reg -> reg.getValue()));
	}
	
	private PrizeRangeView generateEmptyReturn() {
		PrizeRangeView prizeRange =  new PrizeRangeView();
		prizeRange.setMax(new ArrayList<>());
		prizeRange.setMin(new ArrayList<>());
		return prizeRange;
	}

}
