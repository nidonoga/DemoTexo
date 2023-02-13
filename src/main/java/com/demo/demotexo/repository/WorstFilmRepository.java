package com.demo.demotexo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.demo.demotexo.entity.WorstFilmEntity;

public interface WorstFilmRepository extends JpaRepository<WorstFilmEntity, Integer> {
	
	@Query("select worstFilm from WorstFilmEntity worstFilm where worstFilm.winner = 1 ")
	List<WorstFilmEntity> findAllWorstFilmEntityWinner();

}
