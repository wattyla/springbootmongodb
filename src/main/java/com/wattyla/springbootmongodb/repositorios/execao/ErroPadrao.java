package com.wattyla.springbootmongodb.repositorios.execao;

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
public class ErroPadrao implements Serializable {
	private static final long serialVersionUID = 1L;

	private Instant timestamp;
	private Integer status;
	private String erro;
	private String messagem;
	private String caminho;

}
