package com.example.database;

import com.example.database.domain.Author;
import com.example.database.domain.Book;

public class TestDataUtil {
    private TestDataUtil(){

    }


    public static Author createTestAuthorA() {
        return Author.builder()
                .id(1L)
                .name("anand")
                .age(80)
                .build();
    }

    public static Author createTestAuthorB() {
        return Author.builder()
                .id(2L)
                .name("mann")
                .age(49)
                .build();
    }
    public static Author createTestAuthorC() {
        return Author.builder()
                .id(3L)
                .name("jay")
                .age(24)
                .build();
    }

    public static Book createTestBookA(final Author author) {
        return Book.builder()
                .isbn("53534")
                .title("the shadow of attic")
                .author(author)
                .build();
    }

    public static Book createTestBookB(final Author author) {
        return Book.builder()
                .isbn("5334534")
                .title("the shadow attic")
                .author(author)
                .build();
    }

    public static Book createTestBookC(final Author author) {
        return Book.builder()
                .isbn("5345534")
                .title("shadow of attic")
                .author(author)
                .build();
    }
}
