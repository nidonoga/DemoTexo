package com.demo.demotexo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.demotexo.service.WorstFilmService;
import com.demo.demotexo.view.PrizeRangeView;

@RestController
@RequestMapping("/prizeRange")
public class WorstFilmController {
	
	@Autowired
	private WorstFilmService worstFilmService;
	
	@GetMapping()
	public PrizeRangeView prizeRange() {
		return worstFilmService.prizeRange();
	}

}
