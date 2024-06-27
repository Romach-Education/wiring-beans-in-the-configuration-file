package org.example.repository;

import org.example.entity.Author;

public class AuthorRepository {
    public Author getAuthor() {
        return new Author("F. Scott Fitzgerald");
    }
}
