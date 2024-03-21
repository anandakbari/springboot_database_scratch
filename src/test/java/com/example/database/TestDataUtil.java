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
                .age(50)
                .build();
    }
    public static Author createTestAuthorC() {
        return Author.builder()
                .id(3L)
                .name("jay")
                .age(24)
                .build();
    }

    public static Book createTestBookA() {
        return Book.builder()
                .isbn("53534")
                .title("the shadow of attic")
                .authorId(1L)
                .build();
    }

    public static Book createTestBookB() {
        return Book.builder()
                .isbn("5334534")
                .title("the shadow attic")
                .authorId(2L)
                .build();
    }

    public static Book createTestBookC() {
        return Book.builder()
                .isbn("5345534")
                .title("shadow of attic")
                .authorId(3L)
                .build();
    }
}
