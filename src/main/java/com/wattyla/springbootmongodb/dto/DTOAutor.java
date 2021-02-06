package com.wattyla.springbootmongodb.dto;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.wattyla.springbootmongodb.dominio.Usuario;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Document
public class DTOAutor implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	private String name;
	
	public DTOAutor(Usuario obj) {
		id = obj.getId();
		name = obj.getName();
	}	
}
