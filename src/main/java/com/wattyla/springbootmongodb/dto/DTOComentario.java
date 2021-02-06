package com.wattyla.springbootmongodb.dto;

import java.io.Serializable;
import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DTOComentario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String texto;
	private Instant momento;
	private DTOAutor autor;
	
}
