package com.example.database.dao.impl;

import com.example.database.dao.BookDao;
import com.example.database.domain.Author;
import com.example.database.domain.Book;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
@Component
public class BookDaoImpl implements BookDao {

    private final JdbcTemplate jdbcTemplate;

    public BookDaoImpl(final JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }

    @Override
    public void create(Book book) {
        jdbcTemplate.update(
                "insert into books (isbn,title,author_id) values (?,?,?)",
                book.getIsbn(),
                book.getTitle(),
                book.getAuthorId()
        );
    }

    @Override
    public Optional<Book> findOne(String isbn) {
        List<Book> results=jdbcTemplate.query(
                "select isbn,title,author_id from books where isbn= ? limit 1",
                new BookRowMapper(),
                isbn
        );

        return results.stream().findFirst();
    }

    public static class BookRowMapper implements RowMapper<Book>{

        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Book.builder()
                    .isbn(rs.getString("isbn"))
                    .title(rs.getString("title"))
                    .authorId(rs.getLong("author_id")).build();
        }
    }

    @Override
    public List<Book> find() {
        return jdbcTemplate.query(
                "select isbn,title,author_id from books",
                new BookRowMapper()
        );
    }

    @Override
    public void update(String number, Book book) {
        jdbcTemplate.update(
                "update books set isbn=?,title=?,author_id=? where isbn=?",
                book.getIsbn(),book.getTitle(),book.getAuthorId(),number
        );
    }

    @Override
    public void delete(String number) {
        jdbcTemplate.update(
                "delete from books where isbn=?",
                number
        );
    }
}
