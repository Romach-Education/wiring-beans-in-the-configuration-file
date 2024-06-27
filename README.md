# Wiring beans in the configuration files

> This project is based on chapter **3.1. Implementing relationships among beans defined in the configuration file** from book **Spring Starts here (2021)** by Laurentiu Spilca.

## Class diagram

![Class diagram](class-diagram.png)

## Create Maven project with Intellij Idea

File > New project > Java

## Add Spring Context dependency

```xml
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-context</artifactId>
    <version>6.1.10</version>
</dependency>
```

## Create entities

```java
public class Book {
    private final String title;
    private final Author author;

    public Book(String title, Author author) {
        this.title = title;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }
}
```

```java
public class Author {
    private String name;

    public Author(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
```

## Create repositories

```java
public class AuthorRepository {
    public Author getAuthor() {
        return new Author("F. Scott Fitzgerald");
    }
}
```

```java
public class BookRepository {
    public Book getBook(Author author) {
        return new Book("The Great Gatsby", author);
    }
}
```

## Create service

```java
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public Book getBook() {
        final Author author = authorRepository.getAuthor();
        return bookRepository.getBook(author);
    }
}
```

## Create configuration class

- `BookRepository` is wired to `BookService` using `bookRepository()` method call
- `AuthorRepository` is wired to `BookService` using `authorRepository` parameter

```java
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
```

## Create Spring context

```java
ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
```

## Get bean from Spring context

```java
Book book = context.getBean(BookService.class).getBook();
System.out.println("The book is: " + book.getTitle() + " by " + book.getAuthor().getName());
```

