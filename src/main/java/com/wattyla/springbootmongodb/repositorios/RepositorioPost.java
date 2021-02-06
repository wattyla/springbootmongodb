package com.wattyla.springbootmongodb.repositorios;

import java.time.Instant;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.wattyla.springbootmongodb.dominio.Post;

@Repository
public interface RepositorioPost extends MongoRepository<Post, String> {

	@Query("{ 'titulo': { $regex: ?0, $options: 'i' } }")
	List<Post> buscaPorTituloICase(String text);

	List<Post> findByTituloContainingIgnoreCase(String text);

	@Query("{ $and: [  {data: {$gte: ?1} }, {data: {$lte: ?2} } ,"
			+ " { $or: [ "
			+ " { 'titulo': { $regex: ?0, $options: 'i' } },"
			+ " { 'corpo': { $regex: ?0, $options: 'i' } },"
			+ " { 'comentarios.texto': { $regex: ?0, $options: 'i' } } ] } ] }")
	List<Post> buscarFull(String text, Instant mindata, Instant maxdata);
}
