package com.demo.demotexo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.demo.demotexo.entity.WorstFilmEntity;
import com.demo.demotexo.entity.types.NoYesType;
import com.demo.demotexo.repository.WorstFilmRepository;
import com.demo.demotexo.service.WorstFilmService;
import com.demo.demotexo.view.PrizeRangeView;

@SpringBootTest
public class PrizeRangeTest {
	
	private static final String PRODUCER_1 = "1";
	private static final String PRODUCER_2 = "2";
	private static final String PRODUCER_3 = "3";
	private static final String PRODUCER_4 = "4";
	private static final String PRODUCER_5 = "5";
	private static final String PRODUCER_6 = "6";
	
	@Autowired
	private WorstFilmService worstFilmService;
	
	@MockBean
	private WorstFilmRepository repository;
	
	@Test
	public void mustReturnOnlyOneRecordMinor() {
		List<WorstFilmEntity> listWorstFilm = generareScenario1();
		when(repository.findAllWorstFilmEntityWinner()).thenReturn(listWorstFilm);
		PrizeRangeView prizeRange = worstFilmService.prizeRange();
		assertEquals(prizeRange.getMin().size(), 1);
	}
	
	@Test
	public void mustReturnOnlyOneRecordMajor() {
		List<WorstFilmEntity> listWorstFilm = generareScenario1();
		when(repository.findAllWorstFilmEntityWinner()).thenReturn(listWorstFilm);
		PrizeRangeView prizeRange = worstFilmService.prizeRange();
		assertEquals(prizeRange.getMax().size(), 1);
	}
	
	@Test
	public void mustdReturnProducer1AsMinorRecord() {
		List<WorstFilmEntity> listWorstFilm = generareScenario1();
		when(repository.findAllWorstFilmEntityWinner()).thenReturn(listWorstFilm);
		PrizeRangeView prizeRange = worstFilmService.prizeRange();
		
		assertEquals(prizeRange.getMin().get(0).getProducer(), PRODUCER_1);
		assertEquals(prizeRange.getMin().get(0).getInterval(), 4);
		assertEquals(prizeRange.getMin().get(0).getPreviousWin(), 2000);
		assertEquals(prizeRange.getMin().get(0).getFollowingWin(), 2004);
	}
	
	@Test
	public void mustReturnProducer2AsHighestRecord() {
		List<WorstFilmEntity> listWorstFilm = generareScenario1();
		when(repository.findAllWorstFilmEntityWinner()).thenReturn(listWorstFilm);
		PrizeRangeView prizeRange = worstFilmService.prizeRange();
		
		assertEquals(prizeRange.getMax().get(0).getProducer(), PRODUCER_2);
		assertEquals(prizeRange.getMax().get(0).getInterval(), 10);
		assertEquals(prizeRange.getMax().get(0).getPreviousWin(), 2000);
		assertEquals(prizeRange.getMax().get(0).getFollowingWin(), 2010);
	}
	
	
	@Test
	public void mustReturnMoreThanOneRecordMinor() {
		List<WorstFilmEntity> listWorstFilm = generareScenario2();
		when(repository.findAllWorstFilmEntityWinner()).thenReturn(listWorstFilm);
		PrizeRangeView prizeRange = worstFilmService.prizeRange();
		assertEquals(prizeRange.getMin().size(), 2);
	}
	
	@Test
	public void mustReturnMoreThanOneRecordLargest() {
		List<WorstFilmEntity> listWorstFilm = generareScenario2();
		when(repository.findAllWorstFilmEntityWinner()).thenReturn(listWorstFilm);
		PrizeRangeView prizeRange = worstFilmService.prizeRange();
		assertEquals(prizeRange.getMax().size(), 2);
	}
	
	@Test
	public void mustReturnProducer1AndProducer2AsMinor() {
		List<WorstFilmEntity> listWorstFilm = generareScenario2();
		when(repository.findAllWorstFilmEntityWinner()).thenReturn(listWorstFilm);
		PrizeRangeView prizeRange = worstFilmService.prizeRange();
		
		assertThat(prizeRange.getMin())
			.extracting(reg -> reg.getProducer())
			.containsExactlyInAnyOrder(PRODUCER_1, PRODUCER_2);

	}
	
	@Test
	public void mustReturnProducer3AndProducer4AsGreatest() {
		List<WorstFilmEntity> listWorstFilm = generareScenario2();
		when(repository.findAllWorstFilmEntityWinner()).thenReturn(listWorstFilm);
		PrizeRangeView prizeRange = worstFilmService.prizeRange();
		
		assertThat(prizeRange.getMax())
			.extracting(reg -> reg.getProducer())
			.containsExactlyInAnyOrder(PRODUCER_3, PRODUCER_4);

	}
	
	@Test
	public void mustReturnMinimumAndMaximumEmpty() {
		List<WorstFilmEntity> listWorstFilm = generareScenario3();
		when(repository.findAllWorstFilmEntityWinner()).thenReturn(listWorstFilm);
		PrizeRangeView prizeRange = worstFilmService.prizeRange();
		
		assertTrue(prizeRange.getMin().isEmpty());
		assertTrue(prizeRange.getMax().isEmpty());
	}
	
	
	private List<WorstFilmEntity> generareScenario1() {
		List<WorstFilmEntity> listWorstFilm = new ArrayList<>();
		listWorstFilm.add(new WorstFilmEntity(2000, "", "", PRODUCER_1, NoYesType.YES));
		listWorstFilm.add(new WorstFilmEntity(2004, "", "", PRODUCER_1, NoYesType.YES));
		listWorstFilm.add(new WorstFilmEntity(2010, "", "", PRODUCER_1, NoYesType.YES));
		
		listWorstFilm.add(new WorstFilmEntity(2000, "", "", PRODUCER_2, NoYesType.YES));
		listWorstFilm.add(new WorstFilmEntity(2010, "", "", PRODUCER_2, NoYesType.YES));
		
		listWorstFilm.add(new WorstFilmEntity(2010, " 3", " 3", PRODUCER_3, NoYesType.YES));
		return listWorstFilm;
		
	}
	
	private List<WorstFilmEntity> generareScenario2() {
		List<WorstFilmEntity> listWorstFilm = new ArrayList<>();
		
		// menor 
		listWorstFilm.add(new WorstFilmEntity(2000, "", "", PRODUCER_1, NoYesType.YES));
		listWorstFilm.add(new WorstFilmEntity(2001, "", "", PRODUCER_1, NoYesType.YES));
		listWorstFilm.add(new WorstFilmEntity(2005, "", "", PRODUCER_1, NoYesType.YES));
		
		listWorstFilm.add(new WorstFilmEntity(2001, "", "", PRODUCER_2, NoYesType.YES));
		listWorstFilm.add(new WorstFilmEntity(2002, "", "", PRODUCER_2, NoYesType.YES));
		listWorstFilm.add(new WorstFilmEntity(2006, "", "", PRODUCER_2, NoYesType.YES));
		
		
		// maior 
		listWorstFilm.add(new WorstFilmEntity(2000, "", "", PRODUCER_3, NoYesType.YES));
		listWorstFilm.add(new WorstFilmEntity(2005, "", "", PRODUCER_3, NoYesType.YES));
		listWorstFilm.add(new WorstFilmEntity(2015, "", "", PRODUCER_3, NoYesType.YES));
		
		listWorstFilm.add(new WorstFilmEntity(2001, "", "", PRODUCER_4, NoYesType.YES));
		listWorstFilm.add(new WorstFilmEntity(2006, "", "", PRODUCER_4, NoYesType.YES));
		listWorstFilm.add(new WorstFilmEntity(2016, "", "", PRODUCER_4, NoYesType.YES));
		
		listWorstFilm.add(new WorstFilmEntity(2016, "", "", PRODUCER_5, NoYesType.YES));
		listWorstFilm.add(new WorstFilmEntity(2019, "", "", PRODUCER_5, NoYesType.YES));
		
		listWorstFilm.add(new WorstFilmEntity(2016, "", "", PRODUCER_6, NoYesType.YES));
		
		return listWorstFilm;
	}
	
	private List<WorstFilmEntity> generareScenario3() {
		List<WorstFilmEntity> listWorstFilm = new ArrayList<>();
		 
		listWorstFilm.add(new WorstFilmEntity(2000, "", "", PRODUCER_1, NoYesType.YES));
		
		listWorstFilm.add(new WorstFilmEntity(2001, "", "", PRODUCER_2, NoYesType.YES));
				 
		listWorstFilm.add(new WorstFilmEntity(2000, "", "", PRODUCER_3, NoYesType.YES));
		return listWorstFilm;
	}
	


}
