package com.wattyla.springbootmongodb.repositorios;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.wattyla.springbootmongodb.dominio.Usuario;

@Repository
public interface RepositorioUsuario extends MongoRepository<Usuario, String>{

}
