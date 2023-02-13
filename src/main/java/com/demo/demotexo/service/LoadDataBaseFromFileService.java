package com.demo.demotexo.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.demotexo.DemoTexoApplication;
import com.demo.demotexo.entity.WorstFilmEntity;
import com.demo.demotexo.entity.types.NoYesType;
import com.demo.demotexo.repository.WorstFilmRepository;

@Service
public class LoadDataBaseFromFileService {

	@Autowired
	private WorstFilmRepository worstFilmRepository;

	
	@Transactional(readOnly = false)
	public void load() {
		Path arquivo = Paths.get(DemoTexoApplication.FILE);
		
		try(Stream<String> stream = Files.lines(arquivo)) {
		
			List<WorstFilmEntity> list = stream
					.skip(1)
					.map(linha -> linha.split(";"))
					.map(array -> instanceEntity(array)) 
					.collect(Collectors.toList());
			
			worstFilmRepository.saveAll(list);	
			
		} catch (IOException e1) {
			System.out.println("\r\n*** Falha ao carregar arquivo... Obs: o arquivo deve se chamar " + DemoTexoApplication.FILE + " e deve estar na pasta raiz do projeto.\r\n");
		} catch (Exception e) {
			System.out.println("\r\n*** Falha ao processar arquivo...\r\n");
		}
	}
	
	private WorstFilmEntity instanceEntity(String[] array) {	
		return new WorstFilmEntity(Integer.valueOf(array[0]), array[1], array[2], array[3], getWinnerValue(array));		
	}
	
	private NoYesType getWinnerValue(String[] array) {
		if(array.length < 5)
			return NoYesType.NO;
		
		return NoYesType.getType(array[4]);
	}

}
