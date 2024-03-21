package com.example.database.dao;

import com.example.database.domain.Author;
import com.example.database.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    void create(Book book);

    Optional<Book> findOne(String isbn);

    List<Book> find();

    void update(String number, Book book);

    void delete(String number);
}
