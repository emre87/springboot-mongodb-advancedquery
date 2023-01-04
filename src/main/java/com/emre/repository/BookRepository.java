package com.emre.repository;

import java.util.stream.Stream;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Component;

import com.emre.entity.Book;

@Component
public interface BookRepository extends MongoRepository<Book, Integer> {
	@Query("{id : ?0}")
	Book findBookById(int id);
	
	@Query("{writer : ?0, category : ?1}")
	Stream<Book> findBooksByWriterAndCategory(String writer, String category);
	
	@Query("{noOfPages : {$gt: ?0}}")
	Stream<Book> findBooksGtThanNoOfPages(int noOfPages);
	
	@Query("{writer : ?0, noOfPages : {$lt: ?1}}")
	Stream<Book> findBooksByWriterAndLtThanNoOfPages(String writer, int noOfPages);	
	
	@Query("{$or : [{writer: ?0}, {category : ?1}]}")
	Stream<Book> findBooksByWriterOrCategory(String writer, String category);
	
	@Query("{$and : [{$or : [{noOfPages: {$gt: 275}}, {noOfPages : {$lt: 200}}]}, {$or : [{id: {$gt: 103}}, {id : {$lt: 102}}]}]}")
	Stream<Book> findBestBooks();	
	
	@Query(value = "{writer : ?0, category : ?1}", fields = "{ 'title' : 1, 'noOfPages' : 1, 'writer' : 1}")
	Stream<Book> findBooksWithCertainFields(String writer, String category);
	
	@Query(value = "{category : ?0}", count = true)
	Integer findBookCountByCategory(String category);
	
	@Query(value = "{writer : ?0}", exists = true)
	Boolean isBooksAvailableByWriter(String writer);	
	
	@Query(value = "{writer : ?0}", sort = "{title : 1}") //sorting order by title ascending 
	Stream<Book> findBooksByWriter(String writer);
	
	@Query(value = "{category : ?0}", sort = "{title : -1}") //sorting order by title descending
	Stream<Book> findBooksByCategory(String category);	
	
	@Query(value = "{category : ?0}", delete = true)
	Long deleteBooksByCategory(String category);		
}
