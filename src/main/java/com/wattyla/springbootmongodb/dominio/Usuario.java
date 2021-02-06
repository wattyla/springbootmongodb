package com.wattyla.springbootmongodb.dominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document
public class Usuario implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@EqualsAndHashCode.Include
	@Id
	private String id;
	private String name;
	private String email;

	@DBRef(lazy = true)
	private List<Post> posts = new ArrayList<>();

	public Usuario(String id, String name, String email) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
	}

}
