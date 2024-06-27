package org.example;

import org.example.repository.AuthorRepository;
import org.example.repository.BookRepository;
import org.example.service.BookService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {
    @Bean
    public BookRepository bookRepository() {
        return new BookRepository();
    }

    @Bean
    public AuthorRepository authorRepository() {
        return new AuthorRepository();
    }

    @Bean
    public BookService bookService(AuthorRepository authorRepository) {
        return new BookService(bookRepository(), authorRepository);
    }
}
