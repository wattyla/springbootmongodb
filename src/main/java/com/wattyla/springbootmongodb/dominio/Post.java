package com.wattyla.springbootmongodb.dominio;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.wattyla.springbootmongodb.dto.DTOAutor;
import com.wattyla.springbootmongodb.dto.DTOComentario;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document
public class Post implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EqualsAndHashCode.Include
	@Id
	private String id;
	private Instant data;
	private String titulo;
	private String corpo;
	private DTOAutor dtoAutor;
	
	private List<DTOComentario> comentarios = new ArrayList<>();

	public Post(String id, Instant date, String title, String body, DTOAutor dtoAutor) {
		super();
		this.id = id;
		this.data = date;
		this.titulo = title;
		this.corpo = body;
		this.dtoAutor = dtoAutor;
	}
	
	
}
