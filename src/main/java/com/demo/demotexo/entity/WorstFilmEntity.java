package com.demo.demotexo.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.demo.demotexo.entity.types.NoYesType;

@Entity
@Table(name = "WORSTFILM")
public class WorstFilmEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idWorstFilm;

	private Integer releaseYear;

	private String title;

	private String studio;

	private String producer;

	@Enumerated(EnumType.ORDINAL)
	private NoYesType winner;

	public WorstFilmEntity() {
		
	}
	
	public WorstFilmEntity(Integer releaseYear, String title, String studio, String producer, NoYesType winner) {
		this.releaseYear = releaseYear;
		this.title = title;
		this.studio = studio;
		this.producer = producer;
		this.winner = winner;
	}

	public Integer getIdWorstFilm() {
		return idWorstFilm;
	}

	public void setIdWorstFilm(Integer idWorstFilm) {
		this.idWorstFilm = idWorstFilm;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStudio() {
		return studio;
	}

	public void setStudio(String studio) {
		this.studio = studio;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public NoYesType getWinner() {
		return winner;
	}

	public void setWinner(NoYesType winner) {
		this.winner = winner;
	}

	public Integer getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(Integer releaseYear) {
		this.releaseYear = releaseYear;
	}
	
	
	
}
