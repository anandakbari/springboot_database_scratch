package com.example.database.dao.impl;

import com.example.database.TestDataUtil;
import com.example.database.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class BookDaoImplTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private BookDaoImpl underTest;

    @Test
    public void testThatCreateBookGeneratesCorrectSql(){
        Book book= TestDataUtil.createTestBookA();

        underTest.create(book);

        verify(jdbcTemplate).update(
                eq("insert into books (isbn,title,author_id) values (?,?,?)"),
                eq("53534"), eq("the shadow of attic"),eq(1L)
        );
    }

    @Test
    public void testThatFindOneBookGeneratesCorrectSql(){
        underTest.findOne("53534");

        verify(jdbcTemplate).query(
                eq("select isbn,title,author_id from books where isbn= ? limit 1"),
                ArgumentMatchers.<BookDaoImpl.BookRowMapper>any(),
                eq("53534")
        );
    }
    @Test
    public void testThatFindManyBookGeneratesTheCorrectSql(){
        underTest.find();

        verify(jdbcTemplate).query(
                eq("select isbn,title,author_id from books"),
                ArgumentMatchers.<BookDaoImpl.BookRowMapper>any()
        );
    }

    @Test
    public void testThatUpdateGeneratesTheCorrectSql(){
        Book book=TestDataUtil.createTestBookA();
        underTest.update("53534",book);

        verify(jdbcTemplate).update(
                eq("update books set isbn=?,title=?,author_id=? where isbn=?"),
                eq("53534"), eq("the shadow of attic"),eq(1L),eq("53534")
        );
    }

    @Test
    public void TestThatBookDeleteGeneratesCorrectSql(){
        underTest.delete("53534");
        verify(jdbcTemplate).update(
                "delete from books where isbn=?",
                "53534"
        );
    }

}
