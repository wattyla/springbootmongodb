package com.wattyla.springbootmongodb.servicos.execoes;

public class ExecaoBancoDados extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public ExecaoBancoDados(String msg) {
		super(msg);
	}
}
