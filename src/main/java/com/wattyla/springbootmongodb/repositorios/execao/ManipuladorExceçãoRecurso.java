package com.wattyla.springbootmongodb.repositorios.execao;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.wattyla.springbootmongodb.servicos.execoes.ExecaoBancoDados;
import com.wattyla.springbootmongodb.servicos.execoes.ExecaoRecursoNaoEncontrado;

@ControllerAdvice
public class ManipuladorExceçãoRecurso {
	
	@ExceptionHandler(ExecaoRecursoNaoEncontrado.class)
	public ResponseEntity<ErroPadrao> recursoNaoEncontrado(ExecaoRecursoNaoEncontrado e, HttpServletRequest requisicao){
		String erro = "Recurso não encontrado";
		HttpStatus status = HttpStatus.NOT_FOUND;
		ErroPadrao err = new ErroPadrao(Instant.now(), status.value(), erro, e.getMessage(), requisicao.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(ExecaoBancoDados.class)
	public ResponseEntity<ErroPadrao> BancoDados(ExecaoBancoDados e, HttpServletRequest requisicao){
		String erro = "Erro de banco de dados";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ErroPadrao err = new ErroPadrao(Instant.now(), status.value(), erro, e.getMessage(), requisicao.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
}
