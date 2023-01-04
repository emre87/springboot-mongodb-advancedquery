package com.emre;

import java.util.stream.Stream;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.emre.config.MongoDBConfig;
import com.emre.entity.Book;
import com.emre.repository.BookRepository;


public class QueryTest {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(MongoDBConfig.class);
		ctx.refresh();
		BookRepository repository = ctx.getBean(BookRepository.class);
		
		System.out.println("--- findBookById() ---");
		Book myBook = repository.findBookById(102);
		System.out.println("102 id numaralı kayıt : " + myBook);
		
		System.out.println("--- findBooksByWriterAndCategory() ---");		

		try (Stream<Book> stream = repository.findBooksByWriterAndCategory("Emre", "Frontend")) {
			  stream.forEach(book -> System.out.println(book));
		}	
		
		System.out.println("--- findBooksGtThanNoOfPages() ---");		
        int noOfPages = 200;
		try (Stream<Book> stream = repository.findBooksGtThanNoOfPages(noOfPages)) {
			  stream.forEach(book -> System.out.println(book));
		}	
		
		System.out.println("--- findBooksByWriterAndLtThanNoOfPages() ---");		
		try (Stream<Book> stream = repository.findBooksByWriterAndLtThanNoOfPages("Muhammed", 250)) {
			  stream.forEach(book -> System.out.println(book));
		}		
		
		System.out.println("--- findBooksByWriterOrCategory() : Ex-1---");		
		try (Stream<Book> stream = repository.findBooksByWriterOrCategory("Muhammed", "Graphics")) {
			  stream.forEach(book -> System.out.println(book));
		}	
		
		System.out.println("--- findBooksByWriterOrCategory() : Ex-2---");		
		try (Stream<Book> stream = repository.findBooksByWriterOrCategory("Sehmus", "Backend")) {
			  stream.forEach(book -> System.out.println(book));
		}		
		
		System.out.println("--- findBestBooks() ---");		
		try (Stream<Book> stream = repository.findBestBooks()) {
			  stream.forEach(book -> System.out.println(book));
		}		
		
		System.out.println("--- findBooksWithCertainFields() ---");		
		try (Stream<Book> stream = repository.findBooksWithCertainFields("Muhammed", "Backend")) {
			  stream.forEach(book -> System.out.println(book));
		}
		
		System.out.println("--- findBookCountByCategory() ---");		
		Integer bookCount = repository.findBookCountByCategory("Frontend");	
		System.out.println("bookCount: " + bookCount);
		
		System.out.println("--- isBooksAvailableByWriter() ---");		
		Boolean isBooksAvailable = repository.isBooksAvailableByWriter("Emre");
		System.out.println("isBooksAvailable: " + isBooksAvailable);	
		
		System.out.println("--- findBooksByWriter() ---");		
		try (Stream<Book> stream = repository.findBooksByWriter("Muhammed")) {
			  stream.forEach(book -> System.out.println(book));
		}	
		
		System.out.println("--- findBooksByCategory() ---");		
		try (Stream<Book> stream = repository.findBooksByCategory("Backend")) {
			  stream.forEach(book -> System.out.println(book));
		}			
		
		System.out.println("--- deleteBooksByCategory() ---");		
		Long deletedCount = repository.deleteBooksByCategory("Frontend");
        System.out.println("deletedCount: " + deletedCount);
		
		ctx.registerShutdownHook();
		ctx.close();
	}
}