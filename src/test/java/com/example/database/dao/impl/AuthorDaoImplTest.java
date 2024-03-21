package com.example.database.dao.impl;

import com.example.database.TestDataUtil;
import com.example.database.domain.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AuthorDaoImplTest {

    @Mock
    private JdbcTemplate jdbcTemplate;
    @InjectMocks
    private AuthorDaoImpl underTest;

    @Test
    public void testThatCreateAuthorGeneratesCorrectSql(){
        Author author= TestDataUtil.createTestAuthorA();
        underTest.create(author);

        verify(jdbcTemplate).update(eq("insert into authors (id,name,age) values (?,?,?)"),
                eq(1L),eq("anand"),eq(80)
                );
    }

    @Test
    public void testThatFindOneGeneratesTheCorrectSql(){
        underTest.findOne(1L);

        verify(jdbcTemplate).query(
                eq("select id,name,age from authors where id= ? limit 1"),
                ArgumentMatchers.<AuthorDaoImpl.AuthorRowMapper>any(),
                eq(1L)
        );
    }

    @Test
    public void testThatFindManyGeneratesTheCorrectSql(){
        underTest.find();
        verify(jdbcTemplate).query(
                eq("select id,name,age from authors"),
                ArgumentMatchers.<AuthorDaoImpl.AuthorRowMapper>any()
        );
    }

    @Test
    public void testThatUpdateGeneratesCorrectSql(){
        Author author=TestDataUtil.createTestAuthorA();
        underTest.update(author.getId(),author);

        verify(jdbcTemplate).update(
                eq("update authors set id=?,name=?,age=? where id=?"),
                eq(1L),eq("anand"),eq(80),eq(1L)
        );
    }

    @Test
    public void testThatDeleteGeneratesCorrectSql(){
        underTest.delete(1L);
        verify(jdbcTemplate).update(
                "delete from authors where id=?",
                1L
        );
    }

}
