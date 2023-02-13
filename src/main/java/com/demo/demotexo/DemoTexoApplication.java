package com.demo.demotexo;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.demo.demotexo.service.LoadDataBaseFromFileService;

@SpringBootApplication
public class DemoTexoApplication {
	
	public static final String FILE = "movielist.csv";
	
	@Autowired
	private LoadDataBaseFromFileService loadDataBaseFromFileService;

	public static void main(String[] args) {
		SpringApplication.run(DemoTexoApplication.class, args);
	}
	
	@PostConstruct
	public void postConstruct() {
		loadDataBaseFromFileService.load();
	}

}
