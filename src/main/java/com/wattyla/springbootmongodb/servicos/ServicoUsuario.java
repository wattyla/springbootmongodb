package com.wattyla.springbootmongodb.servicos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.wattyla.springbootmongodb.dominio.Usuario;
import com.wattyla.springbootmongodb.dto.DTOUsuario;
import com.wattyla.springbootmongodb.repositorios.RepositorioUsuario;
import com.wattyla.springbootmongodb.servicos.execoes.ExecaoBancoDados;
import com.wattyla.springbootmongodb.servicos.execoes.ExecaoRecursoNaoEncontrado;

@Service
public class ServicoUsuario {
	
	@Autowired
	private RepositorioUsuario repositorioUsuario;
	
	public List<Usuario> buscarTodos(){
		return repositorioUsuario.findAll();
	}
	
	public Usuario buscarPorId(String id) {
		Optional<Usuario> obj = repositorioUsuario.findById(id);
		return obj.orElseThrow(() -> new ExecaoRecursoNaoEncontrado(id));
	}
	
	public Usuario salvar(Usuario usuario) {
		return repositorioUsuario.insert(usuario);	
	}
	
	public void deletar(String id) {
		try {
			repositorioUsuario.deleteById(id);
		}catch (EmptyResultDataAccessException e) {
			throw new ExecaoRecursoNaoEncontrado(id);
		} catch (DataIntegrityViolationException e) {
			throw new ExecaoBancoDados(e.getMessage());
		}
	}
	
	public Usuario atualiza(String id, Usuario obj) {
		try{
			Optional<Usuario> objOpt = repositorioUsuario.findById(id);
			Usuario entidade = objOpt.orElseThrow(() -> new ExecaoRecursoNaoEncontrado(id));
			atualizaDados(entidade, obj);
			return repositorioUsuario.save(entidade);
		} catch (RuntimeException e) {
			throw new ExecaoRecursoNaoEncontrado(id);
		}
	}

	private void atualizaDados(Usuario entidade, Usuario obj) {
		entidade.setName(obj.getName());
		entidade.setEmail(obj.getEmail());
	}
	
	public Usuario fromDTO(DTOUsuario objDto) {
		return new Usuario(objDto.getId(),objDto.getName(),objDto.getEmail());
	}
}
