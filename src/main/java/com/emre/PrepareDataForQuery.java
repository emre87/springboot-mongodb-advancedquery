package com.emre;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.emre.config.MongoDBConfig;
import com.emre.entity.Book;
import com.emre.repository.BookRepository;

public class PrepareDataForQuery {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(MongoDBConfig.class);
		ctx.refresh();
		BookRepository repository = ctx.getBean(BookRepository.class);

		repository.deleteAll();

		Book b1 = new Book(101, "Angular Tutorials", 200, "Emre", "Frontend");
		Book b2 = new Book(102, "JavaScript Tutorials", 200, "Emre", "Frontend");
		Book b3 = new Book(103, "Spring Tutorials", 300, "Muhammed", "Backend");
		Book b4 = new Book(104, "Java Tutorials", 250, "Emre", "Backend");
		Book b5 = new Book(105, "Hibernate Tutorials", 150, "Sehmus", "Backend");
		List<Book> list = new ArrayList<>();
		
		list.add(b1);
		list.add(b2);
		list.add(b3);
		list.add(b4);
		list.add(b5);		
		
		List<Book> obj = repository.saveAll(list);
		
		ctx.registerShutdownHook();
		ctx.close();
	}
}