package com.wattyla.springbootmongodb.resursos;

import java.util.List;
import java.time.Instant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wattyla.springbootmongodb.dominio.Post;
import com.wattyla.springbootmongodb.repositorios.util.URL;
import com.wattyla.springbootmongodb.servicos.ServicoPost;

@RestController
@RequestMapping(value = "/posts")
public class RecursoPost {

	@Autowired
	private ServicoPost servicosPost;

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Post>> buscarTodos() {
		List<Post> post = servicosPost.buscarTodos();
		return ResponseEntity.ok().body(post);
	}
	
	@RequestMapping(value = "/{id}", method=RequestMethod.GET)
	public ResponseEntity<Post> buscarPorId(@PathVariable String id) {
		Post post = servicosPost.buscarPorId(id);
		return ResponseEntity.ok().body(post);
	}
	
	@RequestMapping(value = "/buscartitulo", method=RequestMethod.GET)
	public  ResponseEntity<List<Post>> buscarPorTitulo(@RequestParam(value="texto", defaultValue = "") String texto) {
		texto = URL.decodificaParametro(texto);
		List<Post> post = servicosPost.buscaPorTitulo(texto);
		return ResponseEntity.ok().body(post);
	}
	
	@RequestMapping(value = "/buscarFull", method=RequestMethod.GET)
	public  ResponseEntity<List<Post>> buscarFull(
			@RequestParam(value="text", defaultValue = "") String texto,
			@RequestParam(value="minDate", defaultValue = "") String minDate,
			@RequestParam(value="maxDate", defaultValue = "") String maxDate) {
		texto = URL.decodificaParametro(texto);
		Instant minInstant = URL.converteData(minDate, Instant.MIN);
		Instant maxInstant = URL.converteData(maxDate, Instant.now());
		
		List<Post> post = servicosPost.buscarFull(texto,minInstant,maxInstant);
		return ResponseEntity.ok().body(post);
	}
}
