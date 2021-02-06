package com.wattyla.springbootmongodb.resursos;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.wattyla.springbootmongodb.dominio.Post;
import com.wattyla.springbootmongodb.dominio.Usuario;
import com.wattyla.springbootmongodb.dto.DTOUsuario;
import com.wattyla.springbootmongodb.servicos.ServicoUsuario;

@RestController
@RequestMapping(value = "/usuarios")
public class RecursoUsuario {

	@Autowired
	private ServicoUsuario servicosUsuario;

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<DTOUsuario>> buscarTodos() {
		List<Usuario> listaUsuario = servicosUsuario.buscarTodos();
		List<DTOUsuario> listaDTOUsuario = listaUsuario.stream().map(x -> new DTOUsuario(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listaDTOUsuario);
	}
	
	@RequestMapping(value = "/{id}", method=RequestMethod.GET)
	public ResponseEntity<DTOUsuario> buscarPorI(@PathVariable String id) {
		Usuario usuario = servicosUsuario.buscarPorId(id);
		return ResponseEntity.ok().body(new DTOUsuario(usuario));
	}

	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Usuario> salvar(@RequestBody DTOUsuario dtoUsuario) {
		Usuario usuario = servicosUsuario.fromDTO(dtoUsuario);
		usuario = servicosUsuario.salvar(usuario);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuario.getId())
				.toUri();
		return ResponseEntity.created(uri).body(usuario);
	}
	
	@RequestMapping(value = "/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deletar(@PathVariable String id) {
		servicosUsuario.deletar(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Usuario> salvar(@PathVariable String id, @RequestBody Usuario usuario) {
		usuario = servicosUsuario.atualiza(id, usuario);
		return ResponseEntity.ok().body(usuario);
	}
	
	@RequestMapping(value = "/{id}/posts", method=RequestMethod.GET)
	public ResponseEntity<List<Post>> buscarPosts(@PathVariable String id) {
		Usuario usuario = servicosUsuario.buscarPorId(id);
		return ResponseEntity.ok().body(usuario.getPosts());
	}

}
