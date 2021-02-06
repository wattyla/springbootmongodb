package com.wattyla.springbootmongodb.dto;

import java.io.Serializable;

import com.wattyla.springbootmongodb.dominio.Usuario;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class DTOUsuario implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	private String email;
	
	public DTOUsuario(Usuario obj) {
		id = obj.getId();
		name = obj.getName();
		email = obj.getEmail();
	}
}
