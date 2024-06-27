package org.example.repository;

import org.example.entity.Author;
import org.example.entity.Book;

public class BookRepository {
    public Book getBook(Author author) {
        return new Book("The Great Gatsby", author);
    }
}
