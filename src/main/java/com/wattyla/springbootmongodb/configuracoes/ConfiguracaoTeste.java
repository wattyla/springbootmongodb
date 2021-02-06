package com.wattyla.springbootmongodb.configuracoes;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.wattyla.springbootmongodb.dominio.Post;
import com.wattyla.springbootmongodb.dominio.Usuario;
import com.wattyla.springbootmongodb.dto.DTOAutor;
import com.wattyla.springbootmongodb.dto.DTOComentario;
import com.wattyla.springbootmongodb.repositorios.RepositorioPost;
import com.wattyla.springbootmongodb.repositorios.RepositorioUsuario;

@Configuration
public class ConfiguracaoTeste implements CommandLineRunner {

	@Autowired
	private RepositorioUsuario repositorioUsuario;
	
	@Autowired
	private RepositorioPost repositorioPost;
	@Override
	public void run(String... args) throws Exception {
		
		repositorioUsuario.deleteAll();
		repositorioPost.deleteAll();
		
		Usuario usuario1 = new Usuario(null, "Maria Brown", "maria@gmail.com");
		Usuario usuario2 = new Usuario(null, "Alex Green", "alexgreen@gmail.com");
		Usuario usuario3 = new Usuario(null, "Green", "green@gmail.com");
		Usuario usuario4 = new Usuario(null, "Alex", "alex@gmail.com");
		
		repositorioUsuario.saveAll(Arrays.asList(usuario1, usuario2, usuario3, usuario4));
		
		Post post = new Post(null,Instant.parse("2019-06-20T19:53:07Z"),"d","g",new DTOAutor(usuario1));
		Post post1 = new Post(null,Instant.parse("2021-07-23T19:53:07Z"),"oi","oi",new DTOAutor(usuario1));

		DTOComentario c1 = new DTOComentario("Boa viagem mano!", Instant.parse("2019-06-20T19:53:07Z"), new DTOAutor(usuario2));
		DTOComentario c2 = new DTOComentario("Aproveite", Instant.parse("2019-06-20T19:53:07Z"), new DTOAutor(usuario3));
		DTOComentario c3 = new DTOComentario("Tenha um ótimo dia!", Instant.parse("2019-06-20T19:53:07Z"), new DTOAutor(usuario4));
		
		post.getComentarios().addAll(Arrays.asList(c1, c2));
		post1.getComentarios().addAll(Arrays.asList(c3));
		
		repositorioPost.saveAll(Arrays.asList(post, post1));
		
		usuario1.getPosts().addAll(Arrays.asList(post, post1));
		
		repositorioUsuario.save(usuario1);
	}

}
