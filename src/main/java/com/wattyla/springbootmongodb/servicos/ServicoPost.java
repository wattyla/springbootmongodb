package com.wattyla.springbootmongodb.servicos;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wattyla.springbootmongodb.dominio.Post;
import com.wattyla.springbootmongodb.repositorios.RepositorioPost;
import com.wattyla.springbootmongodb.servicos.execoes.ExecaoRecursoNaoEncontrado;

@Service
public class ServicoPost {

	@Autowired
	private RepositorioPost repositorioPost;

	public List<Post> buscarTodos() {
		return repositorioPost.findAll();
	}

	public Post buscarPorId(String id) {
		Optional<Post> obj = repositorioPost.findById(id);
		return obj.orElseThrow(() -> new ExecaoRecursoNaoEncontrado(id));
	}

	public List<Post> buscaPorTitulo(String texto) {
		return repositorioPost.buscaPorTituloICase(texto);
	}

	public List<Post> buscarFull(String text, Instant mindata, Instant maxdata) {
		maxdata = maxdata.plusMillis((24 * 60 * 60 * 1000));
		return repositorioPost.buscarFull(text, mindata, maxdata);
	}
}
